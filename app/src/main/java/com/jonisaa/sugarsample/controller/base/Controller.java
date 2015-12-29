package com.jonisaa.sugarsample.controller.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * @author jonatan.salas
 */
public abstract class Controller<T> {

    @NonNull
    public abstract Long insert(@NonNull T object);

    @Nullable
    public abstract T findById(Class<T> clazz, @NonNull Long id);

    @Nullable
    public abstract List<T> listAll(Class<T> clazz);

    @NonNull
    public abstract Long update(@NonNull T object);

    @NonNull
    public abstract Boolean delete(@NonNull T object);

    public abstract int deleteAll(Class<T> clazz);

    public abstract long getCount(Class<T> clazz);
}
