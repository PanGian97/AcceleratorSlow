package com.example.accelerator.model;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class testPattern implements ΙtestPattern {


    double xAxis;
    double yAxis;
    double zAxis;

    public testPattern(double xAxis,double yAxis,double zAxis){
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.zAxis = zAxis;
    }
@Override
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



    public double getxAxis() {
        return xAxis;
    }


    public double getyAxis() {
        return yAxis;
    }


    public double getzAxis() {
        return zAxis;
    }



}
