package com.example.accelerator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";


    ArrayList<Integer> tryPattern = new ArrayList<>();
    ArrayList<Integer> patternList = new ArrayList<>();
    private SensorManager sensorManager;
    private double gravity[] = new double[]{ 0, 0, 0 };
    private double linear_acceleration[] = new double[]{ 0, 0, 0};
    private int counter=0;//for ACCELERATOR APP
    Sensor accelometer;
    TextView xView,yView,zView,pattern_value,setted_pattern;
    ImageView phoneStateImg;
    Button set_pattern;
    View colorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Initializing sensor Services");

       // if(getIntent().getSerializableExtra("patternList") != null){
        //patternList= (ArrayList<Integer>) getIntent().getSerializableExtra("patternList");}
         patternRetriever();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelometer  =sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener( this,accelometer,SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
//fffffffff

        setted_pattern =(TextView)findViewById(R.id.setted_pattern);
       pattern_value = (TextView)findViewById(R.id.pattern_value);
       set_pattern = (Button)findViewById(R.id.set_pattern);
       phoneStateImg = (ImageView)findViewById(R.id.phone_state_img);
        Log.d(TAG, "onCreate:Registered Accelometer listener");

        set_pattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetPatternActivity.class);
                startActivity(intent);

            }
        });

        phoneStateImg.setBackgroundResource(R.drawable.locked);
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

        StringBuilder patternStringBuilder = new StringBuilder();
        for (Integer s : patternList) {
            patternStringBuilder.append(s );
        } setted_pattern.setText(patternStringBuilder.toString());


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


        boolean isPatterEqual = tryPattern.equals(patternList);

         if(isPatterEqual == true){

        phoneStateImg.setBackgroundResource(R.drawable.unlocked);}


// ONLY FOR ACCELERATOR APP
//        if(counter==(pattern.size()) *3){
//            tryPattern.clear();
//            colorView.setBackgroundColor(Color.RED);
//            counter=0;
//        }
        if(tryPattern.size()==(patternList.size()) *3){
            tryPattern.clear();

            //counter=0;
        }
}
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
public void patternAdder(int sensorValue){
        if(tryPattern.size()>=1){
        if((tryPattern.get(tryPattern.size() - 1)) != sensorValue) {
            tryPattern.add(sensorValue);
        }
    }else tryPattern.add(sensorValue);
}
   public void patternRetriever(){
SharedPreferences sharedPreferences = getSharedPreferences("saved pattern",MODE_PRIVATE);
       Gson gson = new Gson();
       String json = sharedPreferences.getString("pattern list",null);
       Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
       patternList = gson.fromJson(json,type);

       if(patternList == null){

           patternList = new ArrayList<>();
       }
           }


   }
