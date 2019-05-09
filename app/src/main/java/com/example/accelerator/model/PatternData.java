package com.example.accelerator.model;

import android.content.Context;

import java.util.ArrayList;

public interface PatternData {
    ArrayList patternRetriever(Context context, ArrayList<Integer> savedList);
    void saveToSharedPreferences( Context context ,ArrayList<Integer> patternToBeSaved);
}
