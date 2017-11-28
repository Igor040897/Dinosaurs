package com.igor040897.dinosaurs.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.igor040897.dinosaurs.API.DinoApi;
import com.igor040897.dinosaurs.DinoApp;
import com.igor040897.dinosaurs.R;
import com.igor040897.dinosaurs.mvp.presenter.AuthPresenter;
import com.igor040897.dinosaurs.mvp.view.AuthView;

import javax.inject.Inject;

public class AuthActivity extends MvpAppCompatActivity implements AuthView {
    private static final int RC_REGISTER = 999;

    @InjectPresenter
    AuthPresenter authPresenter;

    @Inject
    DinoApi api;

    EditText login, password;
    Button auth;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        DinoApp.component().inject(this);

        login = findViewById(R.id.auth_login);
        password = findViewById(R.id.auth_password);
        auth = findViewById(R.id.auth);
        register = findViewById(R.id.auth_register);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                auth.setEnabled(!TextUtils.isEmpty(login.getText()) &&
                        !TextUtils.isEmpty(password.getText()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        login.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);

        register.setOnClickListener(view ->
                startActivityForResult(new Intent(getApplicationContext(), RegisterActivity.class), RC_REGISTER));

        auth.setOnClickListener(view ->
                authPresenter.auth(login.getText().toString(), password.getText().toString()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_REGISTER) {

        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Throwable exception) {
        exception.printStackTrace();
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideError() {

    }

    @Override
    public void onSignIn() {

    }

    @Override
    public void activityFinish() {
        finish();
    }
}