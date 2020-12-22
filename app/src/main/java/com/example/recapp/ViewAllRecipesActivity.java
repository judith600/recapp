package com.example.recapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recapp.db.AppDataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ViewAllRecipesActivity extends AppCompatActivity {

    private static final String TAG = ViewAllRecipesActivity.class.getSimpleName();
    private AppDataBase appDataBase;
    private final List<Recipe> recipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_recipes);
        appDataBase = AppDataBase.getAppDatabaseInstance(getApplicationContext());
        updateView();
        RecyclerView listView = findViewById(R.id.rec_list_view);
        RecipeListAdapter adapter = new RecipeListAdapter(recipeList);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void updateView() {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            recipeList.addAll(appDataBase.recipeDAO().getAll());
            Log.d(TAG, "Recipes in db: "+ recipeList.size());
        });
    }
}