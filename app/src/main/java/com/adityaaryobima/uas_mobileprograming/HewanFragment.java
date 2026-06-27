package com.adityaaryobima.uas_mobileprograming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HewanFragment extends Fragment {

    private RecyclerView rvHewan;
    private AppDatabase database;
    private EndemikAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hewan, container, false);

        rvHewan = view.findViewById(R.id.rv_hewan);
        rvHewan.setLayoutManager(new GridLayoutManager(getContext(), 2));
        database = AppDatabase.getInstance(getContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Endemik> listHewan = database.endemikDao().getEndemikByKategori("Hewan");

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            adapter = new EndemikAdapter(listHewan);
                            rvHewan.setAdapter(adapter);
                        }
                    });
                }
            }
        }).start();

        return view;
    }
}