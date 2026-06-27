package com.adityaaryobima.uas_mobileprograming;

import android.content.Intent; // Tambahan import buat pindah halaman
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgFoto;
    private TextView tvNama, tvKategori, tvDeskripsi;
    private FloatingActionButton fabFavorit, fabBack;
    private AppDatabase database;
    private boolean isFavorit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgFoto = findViewById(R.id.img_detail_foto);
        tvNama = findViewById(R.id.tv_detail_nama);
        tvKategori = findViewById(R.id.tv_detail_kategori);
        tvDeskripsi = findViewById(R.id.tv_detail_deskripsi);
        fabFavorit = findViewById(R.id.fab_favorit);
        fabBack = findViewById(R.id.fab_back);

        fabBack.setOnClickListener(v -> finish());

        database = AppDatabase.getInstance(this);

        String id = getIntent().getStringExtra("ID");
        String nama = getIntent().getStringExtra("NAMA");
        String gambar = getIntent().getStringExtra("GAMBAR");
        String kategori = getIntent().getStringExtra("KATEGORI");
        String deskripsi = getIntent().getStringExtra("DESKRIPSI");

        if (nama != null) tvNama.setText(nama);
        if (kategori != null) tvKategori.setText(kategori);
        if (deskripsi != null) tvDeskripsi.setText(deskripsi);

        if (gambar != null) {
            Glide.with(this).load(gambar).centerCrop().into(imgFoto);
        }

        imgFoto.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, FullScreenImageActivity.class);
            intent.putExtra("GAMBAR", gambar);
            startActivity(intent);
        });

        new Thread(() -> {
            isFavorit = database.favoritDao().isFavorit(id);
            runOnUiThread(this::updateIconFavorit);
        }).start();

        fabFavorit.setOnClickListener(v -> new Thread(() -> {
            Favorit f = new Favorit();
            f.setId(id);
            f.setNama(nama);
            f.setGambar(gambar);
            f.setKategori(kategori);
            f.setDeskripsi(deskripsi);

            if (isFavorit) {
                database.favoritDao().hapusFavorit(f);
                isFavorit = false;
            } else {
                database.favoritDao().tambahFavorit(f);
                isFavorit = true;
            }

            runOnUiThread(() -> {
                updateIconFavorit();
            });
        }).start());
    }

    private void updateIconFavorit() {
        if (isFavorit) {
            fabFavorit.setImageResource(R.drawable.ic_heart_filled);
            fabFavorit.setColorFilter(android.graphics.Color.RED);
        } else {
            fabFavorit.setImageResource(R.drawable.ic_heart);
            fabFavorit.setColorFilter(android.graphics.Color.parseColor("#888888"));
        }
    }
}