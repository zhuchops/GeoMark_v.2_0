package com.zhuchops.geomark_v20.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zhuchops.geomark_v20.models.GeoLayer;

public class LayerViewViewModel extends ViewModel {
    private MutableLiveData<GeoLayer> layer = new MutableLiveData<>();
    private MutableLiveData<Boolean> isEditing = new MutableLiveData<>();

    public MutableLiveData<GeoLayer> getLayer() {
        return layer;
    }

    public void setLayer(GeoLayer layer) {
        this.layer.setValue(layer);
    }

    public void updateLayer() {

    }

    public void addMark() {

    }

    public void setEditing(boolean mode) {
        isEditing.setValue(mode);
    }

    public LiveData<Boolean> getEditing() {
        return isEditing;
    }
}
