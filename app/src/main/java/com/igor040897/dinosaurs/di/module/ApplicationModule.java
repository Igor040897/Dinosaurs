package com.igor040897.dinosaurs.di.module;

import com.igor040897.dinosaurs.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fanre on 11/16/2017.
 */
@Module
public class ApplicationModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS : HttpLoggingInterceptor.Level.NONE);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient
                .Builder()
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public okhttp3.Response intercept(Chain chain) throws IOException {
//                        Request originalRequest = chain.request();
//                        Request.Builder builder = originalRequest.newBuilder().header("Authorization", Credentials.basic("aUsername", "aPassword"));
//                        Request newRequest = builder.build();
//                        return chain.proceed(newRequest);
//                    }
//                })
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl("http://dinotest.art-coral.com/")
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build();
    }
}
