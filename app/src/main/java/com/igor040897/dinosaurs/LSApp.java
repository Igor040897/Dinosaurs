package com.igor040897.dinosaurs;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;

import com.igor040897.dinosaurs.API.LSApi;
import com.igor040897.dinosaurs.API.Result.AuthResult;
import com.igor040897.dinosaurs.di.components.DaggerApplicationComponent;

import java.io.IOException;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fanre on 6/30/2017.
 */

public class LSApp extends Application implements HasActivityInjector{
    @Inject
    Retrofit retrofit;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
//    private ApplicationComponent applicationComponents;

    private static final String PREFERENCES_SESSION = "session";
    private static final String KEY_AUTH_TOKEN = "register-authToken";

    private static final String KEY_SESSION_ID = "register-session_id";
    private static final String KEY_SESSION_NAME = "register-session_name";

    private LSApi api;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerApplicationComponent.create().inject(this);

        api = retrofit.create(LSApi.class);
    }

    public LSApi api() {
        return api;
    }

    public void setAuthDate(AuthResult authDate) {
        getSharedPreferences(PREFERENCES_SESSION, MODE_PRIVATE).edit().putString(KEY_AUTH_TOKEN, authDate.getAuthToken()).apply();
        getSharedPreferences(PREFERENCES_SESSION, MODE_PRIVATE).edit().putString(KEY_SESSION_ID, authDate.getSessid()).apply();
        getSharedPreferences(PREFERENCES_SESSION, MODE_PRIVATE).edit().putString(KEY_SESSION_NAME, authDate.getSession_name()).apply();
    }

    public String getAuthToken() {
        return getSharedPreferences(PREFERENCES_SESSION, MODE_PRIVATE).getString(KEY_AUTH_TOKEN, "");
    }

    public String getAuthSessionName() {
        return getSharedPreferences(PREFERENCES_SESSION, MODE_PRIVATE).getString(KEY_SESSION_NAME, "");
    }

    public String getAuthSessionId() {
        return getSharedPreferences(PREFERENCES_SESSION, MODE_PRIVATE).getString(KEY_SESSION_ID, "");
    }

    public boolean isLoggedIn() {
        return !TextUtils.isEmpty(getAuthToken());
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private class AuthInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            HttpUrl url = originalRequest.url().newBuilder().addQueryParameter("register-authToken", getAuthToken()).build();
            return chain.proceed(originalRequest.newBuilder().url(url).build());
        }
    }
}
