package com.zhuchops.geomark_v20.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.yandex.mapkit.map.CameraListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CameraUpdateReason;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.mapview.MapView;
import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.databinding.FragmentHomeMapBinding;
import com.zhuchops.geomark_v20.view_models.AppBarViewModel;
import com.zhuchops.geomark_v20.view_models.MainViewModel;

public class HomeMapFragment extends Fragment
        implements CameraListener {

    private FragmentHomeMapBinding binding;
    private MapView mapView;
    private MainViewModel mainViewModel;
    private AppBarViewModel appBarViewModel;

    public HomeMapFragment() {
        super(R.layout.fragment_home_map);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        appBarViewModel
                = new ViewModelProvider(requireActivity()).get(AppBarViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeMapBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = binding.mapView;
        mapView.getMapWindow().getMap().move(mapView.getMapWindow().getMap().getCameraPosition());

        mapView.getMapWindow().getMap().addCameraListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        appBarViewModel.setCurrentFragmentType(AppBarViewModel.FragmentType.MAP);
    }

    @Override
    public void onStop() {
        mapView.onStop();

        super.onStop();
    }

    @Override
    public void onCameraPositionChanged(@NonNull Map map, @NonNull CameraPosition cameraPosition,
                                        @NonNull CameraUpdateReason cameraUpdateReason, boolean b) {

    }
}
