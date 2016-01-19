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
     * Method used to insert an object into the SQLite database
     *
     * @param object the object to insert
     * @return the id of the inserted object
     */
    @NonNull
    Long insert(@NonNull T object);

    /**
     *
     * @param clazz
     * @param query
     *
     * @return
     */
    @Nullable
    List<T> search(Class<T> clazz, String query);

    /**
     * Method used to find an object by its id
     *
     * @param clazz the Class of the object to return
     * @param id the id of the object to find
     * @return a T class object instanced
     */
    @Nullable
    T findById(Class<T> clazz, @NonNull Long id);

    /**
     * Method used to list all object in the SQLite Database
     *
     * @param clazz the Class of the objects to retrieve
     * @return a List of objects
     */
    @Nullable
    List<T> listAll(Class<T> clazz);

    /**
     * Method used to update an object in the SQLite database
     *
     * @param object the object to update
     * @return the id of the updated object
     */
    @NonNull
    Long update(@NonNull T object);

    /**
     * Method used to delete a particular object from the database
     *
     * @param object the object to delete
     * @return a boolean value, true if successful, false in other case
     */
    @NonNull
    Boolean delete(@NonNull T object);

    /**
     * Method used to delete all objects contained in a table
     *
     * @param clazz Class of the objects to delete
     * @return int value that specified the count of deleted objects
     */
    int deleteAll(Class<T> clazz);

    /**
     * Method used to get the count of all objects retained in a table
     *
     * @param clazz the Class of the objects to look
     * @return a long that represents the total rows in a table
     */
    long getCount(Class<T> clazz);
}
