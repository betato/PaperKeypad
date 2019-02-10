package org.betato.paperkeypad;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ClickDetector implements SensorEventListener {
    private SensorManager sensorManager;
    private ClickListner clickListner;
    private double[] x = new double[100];
    private int repeatDelay;
    private boolean firstRun = true;
    int index;

    public ClickDetector(ClickListner clickListner, Context context) {
        this.clickListner = clickListner;
        sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            if (firstRun) {
                for (int i = 0; i < x.length; i++) {
                    x[i] = event.values[0];
                }
                firstRun = false;
            }
            // Calculate moving average
            double xAvg = 0;
            for(int i = 0; i < x.length; i++) {
                xAvg += x[i];
            }
            xAvg /= x.length;

            // Average of current and previous reading
            double d = Math.abs(Math.abs(event.values[0] - xAvg));

            x[index] = event.values[0];
            index = (index + 1) % x.length;

            if (d > 0.1) {
                if (repeatDelay <= 0) {
                    clickListner.onClick();
                }
                if (d < 0.2) {
                    repeatDelay = 3;
                } else {
                    repeatDelay = 4;
                }
            }
            if (repeatDelay > 0) {
                repeatDelay--;
            }
        }
    }
}
