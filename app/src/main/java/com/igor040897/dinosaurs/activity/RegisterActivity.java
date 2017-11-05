package com.igor040897.dinosaurs.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.igor040897.dinosaurs.API.Request.RegisterRequest;
import com.igor040897.dinosaurs.API.Result.RegisterResult;
import com.igor040897.dinosaurs.LSApp;
import com.igor040897.dinosaurs.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fanre on 10/29/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    EditText name, number, email;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LSApp) getApplicationContext()).api()
                        .register(new RegisterRequest(name.getText().toString(), email.getText().toString(), number.getText().toString()))
                        .enqueue(new Callback<RegisterResult>() {
                            @Override
                            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                                if (response.isSuccessful()) {
                                    response.body();
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<RegisterResult> call, Throwable t) {
                                showErrorRegister();
                            }
                        });
            }
        });
    }

    private void showErrorRegister() {
        Toast.makeText(this, "Don't register", Toast.LENGTH_LONG).show();
    }
}
