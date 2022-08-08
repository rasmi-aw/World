package com.beastwall.world;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.beastwall.localisation.Localisation;
import com.beastwall.localisation.model.Country;
import com.beastwall.world.adapter.CountriesAdapter;

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
            List<Country> countries = Localisation.getAllCountriesStatesAndCities();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setAdapter(new CountriesAdapter(countries));
                }
            });
        }).start();

    }
}