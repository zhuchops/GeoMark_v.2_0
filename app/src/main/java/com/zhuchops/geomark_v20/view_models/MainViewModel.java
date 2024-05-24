package com.zhuchops.geomark_v20.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<Class> currentFragment = new MutableLiveData<>();

    public void setCurrentFragment(Class fragmentClass) {
        currentFragment.setValue(fragmentClass);
    }

    public LiveData<Class> getCurrentFragment() {
        return currentFragment;
    }
}
