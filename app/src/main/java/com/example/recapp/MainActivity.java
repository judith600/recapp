package com.example.recapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recapp.db.AppDataBase;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int min = 0;
    private AppDataBase appDataBase;
    private List<Recipe> recipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDataBase = AppDataBase.getAppDatabaseInstance(getApplicationContext());
    }

    public void addRecipe(View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);
    }

    public void viewAll(View view) {
        Intent intent = new Intent(this, ViewAllRecipesActivity.class);
        startActivity(intent);
    }

    public void getOtherRecipe(View view) {
        // Intent intent = new Intent(this, RandomRecipeActivity.class);
        // startActivity(intent);
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            // recipeList.clear();
            // recipeList.addAll(appDataBase.recipeDAO().getAll());
            appDataBase.recipeDAO().getAll()
                    .stream()
                    .filter(rec -> !recipeList.contains(rec))
                    .forEach(rec -> recipeList.add(rec));
            Log.d(TAG, "Recipes in db: " + recipeList.size());
        });
        TextView randomRecipeName = findViewById(R.id.randomRecipeName);
        TextView randomRecipeUrl = findViewById(R.id.randomRecipeUrl);
        randomRecipeName.setText("");
        randomRecipeUrl.setText("");
        if (recipeList != null && recipeList.size() > 0) {
            int max = recipeList.size();
            int randomNum = ThreadLocalRandom.current().nextInt(min, max);
            Recipe randomRecipe = recipeList.get(randomNum);
            randomRecipeName.setText(randomRecipe.getName());
            if (randomRecipe.getUri() != null) {
                randomRecipeUrl.setText(recipeList.get(randomNum).getUri().toString());
            }
        }
    }
}