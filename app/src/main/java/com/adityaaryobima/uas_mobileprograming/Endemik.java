package com.adityaaryobima.uas_mobileprograming;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "endemik")
public class Endemik {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private String id = "";

    @SerializedName("nama")
    private String nama;

    @SerializedName("foto")
    private String gambar;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("tipe")
    private String kategori;

    @NonNull
    public String getId() { return id; }
    public void setId(@NonNull String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getGambar() { return gambar; }
    public void setGambar(String gambar) { this.gambar = gambar; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
}