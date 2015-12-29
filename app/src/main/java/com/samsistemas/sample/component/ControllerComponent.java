package com.samsistemas.sample.component;

import com.samsistemas.sample.module.ControllerModule;
import com.samsistemas.sample.controller.base.Controller;
import com.samsistemas.sample.model.Person;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author jonatan.salas
 */
@Singleton
@Component(modules = { ControllerModule.class } )
public interface ControllerComponent {

    Controller<Person> providePersonController();
}
