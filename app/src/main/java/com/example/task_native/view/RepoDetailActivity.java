package com.example.task_native.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.task_native.R;
import com.example.task_native.adapter.RecyclerAdapter;
import com.example.task_native.databinding.ActivityRepoDetailBinding;
import com.example.task_native.model.Repository;
import com.example.task_native.viewModel.RepoDetailViewModel;
import com.example.task_native.viewModel.RepositoryViewModel;

public class RepoDetailActivity extends AppCompatActivity  {




    ActivityRepoDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_repo_detail);

            if (getIntent().hasExtra("ser")){
               Repository repo =  getIntent().getParcelableExtra("ser");
                binding.setRepomodel(repo);
                binding.setViewmodel(new RepositoryViewModel(RepoDetailActivity.this , null));
            }
    }






}
