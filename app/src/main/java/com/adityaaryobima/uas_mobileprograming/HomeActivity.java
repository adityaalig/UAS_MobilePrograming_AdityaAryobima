package com.adityaaryobima.uas_mobileprograming;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import java.util.Locale;
import android.os.Handler;
import android.os.Looper;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        findViewById(R.id.btn_menu).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        findViewById(R.id.btn_search).setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, SearchActivity.class)));
        findViewById(R.id.btn_favorite).setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, FavoritActivity.class)));

        View btnToggleTheme = findViewById(R.id.btn_toggle_theme);
        btnToggleTheme.setOnClickListener(v -> {
            int mode = AppCompatDelegate.getDefaultNightMode();
            if (mode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

        View btnChangeLanguage = findViewById(R.id.btn_change_language);
        btnChangeLanguage.setOnClickListener(v -> {
            String lang = Locale.getDefault().getLanguage().equals("en") ? "in" : "en";
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.setLocale(locale);
            res.updateConfiguration(conf, dm);
            recreate();
        });

        if (savedInstanceState == null) {
            gantiFragment(new HewanFragment());
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.menu_hewan) {
                    selectedFragment = new HewanFragment();
                } else if (item.getItemId() == R.id.menu_tumbuhan) {
                    selectedFragment = new TumbuhanFragment();
                } else if (item.getItemId() == R.id.menu_profil) {
                    selectedFragment = new ProfileFragment();
                }

                if (selectedFragment != null) {
                    gantiFragment(selectedFragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void gantiFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}