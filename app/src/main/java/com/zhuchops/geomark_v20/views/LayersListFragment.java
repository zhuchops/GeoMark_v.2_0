package com.zhuchops.geomark_v20.views;

import android.content.pm.CapabilityParams;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.adapters.LayerCardsDecoration;
import com.zhuchops.geomark_v20.adapters.RecyclerAdapter;
import com.zhuchops.geomark_v20.adapters.ViewPagerAdapter;
import com.zhuchops.geomark_v20.databinding.FragmentLayersListBinding;
import com.zhuchops.geomark_v20.models.GeoLayer;
import com.zhuchops.geomark_v20.models.RecyclerLayerItem;
import com.zhuchops.geomark_v20.models.ViewPagerLayersItem;
import com.zhuchops.geomark_v20.view_models.AppBarViewModel;
import com.zhuchops.geomark_v20.view_models.MainViewModel;
import com.zhuchops.geomark_v20.view_models.LayersListViewModel;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

public class LayersListFragment extends Fragment
        implements View.OnClickListener, RecyclerAdapter.OnItemClickListener {

    private FragmentLayersListBinding binding;
    private LayersListViewModel viewModel;
    private MainViewModel mainViewModel;
    private AppBarViewModel appBarViewModel;
    private RecyclerAdapter myLayersAdapter;
    private RecyclerAdapter otherLayersAdapter;

    public LayersListFragment() {
        super(R.layout.fragment_layers_list);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myLayersAdapter = new RecyclerAdapter(this);
        otherLayersAdapter = new RecyclerAdapter(this);

        viewModel = new ViewModelProvider(requireActivity()).get(LayersListViewModel.class);
        mainViewModel =
                new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        appBarViewModel =
                new ViewModelProvider(requireActivity()).get(AppBarViewModel.class);

        viewModel.getSelectedLayer().observe(this, layer -> {
            //todo: отправить смотреть выбранный слой

        });
        viewModel.getLayers().observe(this, layers -> {
            myLayersAdapter.setItems(layers);
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

        List<RecyclerView.ItemDecoration> itemDecorations = new ArrayList<>();

        MaterialDividerItemDecoration layerCardsDivider =
                new MaterialDividerItemDecoration(this.requireContext(), LinearLayoutManager.VERTICAL);
        layerCardsDivider.setDividerColor(getResources().getColor(R.color.divider_color));

        itemDecorations.add(layerCardsDivider);

        float dp = getResources().getDimension(R.dimen.layerCardsDividerMargin);
        float density = requireActivity().getResources().getDisplayMetrics().density;
        int px = Math.round(dp * density);
        LayerCardsDecoration layerCardsDecoration = new LayerCardsDecoration(px);

        itemDecorations.add(layerCardsDecoration);

        List<RecyclerAdapter> innerAdapters = new ArrayList<>();
        innerAdapters.add(myLayersAdapter);
        innerAdapters.add(otherLayersAdapter);
        binding.viewPager.setAdapter(new ViewPagerAdapter(innerAdapters, itemDecorations));
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
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        if (v.equals(binding.addLayerButton)) {
            navController.navigate(R.id.action_add_new_layer);
        }
    }

    @Override
    public void onItemClick(GeoLayer layer) {

    }
}
