package com.somniuss.web.service;

import com.somniuss.web.service.impl.SoundeffectsServiceImpl;
import com.somniuss.web.service.impl.UserServiceImpl;
import com.somniuss.web.service.impl.NewsServiceImpl;

public final class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final UserService userService = new UserServiceImpl();
    private final SoundeffectsService soundeffectService = new SoundeffectsServiceImpl();
    private final NewsService newsService = new NewsServiceImpl(); // Добавлен сервис для новостей

    private ServiceProvider() {
    }

    public UserService getUserService() {
        return userService;
    }

    public SoundeffectsService getSoundeffectsService() {
        return soundeffectService;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public static ServiceProvider getInstance() {
        return instance;
    }
}
