package com.beastwall.world.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beastwall.localisation.model.Country;
import com.beastwall.world.R;
import com.beastwall.world.activity.MainActivity;
import com.beastwall.world.cache.SVGCache;
import com.beastwall.world.database.DataBase;
import com.beastwall.world.database.model.CountryDB;
import com.beastwall.world.fetcher.DisplayFlagTask;
import com.beastwall.world.fragment.StatesFragment;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;

import java.util.List;

/**
 * @author AbdelWadoud Rasmi
 */
public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryHolder> {
    private List<CountryDB> countries;


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
        //
        SVG svg = null;
        if (countryDB.getFlag() != null)
        svg = SVGCache.getInstance().get(countryDB.getFlag());
        //
        if (svg != null)
            holder.flag.setSVG(svg);
        else
            DisplayFlagTask
                    .get(holder.flag, DataBase.get(holder.flag.getContext()))
                    .start(countryDB);
        //
        holder.itemView.setOnClickListener(view -> {
            StatesFragment fragment = StatesFragment.getInstance(countries.get(position).getCountry());
            MainActivity.instance.displayFragment(fragment, R.id.main_container, true);

        });

    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }

    public class CountryHolder extends RecyclerView.ViewHolder {
        public TextView name, nameNative;
        public SVGImageView flag;

        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            //
            name = itemView.findViewById(R.id.name);
            nameNative = itemView.findViewById(R.id.name_native);
            flag = itemView.findViewById(R.id.flag);
        }
    }
}
