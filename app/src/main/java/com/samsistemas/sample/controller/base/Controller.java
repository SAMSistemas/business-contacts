package com.samsistemas.sample.controller.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Controller interface
 *
 * @author jonatan.salas
 * @param <T> any model class that persist its data in SugarORM
 */
public interface Controller<T> {

    /**
     *
     * @param object
     * @return
     */
    @NonNull
    Long insert(@NonNull T object);

    /**
     *
     * @param clazz
     * @param id
     * @return
     */
    @Nullable
    T findById(Class<T> clazz, @NonNull Long id);

    /**
     *
     * @param clazz
     * @return
     */
    @Nullable
    List<T> listAll(Class<T> clazz);

    /**
     *
     * @param object
     * @return
     */
    @NonNull
    Long update(@NonNull T object);

    /**
     *
     * @param object
     * @return
     */
    @NonNull
    Boolean delete(@NonNull T object);

    /**
     *
     * @param clazz
     * @return
     */
    int deleteAll(Class<T> clazz);

    /**
     *
     * @param clazz
     * @return
     */
    long getCount(Class<T> clazz);
}
