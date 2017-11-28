package com.igor040897.dinosaurs.di.module;

import com.igor040897.dinosaurs.API.DinoApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Date: 9/2/2016
 * Time: 18:54
 *
 * @author Artur Artikov
 */
@Module(includes = {RetrofitModule.class})
public class DinoApiModule {

//    public DinoApiModule() {
//    }

    @Provides
//    @Singleton
    public DinoApi provideDinoApi(Retrofit retrofit) {
        return retrofit.create(DinoApi.class);
    }
}
