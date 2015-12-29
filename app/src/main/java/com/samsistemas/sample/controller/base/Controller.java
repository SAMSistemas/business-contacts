package com.samsistemas.sample.controller.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * @author jonatan.salas
 */
public interface Controller<T> {

    @NonNull
    Long insert(@NonNull T object);

    @Nullable
    T findById(Class<T> clazz, @NonNull Long id);

    @Nullable
    List<T> listAll(Class<T> clazz);

    @NonNull
    Long update(@NonNull T object);

    @NonNull
    Boolean delete(@NonNull T object);

    int deleteAll(Class<T> clazz);

    long getCount(Class<T> clazz);
}
