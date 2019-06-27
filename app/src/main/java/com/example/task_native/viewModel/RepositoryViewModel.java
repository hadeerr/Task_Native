package com.example.task_native.viewModel;
import android.content.Context;
import android.content.Intent;

import com.example.task_native.adapter.RecyclerAdapter;
import com.example.task_native.model.Repository;
import com.example.task_native.model.ReturnedObject;
import com.example.task_native.model.User;
import com.example.task_native.remote.ApiClient;
import com.example.task_native.remote.ResponseData;
import com.example.task_native.view.MainActivity;
import com.example.task_native.view.RepoDetailActivity;
import com.example.task_native.view.UserDetailActivity;
import java.util.ArrayList;
import java.util.List;




public class RepositoryViewModel   {

    List<Repository> repositoryList ;
    public  RecyclerAdapter adapter;
     Context context;
    public RepositoryViewModel(Context context , RecyclerAdapter.OnLoadMoreListener onLoadMoreListener) {
        repositoryList =  new ArrayList<>();

        this.context = context;
        if(adapter == null)
            adapter = new RecyclerAdapter(context ,onLoadMoreListener);
        ApiClient.getRepoList(100, MainActivity.pageNumber, new ResponseData() {
           @Override
           public void onResponse(ReturnedObject object) {
               if(object != null) {
                   repositoryList = object.getItems();
                   adapter.setData(repositoryList);
                   adapter.notifyDataSetChanged();
                   MainActivity.getInstance().setStatus(true);
                   MainActivity.pageNumber++;


               }
           }


       });

    }
    public  void GotoRepoDetail(Repository repo){

        Intent inent = new Intent(context , RepoDetailActivity.class);

        inent.putExtra("ser" , repo);
        context.startActivity(inent/*new Intent(context, RepoDetailActivity.class)*/);

    }

    public  void GotoUserDetail(User user){
        System.out.println("CALLL");
        Intent inent = new Intent(context , UserDetailActivity.class);
        inent.putExtra("user" , user);
        context.startActivity(inent/*new Intent(context, UserDetailActivity.class)*/);


    }





}
