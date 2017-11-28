package com.igor040897.dinosaurs.di.components;

import android.content.Context;

import com.igor040897.dinosaurs.API.DinoApi;
import com.igor040897.dinosaurs.DinoApp;
import com.igor040897.dinosaurs.di.module.DinoApiModule;
import com.igor040897.dinosaurs.di.module.ContextModule;
import com.igor040897.dinosaurs.mvp.presenter.AuthPresenter;
import com.igor040897.dinosaurs.ui.activity.AddItemActivity;
import com.igor040897.dinosaurs.ui.activity.AuthActivity;
import com.igor040897.dinosaurs.ui.activity.RegisterActivity;
import com.igor040897.dinosaurs.ui.fragment.ItemsFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by fanre on 11/16/2017.
 */

@Singleton
@Component(modules = {/*AndroidInjectionModule.class,*/ DinoApiModule.class, ContextModule.class})
public interface ApplicationComponent extends AndroidInjector<DinoApp> {

    Context getContext();
    DinoApi getDinoApi();

    void inject(AuthPresenter authPresenter);
    void inject(AuthActivity authActivity);
    void inject(RegisterActivity registerActivity);
    void inject(AddItemActivity addItemActivity);
    void inject(ItemsFragment itemsFragment);
//    void inject(DinoApp lsApp);

//    @Component.Builder
//    abstract class Builder extends AndroidInjector.Builder<DinoApp> {
//    }

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        Builder application(DinoApp application);
//
//        ApplicationComponent build();
//    }

}