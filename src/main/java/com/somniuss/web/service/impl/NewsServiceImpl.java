package com.somniuss.web.service.impl;

import com.somniuss.web.bean.News;
import com.somniuss.web.dao.NewsDao;
import com.somniuss.web.dao.DaoException;
import com.somniuss.web.dao.DaoProvider;
import com.somniuss.web.service.NewsService;
import com.somniuss.web.service.ServiceException;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    private final NewsDao newsDao = DaoProvider.getInstance().getNewsDao();

    @Override
    public List<News> getAllNews() throws ServiceException {
        try {
            return newsDao.getAllNews(); // Предположим, что метод getAllNews() возвращает список всех новостей
        } catch (DaoException e) {
            throw new ServiceException("Ошибка при получении списка новостей", e);
        }
    }
}
