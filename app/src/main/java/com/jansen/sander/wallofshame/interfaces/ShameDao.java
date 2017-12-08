package com.jansen.sander.wallofshame.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.jansen.sander.wallofshame.classes.Shame;

import java.util.List;

/**
 * Created by Sander on 5/12/2017.
 */

@Dao
public interface ShameDao {
    /**
     * Listing all of the shames in the database
     */
    @Query("SELECT * FROM shame")
    List<Shame> getAll();

    @Query("SELECT * FROM shame WHERE shame_rating IN (:rating)")
    List<Shame> getByRating(int rating);


    @Query("SELECT * FROM shame WHERE sid IN (:shameIds)")
    List<Shame> loadAllByIds(int[] shameIds);

    /**
     * Inserting a shame into the database
     * @param shames a shame object with a shameMessage, shameDuts and starRating
     */
    @Insert
    void insertAll(Shame... shames);

    /**
     * Deletes a shame from the database
     * @param shame a shame object with a shameMessage, shameDuts and starRating
     */
    @Delete
    void delete(Shame shame);
}
