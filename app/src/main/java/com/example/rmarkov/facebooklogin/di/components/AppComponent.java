package com.example.rmarkov.facebooklogin.di.components;

import com.example.rmarkov.facebooklogin.MapFragment;
import com.example.rmarkov.facebooklogin.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {AppModule.class})
public interface AppComponent {

    void inject(MapFragment mapFragment);
}
