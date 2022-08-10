package com.beastwall.world.fetcher;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.beastwall.localisation.Localisation;
import com.beastwall.localisation.model.Country;
import com.beastwall.localisation.model.complex_fields.Form;
import com.beastwall.storagemanager.FileSaver;
import com.beastwall.world.database.DataBase;
import com.beastwall.world.database.SQLITEDatabase;
import com.beastwall.world.database.dao.CountryDAO;
import com.beastwall.world.database.model.CountryDB;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * A task to fetch al countries
 */
public abstract class CountriesTask extends BackgroundTask<String, List<CountryDB>> {
    private SQLITEDatabase db;

    public CountriesTask(SQLITEDatabase db) {
        this.db = db;
    }

    @Override
    public List<CountryDB> doInBackground(String parentDir) {
        List<CountryDB> countryDBS = new ArrayList<>();
        CountryDAO countryDAO = db.countryDAO();
        //Checking is there's countries in db
        List<Country> countries;
        if (countryDAO.getCount() < 1)
            countries = Localisation.getAllCountriesStatesAndCities();
        else countries = new ArrayList<>();
        //
        if (!countries.isEmpty())
            for (Country c : countries) {
                CountryDB current = new CountryDB(c, null);
                countryDBS.add(current);
                countryDAO.save(current);
            }
        else
            countryDBS = countryDAO.getAll();

        return countryDBS;
    }

}
