package com.zhuchops.geomark_v20.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.databinding.FragmentLayerViewDescriptionBinding;
import com.zhuchops.geomark_v20.models.GeoLayer;
import com.zhuchops.geomark_v20.view_models.LayerViewViewModel;
import com.zhuchops.geomark_v20.view_models.LayersListViewModel;

public class LayerViewDescriptionFragment extends Fragment {

    private FragmentLayerViewDescriptionBinding binding;
    private LayersListViewModel layersListViewModel;

    public LayerViewDescriptionFragment() {
        super(R.layout.fragment_layer_view_description);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment parent = requireParentFragment();
        layersListViewModel = new ViewModelProvider(requireActivity()).get(LayersListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLayerViewDescriptionBinding.inflate(inflater, container, false);

        layersListViewModel.getSelectedLayer().observe(getViewLifecycleOwner(), fragment -> {
            updateUI();
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void updateUI() {
        GeoLayer currentLayer = layersListViewModel.getSelectedLayer().getValue();
        binding.image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.no_image_sign, getResources().newTheme()));
        binding.nameView.setText(currentLayer.getName());
        binding.descriptionOfLayer.setText(currentLayer.getDescription());
    }
}
