package com.zhuchops.geomark_v20.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.databinding.FragmentChangeMarkBinding;
import com.zhuchops.geomark_v20.view_models.BottomNavigationBarViewModel;
import com.zhuchops.geomark_v20.view_models.LayersListViewModel;

public class MarkChangeFragment extends Fragment {

    private BottomNavigationBarViewModel botNavViewModel;
    private LayersListViewModel layersListViewModel;
    private FragmentChangeMarkBinding binding;
    private NavController navController;

    public MarkChangeFragment() {
        super(R.layout.fragment_change_mark);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        botNavViewModel =
                new ViewModelProvider(requireActivity()).get(BottomNavigationBarViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChangeMarkBinding.inflate(inflater, container, false);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

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

    }

    private void save() {
        if (binding.changeLatitudeField.getError() == null && binding.changeLongitudeField.getError() == null) {
            layersListViewModel.addMarkToLayer();
        } else {
            Snackbar.make(binding.getRoot(), R.string.text_correct_input, Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // text watchers
    public class LatitudeValidator implements TextWatcher {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            String latitudeStr = s.toString();
            if (latitudeStr.isEmpty()) {
                binding.changeLatitudeField.setError(getString(R.string.text_longitude_cannot_be_empty));
            } else {
                try {
                    double latitude = Double.parseDouble(latitudeStr);
                    if (latitude < -90 || latitude > 90) {
                        binding.changeLatitudeField.setError(getString(R.string.text_longitude_have_to_be_between));
                    } else {
                        binding.changeLatitudeField.setError(null);
                    }
                } catch (NumberFormatException e) {
                    binding.changeLatitudeField.setError(getString(R.string.text_invalid_format));
                }
            }
        }
    }

    public class LongitudeValidator implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String longitudeStr = s.toString();
            if (longitudeStr.isEmpty()) {
                binding.changeLongitudeField.setError(getString(R.string.text_longitude_cannot_be_empty));
            } else {
                try {
                    double longitude = Double.parseDouble(longitudeStr);
                    if (longitude < -180 || longitude > 180) {
                        binding.changeLongitudeField.setError(getString(R.string.text_longitude_have_to_be_between));
                    } else {
                        binding.changeLongitudeField.setError(null);
                    }
                } catch (NumberFormatException e) {
                    binding.changeLongitudeField.setError(getString(R.string.text_invalid_format));
                }
            }
        }
    }
}
