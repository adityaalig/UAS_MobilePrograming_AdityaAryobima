package com.adityaaryobima.uas_mobileprograming;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText etSearch;
    private ImageView btnBack, btnClear, btnFavorit;
    private RecyclerView rvSearch;
    private EndemikAdapter adapter;
    private AppDatabase database;
    private List<Endemik> listHasil = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch = findViewById(R.id.et_search);
        btnBack = findViewById(R.id.btn_back_search);
        btnClear = findViewById(R.id.btn_clear_search);
        btnFavorit = findViewById(R.id.btn_to_favorit);
        rvSearch = findViewById(R.id.rv_search);

        rvSearch.setLayoutManager(new GridLayoutManager(this, 2));
        database = AppDatabase.getInstance(this);

        btnBack.setOnClickListener(v -> finish());

        btnFavorit.setOnClickListener(v -> startActivity(new Intent(SearchActivity.this, FavoritActivity.class)));

        btnClear.setOnClickListener(v -> {
            etSearch.setText("");
            btnClear.setVisibility(View.GONE);
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {
                    btnClear.setVisibility(View.VISIBLE);
                } else {
                    btnClear.setVisibility(View.GONE);
                }
                cariData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void cariData(String keyword) {
        if (keyword.trim().isEmpty()) {
            listHasil.clear();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
            return;
        }

        new Thread(() -> {
            listHasil = database.endemikDao().searchEndemik(keyword);

            runOnUiThread(() -> {
                adapter = new EndemikAdapter(listHasil);
                rvSearch.setAdapter(adapter);
            });
        }).start();
    }
}