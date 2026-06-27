package com.adityaaryobima.uas_mobileprograming;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class EndemikAdapter extends RecyclerView.Adapter<EndemikAdapter.EndemikViewHolder> {

    private List<Endemik> listEndemik;

    public EndemikAdapter(List<Endemik> listEndemik) {
        this.listEndemik = listEndemik;
    }

    @NonNull
    @Override
    public EndemikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_endemik, parent, false);
        return new EndemikViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EndemikViewHolder holder, int position) {
        Endemik data = listEndemik.get(position);

        holder.tvNama.setText(data.getNama());

        Glide.with(holder.itemView.getContext())
                .load(data.getGambar())
                .centerCrop()
                .into(holder.imgEndemik);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);

                intent.putExtra("ID", data.getId());
                intent.putExtra("NAMA", data.getNama());
                intent.putExtra("GAMBAR", data.getGambar());
                intent.putExtra("KATEGORI", data.getKategori());
                intent.putExtra("DESKRIPSI", data.getDeskripsi());

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listEndemik.size();
    }

    public static class EndemikViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEndemik;
        TextView tvNama;

        public EndemikViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEndemik = itemView.findViewById(R.id.img_endemik);
            tvNama = itemView.findViewById(R.id.tv_nama_endemik);
        }
    }
}