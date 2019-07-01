package com.example.task_native.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.task_native.R;
import com.example.task_native.databinding.ActivityUserDetailBinding;
import com.example.task_native.model.User;

public class UserDetailActivity extends AppCompatActivity {


    ActivityUserDetailBinding binding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_user_detail);
        if(getIntent().hasExtra("user")){
            user =(User)getIntent().getParcelableExtra("user");
           binding.setUsermodel(user);

        }
    }

    public void gotomain(View view) {
        MainActivity.number =2 ;
        MainActivity.name = user.getName();
        startActivity(new Intent(this , MainActivity.class));
    }
}
