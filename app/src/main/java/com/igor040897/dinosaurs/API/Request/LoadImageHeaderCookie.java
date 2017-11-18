package com.igor040897.dinosaurs.API.Request;

import com.igor040897.dinosaurs.API.LSApi;

/**
 * Created by fanre on 11/6/2017.
 */

public class LoadImageHeaderCookie {
    private String session_name;
    private String session_id;

    public LoadImageHeaderCookie(String session_name, String session_id) {
        this.session_name = session_name;
        this.session_id = session_id;
    }

    @Override
    public String toString() {
        return session_name + "=" + session_id;
    }
}
