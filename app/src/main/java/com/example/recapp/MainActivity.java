package com.example.recapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Intent intent = new Intent(this, RandomRecipeActivity.class);
        startActivity(intent);
    }
}