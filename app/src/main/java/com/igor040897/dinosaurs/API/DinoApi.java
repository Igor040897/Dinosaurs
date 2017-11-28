package com.igor040897.dinosaurs.API;


import com.igor040897.dinosaurs.API.Request.AddDinoRequest;
import com.igor040897.dinosaurs.API.Request.AuthRequest;
import com.igor040897.dinosaurs.API.Request.LoadImageRequest;
import com.igor040897.dinosaurs.API.Request.RegisterRequest;
import com.igor040897.dinosaurs.API.Result.AddResult;
import com.igor040897.dinosaurs.API.Result.AuthResult;
import com.igor040897.dinosaurs.API.Result.DinosResult;
import com.igor040897.dinosaurs.API.Result.LoadImageResult;
import com.igor040897.dinosaurs.API.Result.RegisterResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by fanre on 6/29/2017.
 */

public interface DinoApi {

    @Headers({"Content-Type: application/json", "Accepts:application/json"})
    @GET("rest/dinos")
    Observable<DinosResult> dinos();

    @Headers({"Content-Type: application/json", "Accepts:application/json"})
    @POST("rest/node")
    Observable<AddResult> add(@Body AddDinoRequest addDinoRequest,
                        @Header("X-CSRF-Token") String token,
                        @Header("Cookie") String session_name_and_id);

    @Headers({"Content-Type: application/json", "Accepts:application/json"})
    @POST("rest/user")
    Observable<RegisterResult> register(@Body RegisterRequest register);

    @Headers({"Content-Type: application/json", "Accepts:application/json"})
    @POST("rest/user/login")
    Observable<AuthResult> login(@Body AuthRequest auth);

    @Headers({"Content-Type: application/json", "Accepts: application/json",
            "X-CSRF-Token: [token]", "Cookie: [session_name]=[session_id]"})
    @GET("rest/user/logout")
    Observable<Boolean> logout(@Query("social_user_id") String socialUserId);

    @Headers({"Content-Type: application/json", "Accepts:application/json"})
    @POST("rest/file")
    Observable<LoadImageResult> loadImage(@Body LoadImageRequest register,
                                    @Header("X-CSRF-Token") String token,
                                    @Header("Cookie") String session_name_and_id
    );
}
