package com.zhuchops.geomark_v20.models;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.yandex.mapkit.geometry.Geo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    static Gson gson = new Gson();
    private static final String FILE_NAME = "user.json";
    private final Context context;

    public FileManager(Context context) {
        this.context = context;
    }

    public void writeLayers(List<GeoLayer> layers) {
        try (FileOutputStream fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            UserData userData = new UserData(layers);
            String jsonString = gson.toJson(userData);
            fileOutputStream.write(jsonString.getBytes());
            Log.i("WRITE", "successful writing layers");
        } catch (IOException e) {
            Log.e("WRITE", "error when attempting write layers" + "\nException:" + e);
        }
    }

    public List<GeoLayer> readLayers() {
        List<GeoLayer> layers = new ArrayList<>();
        try (FileInputStream fileInputStream = context.openFileInput(FILE_NAME)) {
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            UserData userData = gson.fromJson(inputStreamReader, UserData.class);
            layers = userData.getLayers();
            Log.i("READ", "successful reading layers");
        } catch (IOException e) {
            Log.e("READ", "error when attempting read layers\nException: " + e);
        }
        return layers;
    }
}
