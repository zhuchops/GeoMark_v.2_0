package com.zhuchops.geomark_v20.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.databinding.FragmentBottomNavigationBarBinding;

public class BottomNavigationBarFragment extends Fragment
        {

    private FragmentBottomNavigationBarBinding binding;
    private NavController navController;

    public BottomNavigationBarFragment() {
        super(R.layout.fragment_bottom_navigation_bar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding =
                FragmentBottomNavigationBarBinding.inflate(inflater, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        return binding.getRoot();
    }
}
