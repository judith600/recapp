package com.example.recapp.db;

import android.os.AsyncTask;

import com.example.recapp.Recipe;

public class InsertAsyncTask extends AsyncTask<Recipe, Void, Integer> {

    private final RecipeDAO dao;

    public InsertAsyncTask(RecipeDAO dao) {
        this.dao = dao;
    }

    @Override
    protected Integer doInBackground(Recipe... recipes) {
        return (int) this.dao.insertRecipe(recipes[0]);
    }
}
