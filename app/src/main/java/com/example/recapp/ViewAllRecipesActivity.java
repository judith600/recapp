package com.example.recapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ViewAllRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_recipes);
        updateView();
    }

    private void updateView() {
        List<Recipe> recipeList = new ArrayList<Recipe>();

    }
}