package com.zhuchops.geomark_v20.models;

import java.util.List;

public class UserData {
    private List<GeoLayer> layers;

    public UserData(List<GeoLayer> layers) {
        this.layers = layers;
    }

    public List<GeoLayer> getLayers() {
        return layers;
    }
}
