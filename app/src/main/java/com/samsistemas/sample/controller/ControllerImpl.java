package com.samsistemas.sample.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.samsistemas.sample.controller.base.Controller;
import com.orm.SugarRecord;

import java.util.List;

import javax.inject.Singleton;

/**
 * @author jonatan.salas
 */
@Singleton
public class ControllerImpl<T> implements Controller<T> {

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
