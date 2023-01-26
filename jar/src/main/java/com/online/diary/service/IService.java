package com.online.diary.service;

import java.util.List;

public interface IService<T,ID>{
    void add(T t) ;

    void delete(ID id);

    void modify(T t);

    T getById(ID id);

    List<T> getAll();
}
