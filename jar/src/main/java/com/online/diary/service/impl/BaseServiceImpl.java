package com.online.diary.service.impl;

import com.online.diary.repository.BaseRepository;
import com.online.diary.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public class BaseServiceImpl <T,ID> implements BaseService<T,ID> {

    @Autowired
    private BaseRepository<T,ID> baseRepository;


    @Override
    public void add(T t) {
        baseRepository.save(t);
    }

    @Override
    public void delete(ID id) {
        baseRepository.deleteById(id);
    }

    @Override
    public void modify(T t) {
        baseRepository.save(t);
    }

    @Override
    public T getById(ID id) {
        return baseRepository.findById(id).orElse(null);
    }

    @Override
    public List<T> getAll() {
        return baseRepository.findAll();
    }
}
