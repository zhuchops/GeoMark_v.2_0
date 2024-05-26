package com.zhuchops.geomark_v20.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.zhuchops.geomark_v20.models.FileManager;
import com.zhuchops.geomark_v20.models.GeoLayer;
import com.zhuchops.geomark_v20.models.GeoMark;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LayersListViewModel extends AndroidViewModel {

    private MutableLiveData<List<GeoLayer>> layers;
    private MutableLiveData<GeoLayer> selectedLayer = new MutableLiveData<>();
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

    public void onItemClicked(GeoLayer layer) {
        selectedLayer.setValue(layer);
    }

    public void setLayers(List<GeoLayer> layers) {
        fileManager.writeLayers(layers);
        loadLayers();
    }

    private void loadLayers() {
        List<GeoLayer> layers = fileManager.readLayers();
        this.layers.setValue(layers);
    }

    public void addNewLayer(String name, String description, ArrayList<GeoMark> geoMarks) {
        String id = UUID.randomUUID().toString();
        GeoLayer layer = new GeoLayer(
                id, new byte[111],
                name, description, geoMarks
        );
        List<GeoLayer> layers = getLayers().getValue();
        layers.add(layer);
        setLayers(layers);
    }
}
