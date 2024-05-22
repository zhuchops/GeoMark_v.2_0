package com.zhuchops.geomark_v20.models;

public class RecyclerLayerItem {
    private final GeoLayer layer;
    private byte[] imageData;
    private String name;
    private String description;
    private boolean isChecked;

    public RecyclerLayerItem(GeoLayer layer, boolean isChecked) {
        this.layer = layer;
        this.imageData = layer.getImageData();
        this.name = layer.getName();
        this.description = layer.getDescription();
        this.isChecked = isChecked;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public GeoLayer getLayer() {
        return layer;
    }
}
