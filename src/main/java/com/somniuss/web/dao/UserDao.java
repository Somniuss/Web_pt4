package com.somniuss.web.dao;

import com.somniuss.web.bean.User;

public interface UserDao {
    
    User registration(String name, String email, String password) throws DaoException; // исправленная сигнатура
    User authorization(String login, String password) throws DaoException;
    
}
