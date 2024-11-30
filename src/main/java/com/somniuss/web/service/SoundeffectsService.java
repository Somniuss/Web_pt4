package com.somniuss.web.service;

import com.somniuss.web.bean.Soundeffects;

public interface SoundeffectsService {
    
    // Метод для добавления нового саундэффекта
    boolean add(Soundeffects soundeffect) throws ServiceException;
}
