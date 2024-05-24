package com.zhuchops.geomark_v20.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayoutMediator;
import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.adapters.LayerViewAdapter;
import com.zhuchops.geomark_v20.databinding.FragmentLayerViewBinding;
import com.zhuchops.geomark_v20.models.GeoLayer;
import com.zhuchops.geomark_v20.view_models.AppBarViewModel;
import com.zhuchops.geomark_v20.view_models.LayerViewViewModel;

public class LayerViewFragment extends Fragment {
    private FragmentLayerViewBinding binding;
    private LayerViewViewModel viewModel;
    private AppBarViewModel appBarViewModel;
    private GeoLayer currentLayer;

    public LayerViewFragment() {
        super(R.layout.fragment_layer_view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LayerViewViewModel.class);
        appBarViewModel =
                new ViewModelProvider(requireActivity()).get(AppBarViewModel.class);

        viewModel.getLayer().observe(this, geoLayer -> {
            this.currentLayer = geoLayer;
            updateUI();
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLayerViewBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.viewPager.setAdapter(new LayerViewAdapter(this));

        new TabLayoutMediator(binding.tabsLayout, binding.viewPager, ((tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(getString(R.string.text_description));
                    break;
                case 1:
                    tab.setText(getString(R.string.text_map));
                    break;
            }
        }));
    }

    public void updateUI() {
        //TODO
    }

    @Override
    public void onResume() {
        super.onResume();

        appBarViewModel.setCurrentFragmentType(AppBarViewModel.FragmentType.CARD);
    }
}
