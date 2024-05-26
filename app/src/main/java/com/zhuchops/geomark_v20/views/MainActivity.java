package com.zhuchops.geomark_v20.views;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.yandex.mapkit.MapKitFactory;
import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.databinding.ActivityMainBinding;
import com.zhuchops.geomark_v20.view_models.AppBarViewModel;
import com.zhuchops.geomark_v20.view_models.BottomNavigationBarViewModel;
import com.zhuchops.geomark_v20.view_models.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    private MainViewModel mainViewModel;
    private BottomNavigationBarViewModel botNavBarViewModel;
    private AppBarViewModel appBarViewModel;
    private ConstraintSet constraintSetTop;
    private ConstraintSet constraintSetAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MapKitFactory.setApiKey("0a98fb9d-4b92-4861-a005-87e3998b9d96");
        MapKitFactory.initialize(this);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        mainViewModel =
                new ViewModelProvider(this).get(MainViewModel.class);
        botNavBarViewModel =
                new ViewModelProvider(this).get(BottomNavigationBarViewModel.class);
        appBarViewModel
                = new ViewModelProvider(this).get(AppBarViewModel.class);

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        botNavBarViewModel.getIsVisible().observe(this, isVisible -> {
            if (isVisible) {
                binding.bottomNavigationView.setVisibility(View.VISIBLE);
            } else {
                binding.bottomNavigationView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
    }

    @Override
    protected void onStop() {
        MapKitFactory.getInstance().onStop();

        super.onStop();
    }
}
