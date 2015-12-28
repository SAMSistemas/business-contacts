package com.jonisaa.sugarsample.controller.base;

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
    T findById(@NonNull Long id);

    @Nullable
    List<T> listAll();

    @NonNull
    Long update(@NonNull T object);

    @NonNull
    Boolean delete(@NonNull T object);

    int deleteAll();

    long getCount();
}
