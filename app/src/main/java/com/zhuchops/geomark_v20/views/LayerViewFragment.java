package com.zhuchops.geomark_v20.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayoutMediator;
import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.adapters.LayerViewAdapter;
import com.zhuchops.geomark_v20.databinding.FragmentLayerViewBinding;
import com.zhuchops.geomark_v20.models.GeoLayer;
import com.zhuchops.geomark_v20.view_models.LayerViewViewModel;
import com.zhuchops.geomark_v20.view_models.LayersListViewModel;

public class LayerViewFragment extends Fragment implements View.OnClickListener {
    private FragmentLayerViewBinding binding;
    private LayersListViewModel layersListViewModel;
    private LayerViewViewModel layerViewViewModel;
    private GeoLayer currentLayer;
    private NavController navController;

    public LayerViewFragment() {
        super(R.layout.fragment_layer_view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        layersListViewModel = new ViewModelProvider(requireActivity()).get(LayersListViewModel.class);
        layerViewViewModel = new ViewModelProvider(requireActivity()).get(LayerViewViewModel.class);

        layersListViewModel.getSelectedLayer().observe(this, layer -> {
            layerViewViewModel.setLayer(layersListViewModel.getSelectedLayer().getValue());
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLayerViewBinding.inflate(inflater, container, false);

        binding.buttonEditLayer.setOnClickListener(this);
        binding.toolBar.setNavigationOnClickListener(v -> {
            navController.navigateUp();
        });

        binding.toolBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.option_delete) {
                layersListViewModel.deleteLayer(layersListViewModel.getSelectedLayer().getValue());
                navController.navigateUp();
                return true;
            }
            return false;
        });

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
        })).attach();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        if (v.equals(binding.buttonEditLayer)) {
            navController.navigate(R.id.action_edit_layer);
            layersListViewModel.setEditingLayer(layersListViewModel.getSelectedLayer().getValue());
        }
    }
}
