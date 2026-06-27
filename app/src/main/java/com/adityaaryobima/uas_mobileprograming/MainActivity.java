package com.adityaaryobima.uas_mobileprograming;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = AppDatabase.getInstance(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int jumlahData = database.endemikDao().getCount();

                if (jumlahData > 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pindahKeHome();
                        }
                    });
                } else {
                    ambilDataDariApi();
                }
            }
        }).start();
    }

    private void ambilDataDariApi() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Endemik>> call = apiService.getEndemikData();

        call.enqueue(new Callback<List<Endemik>>() {
            @Override
            public void onResponse(Call<List<Endemik>> call, Response<List<Endemik>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Endemik> listEndemik = response.body();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            database.endemikDao().insertAll(listEndemik);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pindahKeHome();
                                }
                            });
                        }
                    }).start();
                } else {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show());
                }
            }

            @Override
            public void onFailure(Call<List<Endemik>> call, Throwable t) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error koneksi: " + t.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void pindahKeHome() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}