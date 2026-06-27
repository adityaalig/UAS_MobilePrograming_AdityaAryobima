package com.adityaaryobima.uas_mobileprograming;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FavoritActivity extends AppCompatActivity {

    private RecyclerView rvFavorit;
    private ImageView btnBack;
    private AppDatabase database;
    private EndemikAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        rvFavorit = findViewById(R.id.rv_favorit);
        btnBack = findViewById(R.id.btn_back_favorit);

        rvFavorit.setLayoutManager(new GridLayoutManager(this, 2));
        database = AppDatabase.getInstance(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        muatDataFavorit();
    }

    private void muatDataFavorit() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Favorit> listFav = database.favoritDao().getAllFavorit();
                List<Endemik> listKonversi = new ArrayList<>();

                for (Favorit fav : listFav) {
                    Endemik e = new Endemik();
                    e.setId(fav.getId());
                    e.setNama(fav.getNama());
                    e.setGambar(fav.getGambar());
                    e.setKategori(fav.getKategori());
                    e.setDeskripsi(fav.getDeskripsi());
                    listKonversi.add(e);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new EndemikAdapter(listKonversi);
                        rvFavorit.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}