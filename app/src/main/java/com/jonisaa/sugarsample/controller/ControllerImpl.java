package com.jonisaa.sugarsample.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jonisaa.sugarsample.controller.base.Controller;
import com.orm.SugarRecord;

import java.util.List;

/**
 * @author jonatan.salas
 */
public class ControllerImpl<T> extends Controller<T> {

    public ControllerImpl() {
        super();
    }

    @NonNull
    @Override
    public Long insert(@NonNull T object) {
        return SugarRecord.save(object);
    }

    @Nullable
    @Override
    public T findById(Class<T> clazz, @NonNull Long id) {
        return SugarRecord.findById(clazz, id);
    }

    @Nullable
    @Override
    public List<T> listAll(Class<T> clazz) {
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
    public int deleteAll(Class<T> clazz) {
        return SugarRecord.deleteAll(clazz);
    }

    @Override
    public long getCount(Class<T> clazz) {
        return SugarRecord.count(clazz);
    }
}
