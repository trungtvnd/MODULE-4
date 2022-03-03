package com.codegym.cms.service;

import java.lang.annotation.Target;
import java.util.Iterator;
import java.util.Optional;

public interface IGeneralService <T>{
    Iterable<T> findAll();
    Optional<T> findById(Long id);
    T save (T t);
    void remove(Long id);
}
