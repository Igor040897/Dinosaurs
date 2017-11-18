package com.igor040897.dinosaurs.di.components;

import android.content.Context;

import com.igor040897.dinosaurs.LSApp;
import com.igor040897.dinosaurs.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by fanre on 11/16/2017.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class})
public interface ApplicationComponent extends AndroidInjector<LSApp>{
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        Builder context(Context context);
//        ApplicationComponent build();
//    }
//    void inject(LSApp app);
}