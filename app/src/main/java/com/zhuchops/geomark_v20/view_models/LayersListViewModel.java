package com.zhuchops.geomark_v20.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.zhuchops.geomark_v20.models.FileManager;
import com.zhuchops.geomark_v20.models.GeoLayer;
import com.zhuchops.geomark_v20.models.GeoMark;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LayersListViewModel extends AndroidViewModel {

    private MutableLiveData<List<GeoLayer>> layers;
    private MutableLiveData<GeoLayer> selectedLayer = new MutableLiveData<>();
    private MutableLiveData<Boolean> isAddLayer = new MutableLiveData<>();
    private FileManager fileManager;

    public LayersListViewModel(@NonNull Application application) {
        super(application);

        fileManager = new FileManager(application);
        layers = new MutableLiveData<>();
        loadLayers();
    }

    public MutableLiveData<List<GeoLayer>> getLayers() {
        return layers;
    }

    public MutableLiveData<GeoLayer> getSelectedLayer() {
        return selectedLayer;
    }

    public void selectLayer(GeoLayer layer) {
        selectedLayer.setValue(layer);
    }

    public void setLayers(List<GeoLayer> layers) {
        fileManager.writeLayers(layers);
        loadLayers();
    }

    public void saveLayers() {
        fileManager.writeLayers(layers.getValue());
    }

    private void loadLayers() {
        List<GeoLayer> layers = fileManager.readLayers();
        this.layers.setValue(layers);
    }

    public void addLayer(String name, String description, List<GeoMark> geoMarks) {
        String id = UUID.randomUUID().toString();
        GeoLayer layer = new GeoLayer(
                id, new byte[111],
                name, description, geoMarks
        );
        List<GeoLayer> layers = getLayers().getValue();
        layers.add(layer);
        setLayers(layers);
    }

    public void updateCurrentLayer(GeoLayer oldLayer, GeoLayer updatedLayer) {
        List<GeoLayer> layers = this.layers.getValue();
        layers.set(layers.indexOf(oldLayer), updatedLayer);
        setLayers(layers);
    }

    public void setAddLayer(Boolean mode) {
        isAddLayer.setValue(mode);
    }

    public MutableLiveData<Boolean> getIsAddLayer() {
        return isAddLayer;
    }

    public void deleteLayer(GeoLayer value) {
        List<GeoLayer> layers = getLayers().getValue();
        layers.remove(layers.indexOf(value));
        setLayers(layers);
    }

    public void updateLayer(GeoLayer updatedLayer) {
        GeoLayer oldLayer = findLayerById(updatedLayer.getId());
        if (oldLayer != null) {
            layers.getValue().set(layers.getValue().indexOf(oldLayer), updatedLayer);
        }
    }

    public GeoLayer findLayerById(String id) {
        for (GeoLayer layer:
             layers.getValue()) {
            if (layer.getId().equals(id)) return layer;
        }
        return null;
    }

//    public void addMarkToLayer(
//            String name, String attitude,
//            String longitude, String description) {
//
//        String id = UUID.randomUUID().toString();
//        GeoMark mark = new GeoMark(
//            id, name, description,
//                Double.parseDouble(attitude), Double.parseDouble(longitude)
//        );
//        Objects.requireNonNull(selectedLayer.getValue()).addMark(mark);
//        setLayers(layers.getValue());
//    }
//
//    public void removeMarkFromLayer(GeoMark mark) {
//        Objects.requireNonNull(selectedLayer.getValue()).removeMark(mark);
//
//        setLayers(layers.getValue());
//    }
}
