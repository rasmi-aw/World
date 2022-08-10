package com.beastwall.world.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beastwall.world.R;
import com.beastwall.world.activity.MainActivity;
import com.beastwall.world.database.DataBase;
import com.beastwall.world.database.SQLITEDatabase;
import com.beastwall.world.database.model.CountryDB;
import com.beastwall.world.fetcher.CountriesTask;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DownloadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DownloadFragment extends Fragment {
    private static DownloadFragment instance;
    private SQLITEDatabase db;

    private DownloadFragment(SQLITEDatabase db) {
        this.db = db;
    }

    /**
     * get singleton instance
     */
    public static DownloadFragment newInstance(SQLITEDatabase db) {
        if (instance == null)
            instance = new DownloadFragment(db);
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
        new CountriesTask(db) {
            @Override
            public void doInMainThread(List<CountryDB> countryDBS) {
                ((MainActivity) getActivity()).displayFragment(CountriesFragment.newInstance(countryDBS), R.id.main_container, false);
            }

            @Override
            public void progress(long totalBytes, long numberOfReadBytes, int percentage) {

            }

        }.start(getContext().getCacheDir().getPath());


    }
}