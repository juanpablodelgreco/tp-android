package com.example.tpsoa.views;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.tpsoa.R;

import java.util.Map;

public class HistoryViewImp extends Activity {
    TableLayout layout;
    TextView colUser;
    TextView lastAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        layout = findViewById(R.id.historyLayoutId);
        colUser = findViewById(R.id.columnUserId);
        lastAccess = findViewById(R.id.columnLastAccessId);
        generateColumn();
    }

    private void generateColumn(){
        SharedPreferences sp = getSharedPreferences("log", this.MODE_PRIVATE);
        Map<String, ?> data = sp.getAll();
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
}
