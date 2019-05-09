package com.example.accelerator.presenter;

public interface SetPatternPresenter {
    void startButtonTasks();
    void setButtonTasks();
    void tiltAxisSymbolicNumberToBeSaved();
    void patternAdder(int sensorValue);
    void onSensorChanged(double xValue, double yValue, double zValue);
    void savePatternToSharedPref();

   void convTypingPattern();
}
