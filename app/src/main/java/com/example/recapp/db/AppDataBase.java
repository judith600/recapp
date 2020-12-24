package com.example.recapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.recapp.Recipe;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Recipe.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {

    public abstract RecipeDAO recipeDAO();

    private static AppDataBase instance;

    public static AppDataBase getAppDatabaseInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDataBase.class, "recipe-db").build();
            Executors.newSingleThreadExecutor().execute(() -> {
                ArrayList<Recipe> recipeList = new ArrayList<>();
                fillRecipeListStatically(recipeList);
                AppDataBase.instance.recipeDAO().insertAllRecipes(recipeList);
            });
        }
        return instance;
    }

    public static void fillRecipeListStatically(List<Recipe> recipeList) {
        recipeList.add(new Recipe("Linseneintopf", URI.create("")));
        recipeList.add(new Recipe("Linsensuppe", URI.create("")));
        recipeList.add(new Recipe("Linsencurry", URI.create("https://www.chefkoch.de/rezepte/128381055063820/Rote-Linsen-Curry.html")));
        recipeList.add(new Recipe("Kürbissuppe", URI.create("")));
        recipeList.add(new Recipe("Zucchinisuppe", URI.create("")));
        recipeList.add(new Recipe("Selleriesuppe", URI.create("")));
        recipeList.add(new Recipe("Ofengemüse", URI.create("")));
        recipeList.add(new Recipe("(Gemüse-)Lasagne", URI.create("")));
        recipeList.add(new Recipe("Couscoussalat", URI.create("")));
        recipeList.add(new Recipe("Weißkraut", URI.create("")));
        recipeList.add(new Recipe("Kidneybohnen à la Afrika", URI.create("https://blog.kptncook.com/de/2017/09/11/eat-kidneybohnen-la-afrika-mit-nadine-jorg/")));
        recipeList.add(new Recipe("Kohlrouladen mit Linsenfüllung", URI.create("")));
        recipeList.add(new Recipe("Kichererbsen-Erdnusscurry", URI.create("")));
        recipeList.add(new Recipe("Pizza", URI.create("")));
        recipeList.add(new Recipe("Nudeln mit Pilzsoße", URI.create("")));
        recipeList.add(new Recipe("Kürbisquiche", URI.create("")));
        recipeList.add(new Recipe("Kartoffel-Lauch-Suppe", URI.create("")));
        recipeList.add(new Recipe("Ratatouille", URI.create("")));
        recipeList.add(new Recipe("Schwammal-Zucchini-Risotto", URI.create("")));
        recipeList.add(new Recipe("Spitzkohl aus dem Ofen", URI.create("https://www.kochtrotz.de/rezepte/ofen-spitzkohl-so-schmeckt-er-koestlich-fuer-mich-das-perfekte-spitzkohl-gericht/")));
        recipeList.add(new Recipe("Chili sin Carne", URI.create("")));
        recipeList.add(new Recipe("Tomaten-Spinat-Quiche", URI.create("")));
        recipeList.add(new Recipe("Arabischer Blumenkohl", URI.create("https://nikesherztanzt.de/2017/01/15/blumenkohl-mit-kirchererbsen-tahine-granatapfelkernen/")));
        recipeList.add(new Recipe("Italienischer Nudelsalat", URI.create("")));
        recipeList.add(new Recipe("Süßkartoffel-Linsen Salat", URI.create("https://www.daskochrezept.de/rezepte/suesskartoffel-linsen-salat")));
        recipeList.add(new Recipe("Kartoffelgratin", URI.create("")));
        recipeList.add(new Recipe("Tomatenfisch mit Reis", URI.create("")));
        recipeList.add(new Recipe("Käsespätzle", URI.create("")));
        recipeList.add(new Recipe("Schupfnudelgemüsepfanne", URI.create("")));
        recipeList.add(new Recipe("Reissalat", URI.create("https://www.foodtempel.de/bohnensalat-mit-reis-bunter-reissalat-aus-kidneybohnen-und-paprika/")));
        recipeList.add(new Recipe("Maultaschen mit Rührei", URI.create("")));
        recipeList.add(new Recipe("Rinderrouladen", URI.create("https://emmikochteinfach.de/klassische-rinderroulade/")));
        recipeList.add(new Recipe("Knödeltris", URI.create("https://www.bergwelten.com/a/knoedeltris-von-der-toelzer-huette")));
    }
}
