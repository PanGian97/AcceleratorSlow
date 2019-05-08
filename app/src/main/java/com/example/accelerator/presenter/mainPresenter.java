package com.example.accelerator.presenter;

import java.util.ArrayList;

public class mainPresenter {


public String showSettedPattern(ArrayList<Integer> aList) {
    StringBuilder patternStringBuilder = new StringBuilder();
    for (Integer s : aList) {
        patternStringBuilder.append(s);
    }
        String settedPatternOutput = patternStringBuilder.toString();
        return settedPatternOutput;
}
    public String showTryPattern(ArrayList<Integer> aList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer s : aList) {
            stringBuilder.append(s);
        }
        String tryPatternOutput = stringBuilder.toString();
        return tryPatternOutput;

    }
   public Boolean patternComparisor(ArrayList <Integer> tryList,ArrayList <Integer> settedList){
       Boolean state =false;
       boolean isPatterEqual = tryList.equals(settedList);
         if(isPatterEqual == true){state=true;}
          return state;
    }

    public void patternAdder(ArrayList <Integer> aList,int sensorValue){
        if(aList.size()>=1){
            if((aList.get(aList.size() - 1)) != sensorValue) {
                aList.add(sensorValue);
            }
        }else aList.add(sensorValue);
    }
    public int tiltAxisSymbolicNumber(double xAxis,double yAxis,double zAxis){
        int symbolicValue=0;
        if(xAxis>=9 ){
            if(Math.abs(xAxis)>(Math.abs( yAxis)) && (Math.abs(xAxis)>Math.abs( zAxis))){ symbolicValue=1; }}
        if(yAxis>=7){
            if(Math.abs(yAxis)>(Math.abs( xAxis)) && (Math.abs(yAxis)>Math.abs( zAxis))){ symbolicValue=2;}}
        if(zAxis>=9 ){
            if(Math.abs(zAxis)>(Math.abs( xAxis)) && (Math.abs(zAxis)>Math.abs( yAxis))){ symbolicValue=3; }}

        if(xAxis<=-9 ){
            if(Math.abs(xAxis)>(Math.abs( yAxis)) && (Math.abs(xAxis)>Math.abs( zAxis))){ symbolicValue=-1; }}
        if(yAxis<=-7){
            if(Math.abs(yAxis)>(Math.abs( xAxis)) && (Math.abs(yAxis)>Math.abs( zAxis))){ symbolicValue=-2;}}
        if(zAxis<=-9 ){
            if(Math.abs(zAxis)>(Math.abs( xAxis)) && (Math.abs(zAxis)>Math.abs( yAxis))){ symbolicValue=-3;}}
        return symbolicValue;
    }
}
