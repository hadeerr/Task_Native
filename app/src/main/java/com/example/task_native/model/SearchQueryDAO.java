package com.example.task_native.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SearchQueryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addSearchQuery(SearchQuery query);


    @Query("select * from SearchQuery")
    public List<SearchQuery> getAllSearchQueries();
}
