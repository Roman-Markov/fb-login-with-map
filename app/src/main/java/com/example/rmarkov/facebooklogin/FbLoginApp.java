package com.example.rmarkov.facebooklogin;

import android.app.Application;

import com.example.rmarkov.facebooklogin.di.components.AppComponent;
import com.example.rmarkov.facebooklogin.di.components.DaggerAppComponent;
import com.example.rmarkov.facebooklogin.di.modules.AppModule;

public class FbLoginApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        super.onCreate();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) throw new IllegalStateException("App component is not initialized yet");
        return appComponent;
    }
}
