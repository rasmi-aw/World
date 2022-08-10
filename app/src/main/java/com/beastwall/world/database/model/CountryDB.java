package com.beastwall.world.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.beastwall.localisation.model.Country;
import com.beastwall.localisation.model.State;
import com.beastwall.localisation.model.complex_fields.TimeZone;
import com.beastwall.localisation.model.complex_fields.Translations;

import java.util.List;

/**
 * @author Abdel Wadoud Rasmi
 * <p>
 * An SQLITE table representation to store Countries
 */
@Entity(tableName = "country")
public class CountryDB {

    @PrimaryKey
    @NonNull
    private Country country;

    private String flag;


    public Country getCountry() {
        return country;
    }

    public String getFlag() {
        return flag;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public CountryDB(Country country, String flag) {
        this.country = country;
        this.flag = flag;
    }
}
