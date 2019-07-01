package com.example.task_native.remote;

import com.example.task_native.model.Repository;
import com.example.task_native.model.ReturnedObject;
import com.example.task_native.model.User;

import java.util.List;

public interface ResponseData {

    public void onResponse(ReturnedObject object);
    public void onResponseUserList(List<Repository> repositoryList);
    void onError(String message);

}
