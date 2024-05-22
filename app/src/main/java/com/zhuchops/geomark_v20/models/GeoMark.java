package com.zhuchops.geomark_v20.models;

public class GeoMark {
    private String id;
    private String title;
    private double latitude;
    private double longitude;

    // Конструкторы, геттеры и сеттеры
    public GeoMark(String id, String title, double latitude, double longitude) {
        this.id = id;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

