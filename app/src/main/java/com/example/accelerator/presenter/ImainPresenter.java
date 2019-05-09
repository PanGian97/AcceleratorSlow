package com.example.accelerator.presenter;

public interface ImainPresenter {
    void onSensorChanged(double xValue,double yValue,double zValue);
    String showSettedPattern();
    String showTryPattern();
    Boolean patternComparisor();
    void tryPatternAdder(int sensorValue);
    void tiltAxisSymbolicNumber();
    void setPatternClicked();

}
