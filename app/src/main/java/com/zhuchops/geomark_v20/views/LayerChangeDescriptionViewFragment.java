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

import com.google.android.material.snackbar.Snackbar;
import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.databinding.FragmentLayerChangeDescriptionViewBinding;
import com.zhuchops.geomark_v20.models.GeoLayer;
import com.zhuchops.geomark_v20.view_models.BottomNavigationBarViewModel;
import com.zhuchops.geomark_v20.view_models.LayersListViewModel;

import java.util.ArrayList;

public class LayerChangeDescriptionViewFragment extends Fragment
        implements View.OnClickListener {

    private FragmentLayerChangeDescriptionViewBinding binding;
    private BottomNavigationBarViewModel botNavViewModel;
    private LayersListViewModel layersListViewModel;
    private GeoLayer currentLayer;

    public LayerChangeDescriptionViewFragment() {
        super(R.layout.fragment_layer_change_description_view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        botNavViewModel =
                new ViewModelProvider(requireActivity()).get(BottomNavigationBarViewModel.class);

        layersListViewModel = new ViewModelProvider(requireActivity()).get(LayersListViewModel.class);

        layersListViewModel.getEditingLayer().observe(this, layer -> {
            this.currentLayer = layer;
            if (layer != null) {
                updateUI();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLayerChangeDescriptionViewBinding.inflate(inflater, container, false);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        binding.addMarkButton.setOnClickListener(this);

        binding.toolBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.save) {
                save();
                navController.navigateUp();
                return true;
            }
            return false;
        });

        binding.toolBar.setNavigationOnClickListener(view -> {
            navController.navigateUp();
        });

        return binding.getRoot();
    }

    private void save() {
        if (binding.changeNameField.getEditText() != null) {
            String name = binding.changeNameField.getEditText().getText().toString();
            if (name.length() < 5 || name.length() > 20) {
                Snackbar.make(binding.getRoot(), R.string.set_name_attention, Snackbar.LENGTH_SHORT)
                        .show();
            } else {
                String description = binding.changeDescriptionField.getEditText().getText().toString();
                if (currentLayer == null)
                    layersListViewModel.addNewLayer(name, description, new ArrayList<>());
                else {
                    currentLayer.setName(name);
                    currentLayer.setDescription(description);
                    layersListViewModel.updateLayer(currentLayer);
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        botNavViewModel.setVisibility(false);
    }

    @Override
    public void onPause() {
        super.onPause();

        botNavViewModel.setVisibility(true);
    }

    public void updateUI() {
        binding.changeNameField.getEditText().setText(currentLayer.getName());
        binding.changeDescriptionField.getEditText().setText(currentLayer.getDescription());
    }

    @Override
    public void onClick(View v) {

    }
}
