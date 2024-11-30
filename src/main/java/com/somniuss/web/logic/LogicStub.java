package com.somniuss.web.logic;

import com.somniuss.web.bean.AuthInfo;
import com.somniuss.web.bean.User;

public class LogicStub {
    
    public User checkAuth(AuthInfo authInfo) {
        // Проверка аутентификации
        if ("user@mail.ru".equals(authInfo.getLogin())) {
            // Здесь добавлен третий параметр — пароль
            return new User("Olga", "user@mail.ru", "password123");
        }
        return new User("Olga", "user@mail.ru", "password123");  // По аналогии
    }
}

