package com.zhuchops.geomark_v20.view_models;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zhuchops.geomark_v20.models.GeoLayer;
import com.zhuchops.geomark_v20.models.GeoMark;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class LayerViewViewModel extends ViewModel {
    private MutableLiveData<GeoLayer> layer = new MutableLiveData<>();
    private MutableLiveData<Boolean> isEditing = new MutableLiveData<>();
    private MutableLiveData<List<GeoMark>> marks = new MutableLiveData<>();
    private MutableLiveData<GeoMark> editingMark = new MutableLiveData<>();

    public LayerViewViewModel() {

        loadMarks();
    }
    public void loadMarks() {
        List<GeoMark> marks;
        if (layer.getValue() != null) {
            marks = layer.getValue().getMarks();
        } else {
            marks = new ArrayList<>();
        }
        this.marks.setValue(marks);
    }

    public void setMarks(List<GeoMark> marks) {
        layer.getValue().setMarks(marks);
        loadMarks();
    }

    public LiveData<List<GeoMark>> getMarks() {
        return marks;
    }

    public void setLayer(GeoLayer layer) {
        this.layer.setValue(layer);
    }

    public LiveData<GeoLayer> getLayer() {
        return layer;
    }

    public void updateLayer(String name, String description, List<GeoMark> marks) {
        GeoLayer layer = getLayer().getValue();
        layer.setName(name);
        layer.setDescription(description);
        layer.setMarks(marks);
        this.layer.setValue(layer);
    }

    public void addMark(String name, String attitude,
                        String longitude, String description) {
        String id = UUID.randomUUID().toString();
        GeoMark mark = new GeoMark(
                id, name, description,
                Double.parseDouble(attitude), Double.parseDouble(longitude)
        );
        layer.getValue().addMark(mark);
        marks.getValue().add(mark);
        marks.setValue(marks.getValue());
        Log.i("MARK", "mark is added; count of marks is " + layer.getValue().getMarks().size());
    }

    public void removeMark(GeoMark mark) {
        Objects.requireNonNull(layer.getValue()).removeMark(mark);

        setLayer(layer.getValue());
    }

    public void setEditing(boolean mode) {
        isEditing.setValue(mode);
    }

    public LiveData<Boolean> getEditing() {
        return isEditing;
    }

    public void setEditingMark(GeoMark editingMark) {
        this.editingMark.setValue(editingMark);
    }

    public MutableLiveData<GeoMark> getEditingMark() {
        return editingMark;
    }
}
