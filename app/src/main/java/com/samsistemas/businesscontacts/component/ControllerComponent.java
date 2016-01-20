package com.samsistemas.businesscontacts.component;

import com.samsistemas.businesscontacts.module.ControllerModule;
import com.samsistemas.businesscontacts.controller.base.Controller;
import com.samsistemas.businesscontacts.model.Contact;

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
