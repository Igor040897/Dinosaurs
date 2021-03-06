package com.igor040897.dinosaurs.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.igor040897.dinosaurs.DinoApp;
import com.igor040897.dinosaurs.ui.fragment.ItemsFragment;
import com.igor040897.dinosaurs.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!((DinoApp) getApplication()).isLoggedIn()) {
            startActivity(new Intent(this, AuthActivity.class));
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.values);
            if (fragment == null)
            {
                Fragment itemsFragment = new ItemsFragment();
                getSupportFragmentManager().
                        beginTransaction().
                        add(R.id.values, itemsFragment).
                        commit();
            }
        }
    }
}