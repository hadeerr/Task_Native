package com.example.task_native.viewModel;

import android.content.Context;

import com.example.task_native.model.Repository;

public class RepoDetailViewModel {

    public Repository repository;


    public RepoDetailViewModel(Context context , Repository repository) {
        this.repository = repository;
    }
}
