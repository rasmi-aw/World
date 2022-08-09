package com.beastwall.world;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.beastwall.localisation.Localisation;
import com.beastwall.localisation.model.Country;
import com.beastwall.localisation.model.complex_fields.Form;
import com.beastwall.storagemanager.FileSaver;
import com.beastwall.world.adapter.CountriesAdapter;
import com.beastwall.world.database.DataBase;
import com.beastwall.world.database.SQLITEDatabase;
import com.beastwall.world.database.dao.CountryDAO;
import com.beastwall.world.database.model.CountryDB;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abdel Wadoud Rasmi
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.countries_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //
        new Thread(() -> {
            List<CountryDB> countryDBS = new ArrayList<>();
            List<Country> countries = Localisation.getAllCountriesStatesAndCities();
            SQLITEDatabase db = DataBase.get(this);
            CountryDAO countryDAO = db.countryDAO();
            String parentDir = getCacheDir().getPath();
            for (Country c : countries) {
                byte[] dz = Localisation.getCountryFlagSVG(c.getIso2(), Form.SQUARE);
                String path = FileSaver.get().save(dz, parentDir, c.getIso2() + ".svg");
                CountryDB current = new CountryDB(c, path);
                countryDBS.add(current);
                countryDAO.save(current);
            }
            Log.v("rasmi", "" + countryDAO.getCount());
            new Handler(Looper.getMainLooper()).post(() -> recyclerView.setAdapter(new CountriesAdapter(countryDBS)));
        }).start();

    }
}