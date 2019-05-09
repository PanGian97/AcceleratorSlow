package com.example.accelerator.presenter;

import android.content.Context;

import com.example.accelerator.model.PatternData;
import com.example.accelerator.model.PatternDataImp;
import com.example.accelerator.view.MainView;

import java.util.ArrayList;

public class MainPresenterImp implements MainPresenter {

    private MainView mainView;
    private PatternData patternData;
    private Context context;
    private ArrayList<Integer> tryPattern = new ArrayList<>();
    private ArrayList<Integer> patternList = new ArrayList<>();

    private double xValue = 0;
    private double yValue = 0;
    private double zValue = 0;

    public MainPresenterImp(MainView mainView,Context context)
    {
        this.patternData = new PatternDataImp();
        this.mainView = mainView;
        this.context = context;
    }

    @Override
public void onSensorChanged(double xValue, double yValue, double zValue){

    this.xValue = xValue;
    this.yValue = yValue;
    this.zValue = zValue;

}
@Override
public void loadPatternFromSharedPref(){
    patternList = patternData.patternRetriever(context);
}
@Override
public void lockPhoneScreen(){
        mainView.setterUI(0);
        mainView.setterUI(2);
        mainView.setterUI(3);
}
@Override
public void unlockPhoneScreen(){
        mainView.setterUI(1);
    }
  @Override
public String showSettedPattern() {
        loadPatternFromSharedPref();
    StringBuilder patternStringBuilder = new StringBuilder();
    for (Integer s : tryPattern) {
        patternStringBuilder.append(s);
    }
        String settedPatternOutput = patternStringBuilder.toString();
        return settedPatternOutput;
}
@Override
    public String showTryPattern() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer s : tryPattern) {
            stringBuilder.append(s);
        }
        String tryPatternOutput = stringBuilder.toString();
        return tryPatternOutput;

    }
    @Override
   public Boolean patternComparisor(){
       Boolean state =false;
       boolean isPatternEqual = tryPattern.equals(patternList);
         if(isPatternEqual){state=true;}
          return state;
    }
@Override
    public void tryPatternAdder(int sensorValue){
        if(tryPattern.size()>=1){
            if((tryPattern.get(tryPattern.size() - 1)) != sensorValue) {
                tryPattern.add(sensorValue);
            }
        }else tryPattern.add(sensorValue);
    }
    public void tiltAxisSymbolicNumber(){
        int symbolicValue=0;
        if(xValue>=9 ){
            if(Math.abs(xValue)>(Math.abs( yValue)) && (Math.abs(xValue)>Math.abs( zValue))){ symbolicValue=1; }}
        if(yValue>=7){
            if(Math.abs(yValue)>(Math.abs( xValue)) && (Math.abs(yValue)>Math.abs( zValue))){ symbolicValue=2;}}
        if(zValue>=9 ){
            if(Math.abs(zValue)>(Math.abs( xValue)) && (Math.abs(zValue)>Math.abs( yValue))){ symbolicValue=3; }}

        if(xValue<=-9 ){
            if(Math.abs(xValue)>(Math.abs( yValue)) && (Math.abs(xValue)>Math.abs( zValue))){ symbolicValue=-1; }}
        if(yValue<=-7){
            if(Math.abs(yValue)>(Math.abs( xValue)) && (Math.abs(yValue)>Math.abs( zValue))){ symbolicValue=-2;}}
        if(zValue<=-9 ){
            if(Math.abs(zValue)>(Math.abs( xValue)) && (Math.abs(zValue)>Math.abs( yValue))){ symbolicValue=-3;}}
        tryPatternAdder(symbolicValue);
    }
    @Override
    public void setPatternClicked(){
        mainView.navigateToSetPattern();
    }
}
