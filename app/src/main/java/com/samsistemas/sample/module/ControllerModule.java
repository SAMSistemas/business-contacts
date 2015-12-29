package com.samsistemas.sample.module;

import com.samsistemas.sample.controller.ControllerImpl;
import com.samsistemas.sample.controller.base.Controller;
import com.samsistemas.sample.model.Person;

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
