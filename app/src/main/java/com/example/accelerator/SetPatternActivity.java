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

import com.example.accelerator.presenter.SetPatternPresenter;
import com.example.accelerator.presenter.SetPatternPresenterImp;
import com.example.accelerator.view.SetPatternView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetPatternActivity extends AppCompatActivity implements SensorEventListener, SetPatternView {

    SetPatternPresenter setPatternPresenter;

    double xValue = 0;
    double yValue = 0;
    double zValue = 0;

    Button setButton, startButton;
    TextView new_pattern_value;
    private SensorManager sensorManager;
    Sensor accelometer;
    private static final String TAG = "SetPatternActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pattern);

        setPatternPresenter = new SetPatternPresenterImp(this,this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelometer, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);

        new_pattern_value = (TextView) findViewById(R.id.new_pattern_value);
        startButton = (Button) findViewById(R.id.startButton);
        setButton = (Button) findViewById(R.id.setButton);

        setButton.setVisibility(View.INVISIBLE);
        new_pattern_value.setVisibility(View.INVISIBLE);
        startButton.setOnClickListener(buttonsClickListener);
        setButton.setOnClickListener(buttonsClickListener);
    }

    private View.OnClickListener buttonsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startButton:
                    setPatternPresenter.startButtonTasks();
                    break;

                case R.id.setButton:
                    setPatternPresenter.setButtonTasks();
                    break;
            }
        }
    };

    @Override
    public void onSensorChanged(SensorEvent event) {

        xValue = event.values[0];
        yValue = event.values[1];
        zValue = event.values[2];
        setPatternPresenter.onSensorChanged(xValue, yValue, zValue);
        setPatternPresenter.tiltAxisSymbolicNumberToBeSaved();
        setPatternPresenter.convTypingPattern();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
@Override
    public void startButtonFunction() {

   setButton.setVisibility(View.VISIBLE);
   new_pattern_value.setVisibility(View.VISIBLE);
}
@Override
public void setButtonFunction()
    {
    setPatternPresenter.savePatternToSharedPref();
    Intent intent = new Intent(SetPatternActivity.this, MainActivity.class);
                    startActivity(intent);
    }
    @Override
    public void showTypingPattern(String patternToBeShown) {
        new_pattern_value.setText(patternToBeShown);
    }

}
