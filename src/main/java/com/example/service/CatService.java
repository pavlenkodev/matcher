package com.example.service;

import com.example.model.Cat;

import java.util.List;

public interface CatService {

    Cat save(Cat cat);

    Cat findById(long id);

    void removeById(long id);

    void resetById(Long id);

    List<Cat> findAllByIsDeletedTrue();

    List<Cat> findAllByIsDeletedFalse();

    void likeCat(Long id);

    List<Cat> getTop10();

    List<Cat> findAll();
}
