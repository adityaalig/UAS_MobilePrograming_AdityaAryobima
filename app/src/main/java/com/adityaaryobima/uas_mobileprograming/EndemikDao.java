package com.adityaaryobima.uas_mobileprograming;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface EndemikDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Endemik> endemikList);

    @Query("SELECT * FROM endemik WHERE kategori = :kategori")
    List<Endemik> getEndemikByKategori(String kategori);

    @Query("SELECT * FROM endemik WHERE nama LIKE '%' || :keyword || '%'")
    List<Endemik> searchEndemik(String keyword);

    @Query("SELECT COUNT(*) FROM endemik")
    int getCount();
}
