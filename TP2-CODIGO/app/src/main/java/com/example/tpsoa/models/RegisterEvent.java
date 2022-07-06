package com.example.tpsoa.models;

import android.util.Log;

import com.example.tpsoa.utils.SessionInfo;
import com.google.gson.JsonObject;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterEvent implements Runnable {
    String type;
    String desc;
    String env;

    public RegisterEvent(String type, String desc){
        this.type = type;
        this.desc = desc;
        this.env = "PROD";
    }

    private void register()
    {
        try {
            JsonObject postData = new JsonObject();
            postData.addProperty("env", env);
            postData.addProperty("type_events", type);
            postData.addProperty("description", desc);

            URL url = new URL("http://so-unlam.net.ar/api/api/event");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Authorization", "Bearer "+ SessionInfo.authToken);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    out, "UTF-8"));
            writer.write(postData.toString());
            writer.flush();

            int code = urlConnection.getResponseCode();
            if (code !=  201) {
                throw new IOException("Invalid response from server: " + code);
            }

            Log.i("EVENT", "Evento registrado.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.register();
    }
}
