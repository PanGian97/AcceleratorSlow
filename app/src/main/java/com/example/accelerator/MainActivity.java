package com.example.accelerator;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accelerator.presenter.MainPresenter;
import com.example.accelerator.presenter.MainPresenterImp;
import com.example.accelerator.view.MainView;

public class MainActivity extends AppCompatActivity implements SensorEventListener, MainView {

    private static final String TAG = "MainActivity";

//ON PRESENTER
//    ArrayList<Integer> tryPattern = new ArrayList<>();
//    ArrayList<Integer> patternList = new ArrayList<>();
    private MainPresenter mainPresenterImp;
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
        mainPresenterImp = new MainPresenterImp(this,this);
         mainPresenterImp.loadPatternFromSharedPref();


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


                mainPresenterImp.setPatternClicked();
            }
        });


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

        mainPresenterImp.onSensorChanged(event.values[0], event.values[1], event.values[2]);
        mainPresenterImp.tiltAxisSymbolicNumber();
        mainPresenterImp.patternComparisor();
//         }
//
// NOWHERE !!!!!!!
//        if(tryPattern.size()==(patternList.size()) *3){
//            tryPattern.clear();
//        }
}
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

@Override
    public void setterUI(int position) {
        switch (position) {
            case 0: phoneStateImg.setBackgroundResource(R.drawable.locked); break;

           case 1: setted_pattern.setText(mainPresenterImp.showSettedPattern()); break;

           case 2: pattern_value.setText(mainPresenterImp.showTryPattern()); break;

           case 3: phoneStateImg.setBackgroundResource(R.drawable.unlocked); break;
        }
    }
    @Override
public void navigateToSetPattern() {
    Intent intent = new Intent(MainActivity.this, SetPatternActivity.class);
    startActivity(intent);
}
   }
