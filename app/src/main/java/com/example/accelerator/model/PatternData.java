package com.example.accelerator.model;

import android.content.Context;

import java.util.ArrayList;

public interface PatternData {
    ArrayList<Integer> patternRetriever(Context context);
    void saveToSharedPreferences( Context context ,ArrayList<Integer> patternToBeSaved);
    void saveToSharedPreferencesDefault(Context context);
}
