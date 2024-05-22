package com.zhuchops.geomark_v20.views;

import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.databinding.FragmentUpperMenuBinding;
import com.zhuchops.geomark_v20.view_models.CurrentFragmentViewModel;

public class UpperMenuFragment extends Fragment {

    private FragmentUpperMenuBinding binding;
    private CurrentFragmentViewModel currentFragmentViewModel;

    public UpperMenuFragment() {
        super(R.layout.fragment_upper_menu);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentFragmentViewModel =
                new ViewModelProvider(getActivity()).get(CurrentFragmentViewModel.class);

        currentFragmentViewModel.getCurrentFragment().observe(getActivity(), fragment -> {
            onFragmentChanged(fragment);
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUpperMenuBinding.inflate(inflater, container, false);
        onFragmentChanged(HomeMapFragment.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onFragmentChanged(Class fragment) {
        if (binding.getRoot() != null) {
            if (fragment.equals(HomeMapFragment.class)) {
                binding.layerNameView.setText(R.string.map_text);
                binding.upperMenuBg.setBackgroundColor(getResources().getColor(R.color.invisible));
            } else if (fragment.equals(LayersListFragment.class)) {
                binding.layerNameView.setText(R.string.layers_text);
                binding.upperMenuBg.setBackgroundColor(getResources().
                        getColor(R.color.upper_menu_background));
            }
        }
    }

}
