package com.jonisaa.sugarsample.component;

import com.jonisaa.sugarsample.module.ControllerModule;
import com.jonisaa.sugarsample.controller.base.Controller;
import com.jonisaa.sugarsample.model.Person;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author jonatan.salas
 */
@Singleton
@Component(modules = { ControllerModule.class })
public interface ControllerComponent {

    Controller<Person> providePersonController();
}
