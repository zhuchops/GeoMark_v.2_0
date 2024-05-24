package com.zhuchops.geomark_v20.views;

import android.content.pm.CapabilityParams;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayoutMediator;
import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.adapters.ViewPagerAdapter;
import com.zhuchops.geomark_v20.databinding.FragmentLayersListBinding;
import com.zhuchops.geomark_v20.models.RecyclerLayerItem;
import com.zhuchops.geomark_v20.models.ViewPagerLayersItem;
import com.zhuchops.geomark_v20.view_models.AppBarViewModel;
import com.zhuchops.geomark_v20.view_models.MainViewModel;
import com.zhuchops.geomark_v20.view_models.LayersListViewModel;

import java.util.ArrayList;
import java.util.List;

public class LayersListFragment extends Fragment
        implements View.OnClickListener {

    private FragmentLayersListBinding binding;
    private LayersListViewModel viewModel;
    private MainViewModel mainViewModel;
    private AppBarViewModel appBarViewModel;

    public LayersListFragment() {
        super(R.layout.fragment_layers_list);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LayersListViewModel.class);
        mainViewModel =
                new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        appBarViewModel =
                new ViewModelProvider(requireActivity()).get(AppBarViewModel.class);

        viewModel.getSelectedLayer().observe(this, layer -> {
            layer.getName();
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLayersListBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO пересмотреть то что здесь написано, переделать в MVVM
        List<ViewPagerLayersItem> outerItems = new ArrayList<>();
        List<RecyclerLayerItem> innerItems = new ArrayList<>();
        // TODO получаем список слоев и засовываем в innerItems
        outerItems.add(new ViewPagerLayersItem(innerItems));
        outerItems.add(new ViewPagerLayersItem(innerItems));
        binding.viewPager.setAdapter(new ViewPagerAdapter(getContext(), outerItems, viewModel));
        binding.addLayerButton.setOnClickListener(this);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, ((tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(getString(R.string.text_my_layers));
                    break;
                case 1:
                    tab.setText(getString(R.string.text_shared_layers));
                    break;
            }
        })).attach();
    }

    @Override
    public void onResume() {
        super.onResume();

        appBarViewModel.setCurrentFragmentType(AppBarViewModel.FragmentType.TEXT);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(binding.addLayerButton)) {
            // TODO отправить на фрагмент с информацией о геослое
        }
    }
}
