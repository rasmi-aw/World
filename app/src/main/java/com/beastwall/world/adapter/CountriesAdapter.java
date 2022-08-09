package com.beastwall.world.adapter;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beastwall.localisation.Localisation;
import com.beastwall.localisation.model.Country;
import com.beastwall.localisation.model.complex_fields.Form;
import com.beastwall.storagemanager.FileSaver;
import com.beastwall.world.R;
import com.beastwall.world.database.model.CountryDB;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author AbdelWadoud Rasmi
 */
public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryHolder> {
    public List<CountryDB> countries;

    public CountriesAdapter(List<CountryDB> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        CountryDB countryDB = countries.get(position);
        Country country = countryDB.getCountry();
        holder.name.setText(country.getName());
        holder.nameNative.setText(country.getNativeL());

        new Thread(() -> {

            try {
                SVG flag = SVG.getFromInputStream(new FileInputStream(countryDB.getFlag()));
                new Handler(Looper.getMainLooper()).post(() -> {
                    holder.flag.setSVG(flag);

                });
            } catch (SVGParseException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }).start();

    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }

    public static class CountryHolder extends RecyclerView.ViewHolder {
        public TextView name, nameNative;
        public SVGImageView flag;

        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            nameNative = itemView.findViewById(R.id.name_native);
            flag = itemView.findViewById(R.id.flag);
        }
    }
}
