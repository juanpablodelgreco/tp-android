package com.example.tpsoa.utils;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class Accelerometer {

        private SensorManager smanager;
        private Sensor sensorA;
        private SensorEventListener sensorListener;
        public static final int SHAKE_LIMIT = 800;
        public static int contShake = 0;

        public boolean setShake(){
            sensorA = smanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(sensorA == null){
                return false;
            }
            sensorListener = new SensorEventListener() {
                long lastTime = 0;
                float last_x;
                float last_y;
                float last_z;
                @Override
                public void onSensorChanged(SensorEvent event) {
                    long currentTime = System.currentTimeMillis();

                    if((currentTime - lastTime) > 100){
                        long diffTime = (currentTime - lastTime);
                        lastTime = currentTime;
                        float x = event.values[0];
                        float y = event.values[1];
                        float z = event.values[2];
                        float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;
                        if(speed > SHAKE_LIMIT){
                            Log.i("SENSOR", String.valueOf(speed));
                            contShake++;
                        }
                        last_x = x;
                        last_y = y;
                        last_z = z;
                        if(contShake == 2){
                            System.exit(1);
                        }
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
            start();
            return true;
        }

        private void start(){
            smanager.registerListener(sensorListener,sensorA,SensorManager.SENSOR_DELAY_NORMAL);
        }

        public void setSensorManager(SensorManager accelerometer){
            this.smanager = accelerometer;
        }
}
