package com.beastwall.world.database.model;

import androidx.room.Entity;

import com.beastwall.localisation.model.Country;

/**
 * @author Abdel Wadoud Rasmi
 * <p>
 * An SQLITE table representation to store Countries
 */
@Entity(tableName = "country", primaryKeys = {"id"}, ignoredColumns = {"states"})
public class CountryDB extends Country {

}
