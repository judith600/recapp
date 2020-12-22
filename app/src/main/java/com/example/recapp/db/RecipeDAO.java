package com.example.recapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.recapp.Recipe;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface RecipeDAO {

    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Query("select * from recipe where name like :name")
    Recipe getByName(String name);

    @Query("select * from recipe where uuid like :id")
    Recipe getById(int id);

    @Insert
    long insertRecipe(Recipe recipe);

    @Delete
    Integer deleteRecipe(Recipe recipe);

}
