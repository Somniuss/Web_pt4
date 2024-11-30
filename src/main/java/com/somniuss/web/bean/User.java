package com.somniuss.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;  // Здесь пароль нужно будет зашифровать, прежде чем сохранить
    }

    // Получить имя
    public String getName() {
        return name;
    }

    // Установить имя
    public void setName(String name) {
        this.name = name;
    }

    // Получить email
    public String getEmail() {
        return email;
    }

    // Установить email
    public void setEmail(String email) {
        this.email = email;
    }

    // Получить пароль
    public String getPassword() {
        return password;
    }

    // Установить пароль
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }
}
