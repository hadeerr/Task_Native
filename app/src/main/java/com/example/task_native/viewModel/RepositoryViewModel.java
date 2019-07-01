package com.example.task_native.viewModel;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
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

    public  User user;
     Context context;
    public RepositoryViewModel(Context context , RecyclerAdapter.OnLoadMoreListener onLoadMoreListener , int res) {
        repositoryList =  new ArrayList<>();

        this.context = context;
        if(adapter == null)
            adapter = new RecyclerAdapter(context ,onLoadMoreListener);

        if(res == 1){
            getRepoList();
        }else {
            GetUserRepo();
        }


    }


    public void getRepoList(){
        System.out.println("REPOCALLED ");
        ApiClient.getRepoList(100, MainActivity.pageNumber, new ResponseData() {
            @Override
            public void onResponse(ReturnedObject object) {
                if(object != null) {
                    repositoryList = new ArrayList<>();
                    repositoryList = object.getItems();
                    adapter.setData(repositoryList);
                    adapter.notifyDataSetChanged();
                    MainActivity.getInstance().setStatus(true);
                    MainActivity.pageNumber++;

                }
            }

            @Override
            public void onResponseUserList(List<Repository> repositoryList) {

            }

            @Override
            public void onError(String message) {
                MainActivity.getInstance().setStatus(true);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();




            }


        });
    }
    public void GetUserRepo()
    {

        System.out.println("USERREPOCALL");
        ApiClient.getUserRepos(MainActivity.name, "repos", new ResponseData() {
            @Override
            public void onResponse(ReturnedObject object) {

            }

            @Override
            public void onResponseUserList(List<Repository> repositoryList) {
                repositoryList = new ArrayList<>();
                repositoryList.addAll(repositoryList);
                adapter.setData(repositoryList);
                adapter.notifyDataSetChanged();
                MainActivity.getInstance().setStatus(true);

            }

            @Override
            public void onError(String message) {
                MainActivity.getInstance().setStatus(true);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public  void GotoRepoDetail(Repository repo){
        Intent inent = new Intent(context , RepoDetailActivity.class);
        inent.putExtra("ser" , repo);
        context.startActivity(inent);

    }

    public  void GotoUserDetail(User user){
        Intent inent = new Intent(context , UserDetailActivity.class);
        inent.putExtra("user" , user);
        context.startActivity(inent);


    }






}
