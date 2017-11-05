package com.igor040897.dinosaurs.API.Result;

import android.text.TextUtils;

/**
 * Created by fanre on 7/1/2017.
 */

public class Result {
    private String status;

    public boolean isSuccess() {
        return TextUtils.equals(status, "success");
    }
}
