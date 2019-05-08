package com.example.accelerator.model;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class testPattern {



    public void patternRetriever(ArrayList <Integer> savedList){
        SharedPreferences sharedPreferences = getSharedPreferences("saved pattern",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("pattern list",null);
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        savedList = gson.fromJson(json,type);

        if(savedList == null){

            savedList = new ArrayList<>();
        }
    }

}
