package com.somniuss.web.service;

import com.somniuss.web.bean.News;
import java.util.List;

public interface NewsService {
    List<News> getAllNews() throws ServiceException;
}
