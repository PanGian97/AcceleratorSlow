package com.example.accelerator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SetPatternActivity extends AppCompatActivity implements SensorEventListener {
   // ArrayList<Integer> patternList = new ArrayList<>();
    pat pattern = new pat();
    double xValue = 0;
    double yValue = 0;
    double zValue = 0;
    ArrayList<Integer> patternList = pattern.getPattern();
    ArrayList<Integer> newPatternList = new ArrayList<>();
    Button setButton,startButton,backButton;
    TextView xView,yView,zView,new_pattern_value;
    private SensorManager sensorManager;
    Sensor accelometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pattern);



        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelometer  =sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener( this,accelometer,SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
        xView = (TextView)findViewById(R.id.newXView);
        yView = (TextView)findViewById(R.id.newYView);
        zView = (TextView)findViewById(R.id.newZView);
        startButton  =(Button)findViewById(R.id.startButton);
        backButton = (Button)findViewById(R.id.backButton);
        setButton  =(Button)findViewById(R.id.setButton);
        setButton.setVisibility(View.INVISIBLE);

        startButton.setOnClickListener(buttonsClickListener);
        setButton.setOnClickListener(buttonsClickListener);
        backButton.setOnClickListener(buttonsClickListener);

    }
    private View.OnClickListener buttonsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          switch (v.getId()){
              case R.id.startButton:

                setButton.setVisibility(View.VISIBLE);

                  break;
              case R.id.setButton:

                  patternList.addAll(newPatternList);


                  break;
              case R.id.backButton:
                  Intent intent = new Intent(SetPatternActivity.this, MainActivity.class);
                  startActivity(intent);
                  break;
          }
        }
    };
    @Override
    public void onSensorChanged(SensorEvent event) {


         xValue = event.values[0];
         yValue = event.values[1];
         zValue = event.values[2];

        if(xValue>=7){newPatternList.add(1);}
        if(xValue>=9){newPatternList.add(2);}
        if(xValue>=9){newPatternList.add(3);}

        if(xValue<=-7){newPatternList.add(-1);}
        if(xValue<=-9){newPatternList.add(-2);}
        if(xValue<=-9){newPatternList.add(-3);}

        StringBuilder stringBuilder = new StringBuilder();
        if(newPatternList.size()>0){
        for (Integer s : newPatternList) {
            stringBuilder.append(s );
        } new_pattern_value.setText(stringBuilder.toString());
        }


        xView.setText("X: "+ xValue);
        yView.setText("Y: "+ yValue);
        zView.setText("Z: "+ zValue);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
