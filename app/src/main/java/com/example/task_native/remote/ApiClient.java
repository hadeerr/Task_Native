package com.example.task_native.remote;
import com.example.task_native.model.ReturnedObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static Retrofit retrofit = null;

    static public ApiInterface getClient() {



        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiInterface apiInterface =  retrofit.create(ApiInterface.class);

        return apiInterface;
    }


    public static void getRepoList(int perpage , int page , ResponseData responseData){
         ApiClient.getClient().getPosts(perpage , page).enqueue(new Callback<ReturnedObject>() {
            @Override
            public void onResponse(Call<ReturnedObject> call, Response<ReturnedObject> response) {
             responseData.onResponse(response.body());
            }
            @Override
            public void onFailure(Call<ReturnedObject> call, Throwable t) {
                System.out.println("ERROR "+ t.getMessage());

            }
        });


    }




}
