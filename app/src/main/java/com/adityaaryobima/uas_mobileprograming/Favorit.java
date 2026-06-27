package com.adityaaryobima.uas_mobileprograming;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorit")
public class Favorit {

    @PrimaryKey
    @NonNull
    private String id = "";

    private String nama;
    private String gambar;
    private String deskripsi;
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
