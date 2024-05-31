package com.zhuchops.geomark_v20.models;

import com.yandex.mapkit.geometry.Geo;

import java.util.ArrayList;
import java.util.List;

public class GeoLayer {
    private final String id;
    private byte[] imageData;
    private ArrayList<GeoMark> layer;
    private String name;
    private String description;


    public GeoLayer(String id, byte[] imageData, String name, String description, List<GeoMark> layer) {
        this.id = id;
        this.imageData = imageData;
        this.name = name;
        this.description = description;
        this.layer = new ArrayList<>();
        this.layer.addAll(layer);
    }

    public void addMark(GeoMark mark) {
        this.layer.add(mark);
    }

    public void addMark(int index, GeoMark mark) {
        this.layer.add(index, mark);
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void removeMark(int index) {
        this.layer.remove(index);
    }
    public void removeMark(GeoMark mark) {
        this.layer.remove(mark);
    }

    public int getSize() {
        return layer.size();
    }

    public void changeMark(int index, GeoMark mark) {
        this.layer.set(index, mark);
    }

    public ArrayList<GeoMark> getMarks() {
        return layer;
    }

    public GeoMark getMark(int index) {
        return layer.get(index);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return this.id;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public void setMarks(List<GeoMark> layer) {
        this.layer.clear();
        this.layer.addAll(layer);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
