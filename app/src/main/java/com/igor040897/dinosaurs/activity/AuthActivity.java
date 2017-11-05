package com.igor040897.dinosaurs.activity;

import android.content.Intent;
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

import com.igor040897.dinosaurs.API.Request.AuthRequest;
import com.igor040897.dinosaurs.API.Result.AuthResult;
import com.igor040897.dinosaurs.LSApp;
import com.igor040897.dinosaurs.R;

import retrofit2.Call;
import retrofit2.Callback;

public class AuthActivity extends AppCompatActivity {
    private static final int RC_REGISTER = 999;

    EditText login, password;
    Button auth;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), RegisterActivity.class), RC_REGISTER);
            }
        });

        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LSApp) getApplicationContext()).api().
                        login(new AuthRequest(login.getText().toString(), password.getText().toString())).
                        enqueue(new Callback<AuthResult>() {
                    @Override
                    public void onResponse(Call<AuthResult> call, retrofit2.Response<AuthResult> response) {
                        if (response.isSuccessful()) {
                            ((LSApp) getApplicationContext()).setAuthDate(response.body());
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthResult> call, Throwable t) {
                        showError();
                    }
                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_REGISTER){

        }
    }

    private void showError() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }

}