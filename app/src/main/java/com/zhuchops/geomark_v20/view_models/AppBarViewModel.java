package com.zhuchops.geomark_v20.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppBarViewModel extends ViewModel {

    private MutableLiveData<AppBarViewModel.FragmentType> type = new MutableLiveData<>();

    public MutableLiveData<AppBarViewModel.FragmentType> getCurrentFragmentType() {
        return type;
    }

    public void setCurrentFragmentType(AppBarViewModel.FragmentType type) {
        this.type.setValue(type);
    }

    public enum FragmentType {
        MAP,
        TEXT,
        CARD
    }
}
