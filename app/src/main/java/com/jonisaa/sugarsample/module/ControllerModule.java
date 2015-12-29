package com.jonisaa.sugarsample.module;

import com.jonisaa.sugarsample.controller.ControllerImpl;
import com.jonisaa.sugarsample.controller.base.Controller;
import com.jonisaa.sugarsample.model.Person;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jonatan.salas
 */
@Module
public class ControllerModule {

    @Provides @Singleton
    Controller<Person> providePersonController() {
        return new ControllerImpl<>();
    }
}
