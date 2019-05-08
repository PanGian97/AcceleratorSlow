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

    Sensor accelometer;
    TextView pattern_value,setted_pattern;
    ImageView phoneStateImg;
    Button set_pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Initializing sensor Services");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelometer  =sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener( this,accelometer,SensorManager.SENSOR_STATUS_ACCURACY_HIGH);

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

        double xValue = event.values[0];
        double yValue = event.values[1];
        double zValue = event.values[2];


        setted_pattern.setText(patternStringBuilder.toString());

      pattern_value.setText(stringBuilder.toString());

       phoneStateImg.setBackgroundResource(R.drawable.unlocked);
//         }
//
// NOWHERE !!!!!!!
//        if(tryPattern.size()==(patternList.size()) *3){
//            tryPattern.clear();
//        }
}
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

   }
