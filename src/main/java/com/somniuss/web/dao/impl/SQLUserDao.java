package com.somniuss.web.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.somniuss.web.bean.User;
import com.somniuss.web.dao.DaoException;
import com.somniuss.web.dao.UserDao;

public class SQLUserDao implements UserDao {

    private Properties dbProperties;

    public SQLUserDao() {
        dbProperties = new Properties();
        // Загрузка файла db.properties с помощью ClassLoader
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (inputStream == null) {
                throw new IOException("Property file 'db.properties' not found in the classpath");
            }
            dbProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace(); // Логирование ошибки при загрузке конфигурации
        }
    }

    private Connection getConnection() throws SQLException {
        String dbUrl = dbProperties.getProperty("db.url");
        String dbUsername = dbProperties.getProperty("db.username");
        String dbPassword = dbProperties.getProperty("db.password");
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    @Override
    public User registration(String name, String email, String password) throws DaoException {
        String dbUrl = dbProperties.getProperty("db.url");
        String dbUsername = dbProperties.getProperty("db.username");
        String dbPassword = dbProperties.getProperty("db.password");

        // Простой вариант, без хеширования пароля
        User user = new User(name, email, password);  // Пароль сохраняется в открытом виде

        try (Connection con = getConnection()) {
            String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword()); // Сохраняем пароль в открытом виде

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    return user; // Пользователь успешно добавлен
                } else {
                    throw new DaoException("Ошибка при добавлении пользователя");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Логирование ошибки при работе с базой данных
            throw new DaoException(e); // Обработка ошибок
        }
    }

    @Override
    public User authorization(String email, String password) throws DaoException {
        try (Connection con = getConnection()) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, email);
                ps.setString(2, password); // Сравниваем пароль в открытом виде

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    // Если пользователь найден, создаем объект User
                    return new User(rs.getString("name"), rs.getString("email"), rs.getString("password"));
                }
                return null; // Пользователь не найден
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Логирование ошибки при работе с базой данных
            throw new DaoException(e); // Обработка ошибок
        }
    }
}
