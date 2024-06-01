package com.zhuchops.geomark_v20.models;

import java.util.ArrayList;
import java.util.List;

public class GeoLayer {
    private final String id;
    private byte[] imageData;
    private ArrayList<GeoMark> marks;
    private String name;
    private String description;


    public GeoLayer(String id, byte[] imageData, String name, String description, List<GeoMark> marks) {
        this.id = id;
        this.imageData = imageData;
        this.name = name;
        this.description = description;
        this.marks = new ArrayList<>();
        this.marks.addAll(marks);
    }

    public void addMark(GeoMark mark) {
        this.marks.add(mark);
    }

    public void addMark(int index, GeoMark mark) {
        this.marks.add(index, mark);
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void removeMark(int index) {
        this.marks.remove(index);
    }
    public void removeMark(GeoMark mark) {
        this.marks.remove(mark);
    }

    public int getSize() {
        return marks.size();
    }

    public void changeMark(int index, GeoMark mark) {
        this.marks.set(index, mark);
    }

    public ArrayList<GeoMark> getMarks() {
        return marks;
    }

    public GeoMark getMark(int index) {
        return marks.get(index);
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
        this.marks.clear();
        this.marks.addAll(layer);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
