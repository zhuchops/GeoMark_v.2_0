package com.zhuchops.geomark_v20.models;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
    static Gson gson = new Gson();

    static boolean writeLayerTo(GeoLayer layer, String directory) {
        String jsonString = gson.toJson(layer);

        try (FileWriter writer = new FileWriter(directory + layer.getId())) {
            writer.write(jsonString);
        } catch (IOException e) {
            Log.e("WRITE", "can`t write layer: " + layer.getId());
            return false;
        }
        return true;
    }

    static void writeLayersTo(ArrayList<GeoLayer> layers, String directory) {
        for (GeoLayer layer:
             layers) {
            for (int i = 0; i < 3; i++) {
                if (writeLayerTo(layer, directory)) break;
            }
        }
    }

    static GeoLayer readLayerFrom(String directory) {
        StringBuilder jsonStringBuilder = new StringBuilder();
        String line;
        GeoLayer layer;
        try (BufferedReader reader = new BufferedReader(new FileReader(directory))) {
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
            layer = gson.fromJson(jsonStringBuilder.toString(), GeoLayer.class);
        } catch (IOException e) {
            Log.e("READ", "can`t read layer");
            return null;
        }
        return layer;
    }

    static ArrayList<GeoLayer> readLayersFrom(String directory) {
        File dir = new File(directory);
        File[] files = dir.listFiles();
        ArrayList<GeoLayer> layers = new ArrayList<>();
        if (files != null)
            for (File file:
                 files) {
                layers.add(readLayerFrom(file.getPath()));
            }
        return layers;
    }
}
