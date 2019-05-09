package com.example.accelerator.presenter;

public interface MainPresenter {
    void onSensorChanged(double xValue,double yValue,double zValue);
    String showSettedPattern();
    String showTryPattern();
    Boolean patternComparisor();
    void tryPatternAdder(int sensorValue);
    void tiltAxisSymbolicNumber();
    void setPatternClicked();
    void loadPatternFromSharedPref();

}
