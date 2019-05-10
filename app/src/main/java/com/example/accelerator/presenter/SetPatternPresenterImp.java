package com.example.accelerator.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.accelerator.MainActivity;
import com.example.accelerator.SetPatternActivity;
import com.example.accelerator.model.PatternData;
import com.example.accelerator.model.PatternDataImp;
import com.example.accelerator.view.SetPatternView;

import java.util.ArrayList;

public class SetPatternPresenterImp implements SetPatternPresenter{

    private SetPatternView setPatternView;
    private PatternData patternData;
    private Context context;

    private ArrayList<Integer> newPatternList = new ArrayList<>();
    private ArrayList<Integer> patternList = new ArrayList<>();
    private double xValue = 0;
    private double yValue = 0;
    private double zValue = 0;
public SetPatternPresenterImp(SetPatternView setPatternView, Context context){
  this.setPatternView=setPatternView;
  this.context = context;


}
    public void startButtonTasks(){
        newPatternList.clear();
        patternList.clear();
        setPatternView.startButtonFunction();
}
    public void setButtonTasks() {
        patternList.addAll(newPatternList);
        setPatternView.setButtonFunction();

        patternData.saveToSharedPreferences(context,newPatternList);

    }
    @Override
    public void onSensorChanged(double xValue, double yValue, double zValue){

        this.xValue = xValue;
        this.yValue = yValue;
        this.zValue = zValue;

    }
    public void tiltAxisSymbolicNumberToBeSaved(){
        int symbolicValue=0;

        if(xValue>=7){symbolicValue=1;}
        if(yValue>=9){symbolicValue=2;}
        if(zValue>=9){symbolicValue=3;}

        if(xValue<=-7){symbolicValue=-1;}
        if(yValue<=-9){symbolicValue=-2;}
        if(zValue<=-9){symbolicValue=-3;}

        patternAdder(symbolicValue);
        }
    public void patternAdder(int sensorValue){//called in this class by tiltAxisSymbolicToBeSaved
        if(newPatternList.size()>=1){
            if((newPatternList.get(newPatternList.size() - 1)) != sensorValue) {
                newPatternList.add(sensorValue);
            }
        }else newPatternList.add(sensorValue);
    }

    public void convTypingPattern() {
        StringBuilder stringBuilder = new StringBuilder();
        if (newPatternList.size() > 0) {
            for (Integer s : newPatternList) {
                stringBuilder.append(s);
            }
        } setPatternView.showTypingPattern(stringBuilder.toString());

    }
    @Override
    public void savePatternToSharedPref(){
        if(patternList == null){

            patternList = new ArrayList<>();
        }
           patternData.saveToSharedPreferences(context,patternList);
    }

}

