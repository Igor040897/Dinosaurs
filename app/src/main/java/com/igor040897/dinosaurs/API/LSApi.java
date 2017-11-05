package com.igor040897.dinosaurs.API;


import com.igor040897.dinosaurs.API.Request.AddRequest;
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


/**
 * Created by fanre on 6/29/2017.
 */

public interface LSApi {

    @Headers({"Content-Type: application/json", "Accepts:application/json"})
    @GET("rest/dinos")
    Call<DinosResult> dinos();

    @Headers({"Content-Type: application/json", "Accepts:application/json",
            "X-CSRF-Token: [token]", "Cookie: [session_name]=[session_id]"})
    @POST("rest/node")
    Call<AddResult> add(@Body AddRequest addRequest);

    @Headers({"Content-Type: application/json", "Accepts:application/json"})
    @POST("rest/user")
    Call<RegisterResult> register(@Body RegisterRequest register);

    @Headers({"Content-Type: application/json", "Accepts:application/json"})
    @POST("rest/user/login")
    Call<AuthResult> login(@Body AuthRequest auth);

    @Headers({"Content-Type: application/json", "Accepts: application/json",
            "X-CSRF-Token: [token]", "Cookie: [session_name]=[session_id]"})
    @GET("rest/user/logout")
    Call<Boolean> logout(@Query("social_user_id") String socialUserId);

    @Headers({"Content-Type: application/json", "Accepts:application/json",
            "X-CSRF-Token: {token}",
            "Cookie: {session_name}={session_id}"})
    @POST("rest/file")
    Call<LoadImageResult> loadImage(@Body LoadImageRequest register,
//                                  @Path("token") String token,
//                                  @Path("session_name") String session_name,
//                                  @Path("session_id") String session_id,
                                    @Header("X-CSRF-Token") String token
//                                   @HeaderMap("Cookie") Map<String, String> headers
    );
}