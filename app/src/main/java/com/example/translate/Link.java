package com.example.translate;

import java.util.Map;
import java.util.Objects;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface Link {

    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/translate")
    Call<Object> translate (@FieldMap Map<String, String> map);

   // @GET("/android_login")
   // Call<List<User>> getUsers();

}
