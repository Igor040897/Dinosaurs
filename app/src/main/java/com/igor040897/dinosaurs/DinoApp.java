package com.igor040897.dinosaurs;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.text.TextUtils;

import com.igor040897.dinosaurs.API.Result.AuthResult;
import com.igor040897.dinosaurs.di.components.ApplicationComponent;
import com.igor040897.dinosaurs.di.components.DaggerApplicationComponent;
import com.igor040897.dinosaurs.di.module.DinoApiModule;
import com.igor040897.dinosaurs.di.module.ContextModule;
import com.igor040897.dinosaurs.mvp.model.db.DatabaseHelper;

public class DinoApp extends Application /*implements HasActivityInjector*/ {
    private static ApplicationComponent applicationComponent;
    private static DatabaseHelper db;
//    @Inject
//    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private static final String PREFERENCES_SESSION = "session";
    private static final String KEY_AUTH_TOKEN = "register-authToken";

    private static final String KEY_SESSION_ID = "register-session_id";
    private static final String KEY_SESSION_NAME = "register-session_name";

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .dinoApiModule(new DinoApiModule())
                .contextModule(new ContextModule(this))
                .build();

        db = Room.databaseBuilder(getApplicationContext(), DatabaseHelper.class, "date-dinos")
                .allowMainThreadQueries()
                .build();
    }

    public static DatabaseHelper getDatabaseInstance(){
        return db;
    }

    public static ApplicationComponent component() {
        return applicationComponent;
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

//    @Override
//    public AndroidInjector<Activity> activityInjector() {
//        return dispatchingAndroidInjector;
//    }
}
