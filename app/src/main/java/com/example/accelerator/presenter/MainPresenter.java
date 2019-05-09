package com.example.accelerator.presenter;

import com.example.accelerator.view.ImainView;

import java.util.ArrayList;

public class MainPresenter implements ImainPresenter{

    private ImainView imainview;
    ArrayList<Integer> tryPattern = new ArrayList<>();
    ArrayList<Integer> patternList = new ArrayList<>();
    double xValue = 0;
    double yValue = 0;
    double zValue = 0;
    public MainPresenter(ImainView imainView)
    {
        this.imainview = imainView;
    }
    @Override
public void onSensorChanged(double xValue,double yValue,double zValue){

    this.xValue = xValue;
    this.yValue = yValue;
    this.zValue = zValue;

}
  @Override
public String showSettedPattern() {
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
       boolean isPatterEqual = tryPattern.equals(patternList);
         if(isPatterEqual == true){state=true;}
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
        imainview.navigateToSetPattern();
    }
}
