package com.example.tpsoa.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tpsoa.R;
import com.example.tpsoa.dtos.responses.PublicApiResponse;
import com.example.tpsoa.models.HomeInteractorImpl;
import com.example.tpsoa.presenters.HomePresenter;
import com.example.tpsoa.presenters.HomePresenterImpl;
import com.example.tpsoa.utils.Accelerometer;
import com.example.tpsoa.utils.LightSensor;

import java.util.List;

public class HomeViewImp extends Activity implements HomeView {
    private HomePresenter presenter;
    private TableLayout layout;
    private SensorManager sManager;
    private Accelerometer accelerometer;
    private LightSensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.historyButtonId).setOnClickListener(listenerButtons);
        layout = findViewById(R.id.mainTableLayoutId);
        presenter = new HomePresenterImpl(this, new HomeInteractorImpl());
        presenter.getData(getApplicationContext());
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

    public void navigateToHistory(){
        Intent intent = new Intent(this, HistoryViewImp.class);
        startActivity(intent);
    }

    @Override
    public void showData( List<PublicApiResponse> data) {
        data.remove(data.size()-1);
        for(PublicApiResponse r : data){
            TableRow tvr = new TableRow(this);
            TableRow tvr2 = new TableRow(this);
            TextView name = new TextView(this);
            TextView purchase = new TextView(this);
            TextView sale = new TextView(this);
            name.setText(r.getCasa().getNombre());
            name.setTextSize(20);
            name.setTextColor(Color.GREEN);
            purchase.setText(r.getCasa().getCompra());
            purchase.setTextSize(15);
            purchase.setTextColor(Color.BLACK);
            purchase.setGravity(Gravity.CENTER);
            sale.setText(r.getCasa().getVenta());
            sale.setTextSize(15);
            sale.setTextColor(Color.BLACK);
            sale.setGravity(Gravity.CENTER);
            tvr.addView(name);
            tvr2.addView(purchase);
            tvr2.addView(sale);
            tvr.setGravity(Gravity.CENTER);
            tvr2.setGravity(Gravity.CENTER);
            layout.addView(tvr);
            layout.addView(tvr2);
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener listenerButtons = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.historyButtonId:
                    navigateToHistory();
                    break;
            }

        }
    };
}
