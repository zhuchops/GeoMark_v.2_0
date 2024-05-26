package com.zhuchops.geomark_v20.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.zhuchops.geomark_v20.views.LayerViewDescriptionFragment;
import com.zhuchops.geomark_v20.views.LayerViewFragment;

public class LayerViewAdapter extends FragmentStateAdapter {
    public LayerViewAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LayerViewDescriptionFragment();
            case 1:
                return new LayerViewDescriptionFragment();
            default:
                return new LayerViewDescriptionFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
