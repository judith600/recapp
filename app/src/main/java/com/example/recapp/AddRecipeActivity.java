package com.example.recapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.recapp.db.AppDataBase;
import com.example.recapp.db.InsertAsyncTask;
import com.google.common.util.concurrent.ListenableFuture;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class AddRecipeActivity extends AppCompatActivity {

    private static final String TAG = AddRecipeActivity.class.getSimpleName();
    private AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDataBase = AppDataBase.getAppDatabaseInstance(getApplicationContext());
        setContentView(R.layout.activity_add_recipe);
    }


    public void saveRecipeAndNavigateBack(View view) {
        Recipe recipe = createNewRecipe();
        Log.d(TAG, "Inserting recipe into database with following name: " + recipe.getName());
        try {
            Integer resultSize = new InsertAsyncTask(appDataBase.recipeDAO()).execute(recipe).get();
            Log.d(TAG, "Added recipes: " + resultSize);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readDb();
        navigateUpTo(NavUtils.getParentActivityIntent(this));
    }

    public void readDb() {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            List<Recipe> recipeList = appDataBase.recipeDAO().getAll();
            Log.d(TAG, "Recipes in db: "+ recipeList.size());
        });
    }

    public Recipe createNewRecipe() {
        String newRecipeNameText = ((EditText) findViewById(R.id.newRecipeName)).getText().toString();
        Optional<URI> uriOptional = getURI();
        Recipe recipe;
        if (uriOptional.isPresent()) {
            recipe = new Recipe(newRecipeNameText, uriOptional.get());
            Log.d(TAG, "Name: " + recipe.getName());
        } else {
            recipe = new Recipe(newRecipeNameText);
            Log.d(TAG, recipe.getName());
        }
        return recipe;
    }

    private Optional<URI> getURI() {
        EditText newRecipeUrl = findViewById(R.id.newRecipeUrl);
        if (newRecipeUrl.getText() == null || newRecipeUrl.getText().toString().equals("")) {
            return Optional.empty();
        }
        try {
            return Optional.of(new URI(newRecipeUrl.getText().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}