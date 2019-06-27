package com.example.task_native.remote;

import com.example.task_native.model.ReturnedObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {




    @GET("repositories?q=retrofit")
    Call<ReturnedObject> getPosts(@Query("per_page") int per_page, @Query("page") int page);
}
