package com.beastwall.world.database;

import androidx.room.TypeConverter;

import com.beastwall.localisation.model.Country;
import com.google.gson.Gson;


/**
 * @author AbdelWadoud Rasmi
 */
public class Converter {
    private static Gson gson = new Gson();

    /**
     * Country converter
     */
    @TypeConverter
    public static String fromCountry(Country country){
        if (country == null) return null;
        return gson.toJson(country);
    }

    @TypeConverter
    public static Country toCountry(String country) {
        if (country == null || country.isEmpty()) return null;
        return gson.fromJson(country, Country.class);
    }

}
