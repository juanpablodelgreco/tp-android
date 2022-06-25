package com.example.tpsoa.utils;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.example.tpsoa.dtos.responses.CreateUserResponse;
import com.example.tpsoa.services.RegisterEventService;

public class Accelerometer implements SensorEventListener {

        private Activity acc;
        private SensorManager sManager;
        private Sensor sensor;
        public static final int SHAKE_LIMIT = 800;
        public static int contShake = 0;
        private float last_x = 0;
        private float last_y = 0;
        private float last_z = 0;
        private long lastTime = 0;

        public Accelerometer(Activity acc, SensorManager sManager){
            this.acc = acc;
            this.sManager = sManager;
            this.sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        public void start(){
            sManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }

        public void stop(){
            sManager.unregisterListener(this);
        }

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
                Log.i("SENSOR", "Cerrando aplicación por shake.");
                acc.finishAffinity();
                System.exit(0);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
