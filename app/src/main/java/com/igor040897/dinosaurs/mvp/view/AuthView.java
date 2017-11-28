package com.igor040897.dinosaurs.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AuthView extends MvpView {
    void showProgress();

    void hideProgress();

    void showError(Throwable exception);

    void hideError();

    @StateStrategyType(SingleStateStrategy.class)
    void onSignIn();

    void activityFinish();
}
