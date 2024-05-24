package com.zhuchops.geomark_v20.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BottomNavigationBarViewModel extends ViewModel {
    private MutableLiveData<Boolean> isVisible = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsVisible() {
        return isVisible;
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible.setValue(isVisible);
    }
}
