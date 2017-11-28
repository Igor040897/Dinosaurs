package com.igor040897.dinosaurs.mvp.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.igor040897.dinosaurs.API.DinoApi;
import com.igor040897.dinosaurs.API.Request.AuthRequest;
import com.igor040897.dinosaurs.DinoApp;
import com.igor040897.dinosaurs.mvp.view.AuthView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView> {
    @Inject
    DinoApi api;

    @Inject
    Context context;

    public AuthPresenter() {
        DinoApp.component().inject(this);
    }

    public void auth(String login, String password) {
//        getViewState().hideError();
        getViewState().showProgress();
        api
                .login(new AuthRequest(login, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        authResult -> {
                            getViewState().hideProgress();
//                            getViewState().onSignIn();
                            ((DinoApp) context).setAuthDate(authResult);
                            getViewState().activityFinish();
                        },
                        throwable -> {
                            getViewState().hideProgress();
                            getViewState().showError(throwable);
                        });
    }
}
