package com.example.tpsoa.models;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;

import androidx.core.app.ActivityCompat;

import com.example.tpsoa.presenters.OnFinishListenerSoa;

import java.util.Random;

public class AuthenticationInteractorImp implements AuthenticationInteractor {
    Random r = new Random();
    int randNumber = r.nextInt(9000 - 1000) + 1000;

    @Override
    public void send(Context ctx, String number) {
        if(number.length() == 0){
            //showToast("Ingrese número de celular");
            return;
        }
        //if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
        //    ActivityCompat.requestPermissions((Activity) ctx, new String[]{Manifest.permission.SEND_SMS}, 1);
        //}

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, String.valueOf(randNumber), null, null);
    }

    @Override
    public void verify( Context ctx, String code) {
        if(code.length() == 0){
            //showToast("Ingrese el código que se envió al celular");
            return;
        }
    }
}
