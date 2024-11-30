package com.somniuss.web.dao;

import com.somniuss.web.bean.News;
import java.util.List;

public interface NewsDao {
    List<News> getAllNews() throws DaoException;
}
