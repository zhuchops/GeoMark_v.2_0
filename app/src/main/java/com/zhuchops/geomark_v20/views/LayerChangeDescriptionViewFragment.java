package com.zhuchops.geomark_v20.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.google.android.material.snackbar.Snackbar;
import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.adapters.LayerCardsDecoration;
import com.zhuchops.geomark_v20.adapters.MarkRecyclerAdapter;
import com.zhuchops.geomark_v20.databinding.FragmentLayerChangeDescriptionViewBinding;
import com.zhuchops.geomark_v20.models.GeoLayer;
import com.zhuchops.geomark_v20.models.GeoMark;
import com.zhuchops.geomark_v20.view_models.BottomNavigationBarViewModel;
import com.zhuchops.geomark_v20.view_models.LayerViewViewModel;
import com.zhuchops.geomark_v20.view_models.LayersListViewModel;

import java.util.ArrayList;
import java.util.List;

public class LayerChangeDescriptionViewFragment extends Fragment
        implements View.OnClickListener, MarkRecyclerAdapter.OnItemClickListener {

    private FragmentLayerChangeDescriptionViewBinding binding;
    private BottomNavigationBarViewModel botNavViewModel;
    private LayersListViewModel layersListViewModel;
    private LayerViewViewModel layerViewViewModel;
    private NavController navController;
    private MarkRecyclerAdapter marksAdapter;


    public LayerChangeDescriptionViewFragment() {
        super(R.layout.fragment_layer_change_description_view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        botNavViewModel =
                new ViewModelProvider(requireActivity()).get(BottomNavigationBarViewModel.class);

        layersListViewModel = new ViewModelProvider(requireActivity()).get(LayersListViewModel.class);
        layerViewViewModel = new ViewModelProvider(requireActivity()).get(LayerViewViewModel.class);

        layerViewViewModel.getLayer().observe(this, layer -> {
            updateUI(layerViewViewModel.getLayer().getValue());
        });

        layersListViewModel.getIsAddLayer().observe(this, isAddLayer -> {
            if (isAddLayer) {
                layerViewViewModel.setLayer(null);
            }
        });

        layerViewViewModel.getEditing().observe(this, isEditing -> {
            if (isEditing) {
                 layerViewViewModel.setLayer(layerViewViewModel.getLayer().getValue());
            }
        });

        marksAdapter = new MarkRecyclerAdapter(this);

        layerViewViewModel.getMarks().observe(this, geoMarks -> {
            marksAdapter.setItems(geoMarks);
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLayerChangeDescriptionViewBinding.inflate(inflater, container, false);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

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

        binding.changeNameField.getEditText().addTextChangedListener(new NameValidator());

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

        binding.marksRecyclerViewContainer.setAdapter(marksAdapter);
        marksAdapter.setItems(layerViewViewModel.getMarks().getValue());

        updateUI(layerViewViewModel.getLayer().getValue());
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

    @Override
    public void onClick(View v) {
        if (v.equals(binding.addMarkButton)) {
            navController.navigate(R.id.action_add_mark);
        }
    }

    public void updateUI(GeoLayer currentLayer) {
        if (currentLayer != null) {
            binding.changeNameField.getEditText().setText(currentLayer.getName());
            binding.changeDescriptionField.getEditText().setText(currentLayer.getDescription());
        } else {
            binding.changeNameField.getEditText().setText("");
            binding.changeDescriptionField.getEditText().setText("");
        }
    }

    private void save() {
        if (binding.changeNameField.getError() == null) {
            if (layerViewViewModel.getLayer() == null) {
                layersListViewModel.addLayer(
                        binding.changeNameField.getEditText().getText().toString(),
                        binding.changeDescriptionField.getEditText().getText().toString(),
                        layerViewViewModel.getMarks().getValue()
                );
                layersListViewModel.setAddLayer(false);
            } else {
                layerViewViewModel.updateLayer(
                        binding.changeNameField.getEditText().getText().toString(),
                        binding.changeDescriptionField.getEditText().getText().toString(),
                        layerViewViewModel.getMarks().getValue()
                );
                layersListViewModel.updateLayer(layerViewViewModel.getLayer().getValue());
//                todo: кто отслеживает изменения и когда их сохраняет
                layerViewViewModel.setEditing(false);
            }
        } else {
            Snackbar.make(binding.getRoot(), R.string.text_correct_input, Toast.LENGTH_SHORT)
                    .show();
        }

    }

    @Override
    public void onItemClick(GeoMark mark) {
//        todo: отправить смотреть геометку
    }

    @Override
    public void onMoreOptionClick(View view, GeoMark mark) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            if (view.getId() == R.id.action_delete_mark) {
                layerViewViewModel.removeMark(mark);
                return true;
            }
            return false;
        });
        popupMenu.show();
    }

    public class NameValidator implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String name = s.toString();
            if (name.isEmpty()) {
                binding.changeNameField.setError(getString(R.string.text_name_cannot_be_empty));
            } else {
                binding.changeNameField.setError(null);
            }
        }
    }

    public static enum Mode {
        EDITING(1), ADDING(2);

        private final int mode;
        Mode(int mode) {
            this.mode = mode;
        }

        public int getMode() {
            return mode;
        }
    }
}
