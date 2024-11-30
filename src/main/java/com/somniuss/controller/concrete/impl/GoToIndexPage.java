package com.somniuss.controller.concrete.impl;

import com.somniuss.web.bean.News;
import com.somniuss.web.controller.concrete.Command;
import com.somniuss.web.service.impl.NewsServiceImpl; // Предположим, это сервис для загрузки новостей
import com.somniuss.web.service.ServiceException; // Импортируем ServiceException
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToIndexPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsServiceImpl newsService = new NewsServiceImpl();
        
        try {
            // Получаем новости
            List<News> newsList = newsService.getAllNews();
            
            // Устанавливаем новости в атрибут запроса
            request.setAttribute("news", newsList);
            
            // Перенаправляем на JSP-страницу
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main_index.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            // Логирование исключения (можно использовать логгер)
            e.printStackTrace();
            
            // Перенаправление на страницу ошибки или отображение сообщения пользователю
            request.setAttribute("errorMessage", "Ошибка при загрузке новостей. Попробуйте позже.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
