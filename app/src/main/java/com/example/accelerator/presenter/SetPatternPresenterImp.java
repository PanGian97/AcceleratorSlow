package com.example.accelerator.presenter;

import android.content.Intent;
import android.view.View;

import com.example.accelerator.MainActivity;
import com.example.accelerator.SetPatternActivity;

import java.util.ArrayList;

public class SetPatternPresenterImp {


    public void startButtonTasks(){
    setButton.setVisibility(View.VISIBLE);
                newPatternList.clear();
                patternList.clear();
                new_pattern_value.setVisibility(View.VISIBLE);
}
    public void setButtonTasks() {
        patternList.addAll(newPatternList);
        Intent intent = new Intent(SetPatternActivity.this, MainActivity.class);
        intent.putExtra("patternList", patternList);
        saveToSharedPreferences(newPatternList);
        startActivity(intent);
    }
    public int tiltAxisSymbolicNumberToBeSaved(double xAxis,double yAxis,double zAxis){
        int symbolicValue=0;

     if(xAxis>=7){symbolicValue=1;}
        if(yAxis>=9){symbolicValue=2;}
        if(zAxis>=9){symbolicValue=3;}

        if(xAxis<=-7){symbolicValue=-1;}
        if(yAxis<=-9){symbolicValue=-2;}
        if(zAxis<=-9){symbolicValue=-3;}

        return symbolicValue;
        }
    public void patternAdder(int sensorValue, ArrayList<Integer> newList){
        if(newList.size()>=1){
            if((newList.get(newList.size() - 1)) != sensorValue) {
                newList.add(sensorValue);
            }
        }else newList.add(sensorValue);
    }
}

