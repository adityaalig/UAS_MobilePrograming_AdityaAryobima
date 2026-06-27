package com.adityaaryobima.uas_mobileprograming;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface FavoritDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void tambahFavorit(Favorit favorit);

    @Delete
    void hapusFavorit(Favorit favorit);

    @Query("SELECT * FROM favorit")
    List<Favorit> getAllFavorit();

    @Query("SELECT EXISTS(SELECT * FROM favorit WHERE id = :id)")
    boolean isFavorit(String id);
}