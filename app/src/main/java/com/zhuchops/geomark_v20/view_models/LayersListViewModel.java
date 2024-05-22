package com.zhuchops.geomark_v20.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zhuchops.geomark_v20.models.GeoLayer;

import java.util.ArrayList;
import java.util.List;

public class LayersListViewModel extends ViewModel {

    private MutableLiveData<List<GeoLayer>> layers;
    private MutableLiveData<GeoLayer> selectedLayer = new MutableLiveData<>();

    public LayersListViewModel() {
        layers = new MutableLiveData<>();
        loadLayers();
    }

    public MutableLiveData<List<GeoLayer>> getLayers() {
        return layers;
    }

    public MutableLiveData<GeoLayer> getSelectedLayer() {
        return selectedLayer;
    }

    public void onItemClicked(GeoLayer layer) {
        selectedLayer.setValue(layer);
    }

    private void loadLayers() {
        List<GeoLayer> layers = new ArrayList<>();
        // TODO добавляем слои
        this.layers.setValue(layers);
    }
}
