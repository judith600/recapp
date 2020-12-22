package com.example.recapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Entity
public class Recipe {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uuid")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "uri")
    private URI uri;

    @Ignore
    private List<String> ingredients;

    @Ignore
    public Recipe(String name) {
        this(name, null, null);
    }

    public Recipe(String name, URI uri) {
        this(name, uri, null);
    }

    @Ignore
    public Recipe(String name, URI uri, List<String> ingredients) {
        this.name = name;
        this.uri = uri;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

}
