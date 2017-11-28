package com.igor040897.dinosaurs.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.igor040897.dinosaurs.API.DinoApi;
import com.igor040897.dinosaurs.API.Request.RegisterRequest;
import com.igor040897.dinosaurs.DinoApp;
import com.igor040897.dinosaurs.R;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {

    @Inject
    DinoApi api;

    EditText name, number, email;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        DinoApp.component().inject(this);

        name = findViewById(R.id.register_name);
        number = findViewById(R.id.register_number);
        email = findViewById(R.id.register_mail);
        register = findViewById(R.id.register);
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
                register.setEnabled(!TextUtils.isEmpty(name.getText()) &&
                        !TextUtils.isEmpty(number.getText()) &&
                        !TextUtils.isEmpty(email.getText()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        };

        name.addTextChangedListener(textWatcher);
        number.addTextChangedListener(textWatcher);
        email.addTextChangedListener(textWatcher);

        register.setOnClickListener(view -> api
                .register(new RegisterRequest(name.getText().toString(), email.getText().toString(), number.getText().toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        resultRegister -> {
                        },
                        throwable -> showErrorRegister()));
    }

    private void showErrorRegister() {
        Toast.makeText(this, "Don't register", Toast.LENGTH_LONG).show();
    }
}
