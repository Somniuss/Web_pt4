package com.somniuss.web.dao.impl;

import com.somniuss.web.bean.News;
import com.somniuss.web.dao.NewsDao;
import com.somniuss.web.dao.DaoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name"; // Укажи имя своей базы данных
    private static final String JDBC_USER = "your_db_user"; // Имя пользователя
    private static final String JDBC_PASSWORD = "your_db_password"; // Пароль
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // Драйвер для MySQL

    @Override
    public List<News> getAllNews() throws DaoException {
        List<News> newsList = new ArrayList<>();
        
        try {
            // Загружаем драйвер базы данных
            Class.forName(JDBC_DRIVER);
            
            // Устанавливаем соединение с базой данных
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM news_table"); // Укажи имя своей таблицы
                 ResultSet resultSet = statement.executeQuery()) {
                
                // Обрабатываем результат запроса
                while (resultSet.next()) {
                    // Создаем объект News и заполняем его данными из ResultSet
                    News news = new News();
                    news.setTitle(resultSet.getString("title")); // Имя столбца "title"
                    news.setContent(resultSet.getString("content")); // Имя столбца "content"
                    
                    // Добавляем объект в список
                    newsList.add(news);
                }
            }
        } catch (Exception e) {
            throw new DaoException("Ошибка при получении новостей", e);
        }
        
        return newsList;
    }
}
