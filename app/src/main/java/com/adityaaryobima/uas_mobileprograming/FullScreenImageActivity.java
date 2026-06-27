package com.adityaaryobima.uas_mobileprograming;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        PhotoView photoView = findViewById(R.id.photo_view_full);
        ImageView btnClose = findViewById(R.id.btn_close_full);

        String gambar = getIntent().getStringExtra("GAMBAR");

        if (gambar != null) {
            Glide.with(this)
                    .load(gambar)
                    .into(photoView);
        }

        btnClose.setOnClickListener(v -> finish());
    }
}