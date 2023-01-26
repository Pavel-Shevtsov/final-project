package com.online.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository <T,ID> extends JpaRepository<T,ID> {

}

