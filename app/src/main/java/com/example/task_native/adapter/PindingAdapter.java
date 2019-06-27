package com.example.task_native.adapter;

import android.arch.persistence.room.OnConflictStrategy;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;


public class PindingAdapter {



    @BindingAdapter({"app:setAdapter"})
    public static void SetAdapter(RecyclerView recyclerView , RecyclerAdapter adapter){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("app:setImage")
    public static void SetImage(CircleImageView imageView , String image_url){
        Glide.with(imageView.getContext()).load(image_url).into(imageView);
    }

    @BindingAdapter("app:SearchQueries")
    public static void Search(SearchView searchView , RecyclerAdapter adapter){


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
//                AppDatabase.getInstance(searchView.getContext()).searchQueryDAO().addSearchQuery(new SearchQuery(s));


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.getFilter().filter("");
//                adapter.notifyDataSetChanged();
                System.out.println("Close");
                return false;
            }
        });

    }

}
