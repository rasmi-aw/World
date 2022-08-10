package com.beastwall.world.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beastwall.world.R;
import com.beastwall.world.adapter.CountriesAdapter;
import com.beastwall.world.database.model.CountryDB;

import java.util.List;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * Countries fragment to display all countries
 */
public class CountriesFragment extends Fragment {
    public static CountriesFragment instance;
    private List<CountryDB> countries;
    private RecyclerView recyclerView;

    //Constructor
    public CountriesFragment(List<CountryDB> countries) {
        this.countries = countries;
    }

    /**
     * new instance of this fragment
     */
    public static CountriesFragment newInstance(@Nullable List<CountryDB> countries) {
        if (instance == null)
            instance = new CountriesFragment(countries);
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //
        recyclerView = view.findViewById(R.id.countries_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CountriesAdapter(countries));

    }
}