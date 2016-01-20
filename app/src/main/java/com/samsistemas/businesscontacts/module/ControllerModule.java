package com.samsistemas.businesscontacts.module;

import com.samsistemas.businesscontacts.controller.ControllerImpl;
import com.samsistemas.businesscontacts.controller.base.Controller;
import com.samsistemas.businesscontacts.model.Contact;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module used by Dagger2 to provide Application dependencies
 *
 * @author jonatan.salas
 */
@Module
public class ControllerModule {

    /**
     * Method that provides a Singleton instance of Controller<Contact> class
     *
     * @return a singleton instance
     */
    @Provides @Singleton
    Controller<Contact> providePersonController() {
        return new ControllerImpl<>();
    }
}
