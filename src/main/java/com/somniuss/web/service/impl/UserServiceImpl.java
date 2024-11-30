package com.somniuss.web.service.impl;

import com.somniuss.web.bean.User;
import com.somniuss.web.dao.DaoException;
import com.somniuss.web.dao.DaoProvider;
import com.somniuss.web.dao.UserDao;
import com.somniuss.web.service.ServiceException;
import com.somniuss.web.service.UserService;

public class UserServiceImpl implements UserService {

	private final UserDao userDao = DaoProvider.getInstance().getUserDao();

	@Override
	public User signIn(String login, String password) throws ServiceException {
// validation
		try {
			System.out.println("-----1");
			User user = userDao.authorization(login, password);
			System.out.println("-----2");
			return user;
		} catch (DaoException e) {
			System.out.println("-----3");
			throw new ServiceException(e);
		}


	}

	@Override
	public User registration(String name, String email, String password) throws ServiceException {
	    try {
	        
	      
	        // Создаем нового пользователя
	        User user = new User(name, email, password);

	        // Добавляем пользователя в базу данных через UserDao
	        return userDao.registration(name, email, password);
	    } catch (DaoException e) {
	        throw new ServiceException("Ошибка при регистрации пользователя", e);
	    }
	}

	

}
