package com.jonisaa.sugarsample.controller.base;

import java.util.List;

/**
 *
 */
public interface Controller<T> {

    Long insert(T object);

    T findById(Long id);

    List<T> listAll();

    Long update(T object);

    Boolean delete(T object);

    int deleteAll();

    long getCount();
}
