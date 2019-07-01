package com.example.task_native.view;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.task_native.R;
import com.example.task_native.adapter.RecyclerAdapter;
import com.example.task_native.databinding.ActivityMainBinding;
import com.example.task_native.model.AppDatabase;
import com.example.task_native.model.Repository;
import com.example.task_native.model.ReturnedObject;
import com.example.task_native.model.User;
import com.example.task_native.remote.ApiClient;
import com.example.task_native.remote.ResponseData;
import com.example.task_native.viewModel.RepositoryViewModel;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnLoadMoreListener {



    RepositoryViewModel  viewModel;
    ActivityMainBinding binding;
   static MainActivity instance;
      boolean value;
      AppDatabase database;
    public static int pageNumber=1;
    public static int number = 1;
    public static String name ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = AppDatabase.getInstance(this);

        ButterKnife.bind(this);
        instance = this;
        viewModel = new RepositoryViewModel(MainActivity.this , this::onLoadMore , number) ;
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main);
        binding.setViewmodel(viewModel);
        binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager llManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (dy > 0 && llManager.findLastCompletelyVisibleItemPosition() ==
                        (binding.getViewmodel().adapter.getItemCount() - 2)) {
                    binding.getViewmodel().adapter.showLoading();
                }
            }
        });





    }

    public static MainActivity getInstance(){
        return instance;
    }
    public  void setStatus(boolean value2){
        value = value2;

        binding.progress.setVisibility((value2)? View.GONE:View.VISIBLE);

    }


    @Override
    public void onLoadMore() {

        new AsyncTask<Void, Void, List<Repository>>() {
            @Override
            protected List<Repository> doInBackground(Void... voids) {

                int start =  binding.getViewmodel().adapter.getItemCount() - 1;
                int end = start + 10;
                final List<Repository>[] list = new List[]{new ArrayList<>()};

                if(end<1000) {
                    ApiClient.getRepoList(100, pageNumber, new ResponseData() {
                        @Override
                        public void onResponse(ReturnedObject object) {
                            list[0] = object.getItems();
                            System.out.println("PAGENUMBER " + pageNumber);
                            binding.getViewmodel().adapter.setData(list[0]);
                            binding.getViewmodel().adapter.notifyDataSetChanged();

                            setStatus(true);
                             pageNumber++;

                        }

                        @Override
                        public void onResponseUserList(List<Repository> repositoryList) {

                        }

                        @Override
                        public void onError(String message) {
                            MainActivity.getInstance().setStatus(true);
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        }


                    });
                }

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return list[0];

            }

            @Override
            protected void onPostExecute(List<Repository> items) {
                super.onPostExecute(items);
                binding.getViewmodel().adapter.dismissLoading();
                binding.getViewmodel().adapter.addItemMore(items);
                binding.getViewmodel().adapter.setMore(true);
            }
        }.execute();

    }
}
