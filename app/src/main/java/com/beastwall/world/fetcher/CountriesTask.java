package com.beastwall.world.fetcher;

import android.content.Context;
import android.os.AsyncTask;

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
public abstract class  CountriesTask extends BackgroundTask<String, List<CountryDB>> {
    private SQLITEDatabase db;

    public CountriesTask(SQLITEDatabase db) {
        this.db = db;
    }

    @Override
    public List<CountryDB> doInBackground(String parentDir) {
        List<CountryDB> countryDBS = new ArrayList<>();
        List<Country> countries = Localisation.getAllCountriesStatesAndCities();
        CountryDAO countryDAO = db.countryDAO();
        //
        for (Country c : countries) {
            byte[] dz = Localisation.getCountryFlagSVG(c.getIso2(), Form.SQUARE);
            String path = FileSaver.get().save(dz, parentDir, c.getIso2() + ".svg");
            CountryDB current = new CountryDB(c, path);
            countryDBS.add(current);
            countryDAO.save(current);
        }
        return countryDBS;
    }

}
