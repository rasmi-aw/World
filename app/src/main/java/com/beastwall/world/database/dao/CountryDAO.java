package com.beastwall.world.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.beastwall.world.database.model.CountryDB;

import java.util.List;

/**
 * @author Abdel Wadoud Rasmi
 * <p>
 */
@Dao
public interface CountryDAO extends EntityDao<CountryDB> {

    @Override
    @Query("SELECT * FROM country")
    List<CountryDB> getAll();

    @Override
    @Query("SELECT * FROM country where id = :id")
    CountryDB getWithId(Long id);

    @Override
    @Insert
    void saveAll(List<CountryDB> countryDBS);

    @Override
    @Delete
    void deleteAll(List<CountryDB> countryDBS);

    @Override
    @Delete
    void delete(CountryDB countryDB);
}
