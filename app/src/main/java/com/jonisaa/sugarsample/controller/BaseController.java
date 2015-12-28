package com.jonisaa.sugarsample.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jonisaa.sugarsample.controller.base.Controller;
import com.orm.SugarRecord;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class BaseController<T> implements Controller<T> {
    private Class<T> clazz;

    public BaseController() {
    }

    @NonNull
    @Override
    public Long insert(@NonNull T object) {
        return SugarRecord.save(object);
    }

    @Nullable
    @Override
    public T findById(@NonNull Long id) {
        return SugarRecord.findById(clazz, id);
    }

    @Nullable
    @Override
    public List<T> listAll() {
        return SugarRecord.listAll(clazz);
    }

    @NonNull
    @Override
    public Long update(@NonNull T object) {
        return SugarRecord.save(object);
    }

    @NonNull
    @Override
    public Boolean delete(@NonNull T object) {
        return SugarRecord.delete(object);
    }

    @Override
    public int deleteAll() {
        return SugarRecord.deleteAll(clazz);
    }

    @Override
    public long getCount() {
        return SugarRecord.count(clazz);
    }
}
