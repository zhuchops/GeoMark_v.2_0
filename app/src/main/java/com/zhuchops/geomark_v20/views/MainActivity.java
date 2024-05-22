package com.zhuchops.geomark_v20.views;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.yandex.mapkit.MapKitFactory;
import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.databinding.ActivityMainBinding;
import com.zhuchops.geomark_v20.view_models.CurrentFragmentViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    private CurrentFragmentViewModel currentFragmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MapKitFactory.setApiKey("0a98fb9d-4b92-4861-a005-87e3998b9d96");
        MapKitFactory.initialize(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.bottom_navigation_container, new BottomNavigationBarFragment());
        transaction.replace(R.id.upper_menu, new UpperMenuFragment());
        transaction.commit();

        if (findViewById(R.id.nav_host_fragment) != null) {
            Log.d("CHECK", "FragmentContainerView найдено: R.id.nav_host_fragment");

            NavHostFragment navHostFragment =
                    (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
            NavController navController = navHostFragment.getNavController();
        } else {
            Log.e("CHECK", "FragmentContainerView не найдено: R.id.nav_host_fragment");
        }

        currentFragmentViewModel =
                new ViewModelProvider(this).get(CurrentFragmentViewModel.class);
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
