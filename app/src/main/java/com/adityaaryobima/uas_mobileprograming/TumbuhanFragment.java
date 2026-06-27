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

public class TumbuhanFragment extends Fragment {

    private RecyclerView rvTumbuhan;
    private AppDatabase database;
    private EndemikAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tumbuhan, container, false);

        rvTumbuhan = view.findViewById(R.id.rv_tumbuhan);
        rvTumbuhan.setLayoutManager(new GridLayoutManager(getContext(), 2));

        database = AppDatabase.getInstance(getContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Endemik> listTumbuhan = database.endemikDao().getEndemikByKategori("Tumbuhan");

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new EndemikAdapter(listTumbuhan);
                            rvTumbuhan.setAdapter(adapter);
                        }
                    });
                }
            }
        }).start();

        return view;
    }
}