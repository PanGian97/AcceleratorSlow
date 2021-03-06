package com.example.accelerator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetPatternActivity extends AppCompatActivity implements SensorEventListener {
   // ArrayList<Integer> patternList = new ArrayList<>();
   private Context context;
    double xValue = 0;
    double yValue = 0;
    double zValue = 0;

    ArrayList<Integer> newPatternList = new ArrayList<>();
    ArrayList<Integer> patternList = new ArrayList<>();
    Button setButton,startButton;
    TextView new_pattern_value;
    private SensorManager sensorManager;
    Sensor accelometer;
    private static final String TAG = "SetPatternActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pattern);



        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelometer  =sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener( this,accelometer,SensorManager.SENSOR_STATUS_ACCURACY_HIGH);

        new_pattern_value = (TextView)findViewById(R.id.new_pattern_value);

        startButton  =(Button)findViewById(R.id.startButton);
        setButton  =(Button)findViewById(R.id.setButton);
        setButton.setVisibility(View.INVISIBLE);
        new_pattern_value.setVisibility(View.INVISIBLE);
        startButton.setOnClickListener(buttonsClickListener);
        setButton.setOnClickListener(buttonsClickListener);


    }
    private View.OnClickListener buttonsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          switch (v.getId()){
              case R.id.startButton:

                setButton.setVisibility(View.VISIBLE);
                newPatternList.clear();
                patternList.clear();
                new_pattern_value.setVisibility(View.VISIBLE);
                  break;
              case R.id.setButton:

                  patternList.addAll(newPatternList);
                 Intent intent = new Intent(SetPatternActivity.this, MainActivity.class);
//                  intent.putExtra("patternList", patternList);
                  saveToSharedPreferences(newPatternList);
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

        if(xValue>=7){patternAdder(1);}
        if(yValue>=9){patternAdder(2);}
        if(zValue>=9){patternAdder(3);}

        if(xValue<=-7){patternAdder(-1);}
        if(yValue<=-9){patternAdder(-2);}
        if(zValue<=-9){patternAdder(-3);}

        StringBuilder stringBuilder = new StringBuilder();
        if(newPatternList.size()>0){
        for (Integer s : newPatternList) {
            stringBuilder.append(s );
        } new_pattern_value.setText(stringBuilder.toString());
        }





    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void patternAdder(int sensorValue){
        if(newPatternList.size()>=1){
            if((newPatternList.get(newPatternList.size() - 1)) != sensorValue) {
                newPatternList.add(sensorValue);
            }
        }else newPatternList.add(sensorValue);
    }

    public void saveToSharedPreferences( ArrayList<Integer> patternToBeSaved) {
     SharedPreferences sharedPreferences = getSharedPreferences("saved pattern",MODE_PRIVATE);
     SharedPreferences.Editor editor = sharedPreferences.edit();
     Gson gson = new Gson();
     String json = gson.toJson(newPatternList);
     editor.putString("pattern list",json);
     editor.apply();
    }
}
