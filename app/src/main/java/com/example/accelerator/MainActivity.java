package com.example.accelerator;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    ArrayList<Integer> pattern = new ArrayList<>(3);
    ArrayList<Integer> tryPattern = new ArrayList<>();
    private SensorManager sensorManager;
    private double gravity[] = new double[]{ 0, 0, 0 };
    private double linear_acceleration[] = new double[]{ 0, 0, 0};
    private int counter=0;
    Sensor accelometer;
    TextView xView,yView,zView,pattern_value;
    View colorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pattern.add(2);
        pattern.add(-2);
        pattern.add(1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Initializing sensor Services");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelometer  =sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener( this,accelometer,SensorManager.SENSOR_STATUS_ACCURACY_HIGH);

        xView = (TextView)findViewById(R.id.xView);
        yView = (TextView)findViewById(R.id.yView);
        zView = (TextView)findViewById(R.id.zView);
       pattern_value = (TextView)findViewById(R.id.pattern_value);
        colorView = (View)findViewById(R.id.view);
        Log.d(TAG, "onCreate:Registered Accelometer listener");

        colorView.setBackgroundColor(Color.YELLOW);
    }
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.d(TAG, "onSensorChanged: X: "+ event.values[0] +"Y: "+ event.values[1]+"Z: "+ event.values[2]);

        final double alpha = 0.8;

        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];
//        double xValue = linear_acceleration[0];
//        double yValue = linear_acceleration[1];
//        double zValue = linear_acceleration[2];

        double xValue = event.values[0];
        double yValue = event.values[1];
        double zValue = event.values[2];

if(xValue>=9 ){
         if(Math.abs(xValue)>(Math.abs( yValue)) && (Math.abs(xValue)>Math.abs( zValue))){ patternAdder(1);counter++; }}
if(yValue>=7){
         if(Math.abs(yValue)>(Math.abs( xValue)) && (Math.abs(yValue)>Math.abs( zValue))){ patternAdder(2);counter++;}}
if(zValue>=9 ){
         if(Math.abs(zValue)>(Math.abs( xValue)) && (Math.abs(zValue)>Math.abs( yValue))){ patternAdder(3); counter++;}}

        if(xValue<=-9 ){
            if(Math.abs(xValue)>(Math.abs( yValue)) && (Math.abs(xValue)>Math.abs( zValue))){ patternAdder(-1);counter++; }}
        if(yValue<=-7){
            if(Math.abs(yValue)>(Math.abs( xValue)) && (Math.abs(yValue)>Math.abs( zValue))){ patternAdder(-2);counter++;}}
        if(zValue<=-9 ){
            if(Math.abs(zValue)>(Math.abs( xValue)) && (Math.abs(zValue)>Math.abs( yValue))){ patternAdder(-3); counter++;}}



        StringBuilder stringBuilder = new StringBuilder();
        for (Integer s : tryPattern) {
            stringBuilder.append(s );
        }
        pattern_value.setText(stringBuilder.toString());

        boolean isPatterEqual = tryPattern.equals(pattern);

         if(isPatterEqual == true){

        colorView.setBackgroundColor(Color.GREEN);}

        xView.setText("X: "+ xValue);
        yView.setText("Y: "+ yValue);
        zView.setText("Z: "+ zValue);
// ONLY FOR ACCELERATOR APP
//        if(counter==(pattern.size()) *3){
//            tryPattern.clear();
//            colorView.setBackgroundColor(Color.RED);
//            counter=0;
//        }
        if(tryPattern.size()==(pattern.size()) *3){
            tryPattern.clear();
            colorView.setBackgroundColor(Color.RED);
            //counter=0;
        }
}
public void patternAdder(int sensorValue){
        if(tryPattern.size()>=1){
        if((tryPattern.get(tryPattern.size() - 1)) != sensorValue) {
            tryPattern.add(sensorValue);
        }
    }else tryPattern.add(sensorValue);
}
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
//        if(xValue<= 1 && xValue >=-1){
//            colorView.setBackgroundColor(Color.BLUE);
//        }
//        else if(yValue<= 1 && yValue >=-1){
//            colorView.setBackgroundColor(Color.RED);
//        }
//        else if(xValue<= 1 && xValue >=-1){
//            colorView.setBackgroundColor(Color.GREEN);
//        }
//        else  colorView.setBackgroundColor(Color.BLACK);

//          if((xValue>= 4 || yValue>=4 ||zValue>=4) || (xValue<= -4 || yValue<=-4 ||zValue<=-4)){
//            colorView.setBackgroundColor(Color.RED);
//        }
//       else if((xValue>= 2|| yValue>= 2 || zValue>=2) || (xValue<= -2|| yValue<= -2 || zValue<=-2)){
//           colorView.setBackgroundColor(Color.BLUE);
//       }
//        else colorView.setBackgroundColor(Color.BLACK);

//     if(zValue>=4||zValue<=-4){
//        colorView.setBackgroundColor(Color.RED);
//    }
//       else if(zValue>=2||zValue<=-2){
//        colorView.setBackgroundColor(Color.BLUE);
//    }
//        else colorView.setBackgroundColor(Color.BLACK);