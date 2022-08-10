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

import com.beastwall.localisation.model.Country;
import com.beastwall.world.R;
import com.beastwall.world.adapter.StatesAdapter;

/**
 * @author AbdelWadoud Rasmi
 */
public class StatesFragment extends Fragment {
    private Country country;
    public RecyclerView recyclerView;

    public StatesFragment(Country country) {
        this.country = country;
    }

    /**
     * get Instance
     */
    public static StatesFragment getInstance(Country country) {
        return new StatesFragment(country);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_states, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.states_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new StatesAdapter(country.getStates()));
    }
}