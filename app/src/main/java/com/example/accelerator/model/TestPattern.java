package com.example.accelerator.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class TestPattern {



    public void patternRetriever(Context context,ArrayList <Integer> savedList){
        SharedPreferences sharedPreferences = context.getSharedPreferences("saved pattern",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("pattern list",null);
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        savedList = gson.fromJson(json,type);

        if(savedList == null){

            savedList = new ArrayList<>();
        }
    }
    public void saveToSharedPreferences( Context context ,ArrayList<Integer> patternToBeSaved) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("saved pattern",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(patternToBeSaved); // was newPattern
        editor.putString("pattern list",json);
        editor.apply();
    }

}
