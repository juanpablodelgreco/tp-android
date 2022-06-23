package com.example.tpsoa.models;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.example.tpsoa.presenters.OnFinishListenerSoa;
import com.example.tpsoa.services.RegisterEventService;
import com.example.tpsoa.views.AuthenticationViewImp;

import java.util.Random;

public class AuthenticationInteractorImp implements AuthenticationInteractor {
    Random r = new Random();
    int randNumber = r.nextInt(9000 - 1000) + 1000;

    @Override
    public void send(Context ctx, OnFinishListenerSoa ofs, String number) {
        if(number.length() == 0){
            ofs.showToast("Ingrese número de celular.");
            return;
        }

        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) ctx, new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, String.valueOf(randNumber), null, null);

        RegisterEventService.register("Send Code", "Se envió código de verificación");
        Log.i("Event", "Evento registrado con éxito.");
    }

    @Override
    public boolean verify(Context ctx, OnFinishListenerSoa ofs, String code) {
        if(code.length() == 0){
            ofs.showToast("Ingrese el código que se envió al celular.");
        }
        return String.valueOf(randNumber).equals(code) ? true : false;
    }
}
