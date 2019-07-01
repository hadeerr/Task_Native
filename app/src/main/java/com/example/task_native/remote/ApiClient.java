package com.example.task_native.remote;
import com.example.task_native.model.Repository;
import com.example.task_native.model.ReturnedObject;
import com.example.task_native.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static Retrofit retrofit = null;

    static public ApiInterface getClient() {



        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
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
                responseData.onError(t.getMessage());

            }
        });


    }



    public static void  getUserRepos(String name , String repos , ResponseData responseData){
       ApiClient.getClient().getUserRepos(name ).enqueue(new Callback<List<Repository>>() {
           @Override
           public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
               responseData.onResponseUserList(response.body());
           }

           @Override
           public void onFailure(Call<List<Repository>> call, Throwable t) {
               responseData.onError(t.getMessage());

           }
       });

    }




}
