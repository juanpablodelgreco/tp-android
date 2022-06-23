package com.example.tpsoa.views;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tpsoa.R;
import com.example.tpsoa.models.HistoryInteractorImp;
import com.example.tpsoa.presenters.HistoryPresenter;
import com.example.tpsoa.presenters.HistoryPresenterImp;
import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;

import java.util.Map;

public class HistoryViewImp extends Activity implements HistoryView {
    TableLayout layout;
    TextView colUser;
    TextView lastAccess;
    private HistoryPresenter presenter;
    private SensorManager sManager;
    private Accelerometer accelerometer;
    private LightSensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        layout = findViewById(R.id.historyLayoutId);
        colUser = findViewById(R.id.columnUserId);
        lastAccess = findViewById(R.id.columnLastAccessId);
        presenter = new HistoryPresenterImp(this, new HistoryInteractorImp());
        presenter.getData(this);
        accelerometer = presenter.getAccelerometer(getApplicationContext(), this, sManager);
        lightSensor = presenter.getLightSensor(getApplicationContext(),this, sManager, layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        accelerometer.start();
        lightSensor.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        accelerometer.stop();
        lightSensor.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        accelerometer.stop();
        lightSensor.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accelerometer.stop();
        lightSensor.stop();
    }

    public void generateRows(Map<String, ?> data){
        for(Map.Entry<String, ?> entry : data.entrySet()) {
            TableRow tvr = new TableRow(this);
            TextView email = new TextView(this);
            TextView lastAccess = new TextView(this);
            email.setText(entry.getKey());
            email.setGravity(Gravity.CENTER_HORIZONTAL);
            email.setTextColor(Color.BLACK);
            email.setTextSize(14);
            email.setLayoutParams(colUser.getLayoutParams());
            lastAccess.setText(entry.getValue().toString());
            lastAccess.setGravity(Gravity.CENTER_HORIZONTAL);
            lastAccess.setTextColor(Color.BLACK);
            lastAccess.setTextSize(14);
            lastAccess.setLayoutParams(colUser.getLayoutParams());
            tvr.addView(email);
            tvr.addView(lastAccess);
            layout.addView(tvr);
        }

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
