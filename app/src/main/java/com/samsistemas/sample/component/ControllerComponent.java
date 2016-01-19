package com.samsistemas.sample.component;

import com.samsistemas.sample.module.ControllerModule;
import com.samsistemas.sample.controller.base.Controller;
import com.samsistemas.sample.model.Contact;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author jonatan.salas
 */
@Singleton
@Component(modules = { ControllerModule.class })
public interface ControllerComponent {

    /**
     * Method that provides a Contact controller as singleton instance
     *
     * @return a Singleton object
     */
    Controller<Contact> provideContactController();
}
