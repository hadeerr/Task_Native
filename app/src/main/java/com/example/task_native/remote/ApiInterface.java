package com.example.task_native.remote;

import com.example.task_native.model.Repository;
import com.example.task_native.model.ReturnedObject;
import com.example.task_native.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {




    @GET("search/repositories?q=retrofit")
    Call<ReturnedObject> getPosts(@Query("per_page") int per_page, @Query("page") int page);


    @GET("users/{name}/repos")
    Call<List<Repository>> getUserRepos(@Query("name") String  name/* , @Query("repos") String repos*/);
}
