package com.beastwall.world.fetcher;

import android.util.Log;

import com.beastwall.localisation.Localisation;
import com.beastwall.localisation.model.complex_fields.Form;
import com.beastwall.storagemanager.FileSaver;
import com.beastwall.storagemanager.utils.StorageUtils;
import com.beastwall.world.cache.SVGCache;
import com.beastwall.world.database.SQLITEDatabase;
import com.beastwall.world.database.model.CountryDB;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;

import java.io.FileInputStream;

/**
 * @author Abdel Wadoud Rasmi
 */
public class DisplayFlagTask extends BackgroundTask<CountryDB, SVG> {
    private SVGImageView imageView;
    private SQLITEDatabase db;

    public DisplayFlagTask(SVGImageView imageView, SQLITEDatabase db) {
        this.imageView = imageView;
        this.db = db;
    }

    /**
     * get instance
     */
    public static DisplayFlagTask get(SVGImageView imageView, SQLITEDatabase db) {
        return new DisplayFlagTask(imageView, db);
    }

    @Override
    public SVG doInBackground(CountryDB country) {
        if (StorageUtils.isStoredLocally(country.getFlag())) {
            //Case when the file is already saved locally
            try {
                SVG svg = SVG.getFromInputStream(new FileInputStream(country.getFlag()));
                SVGCache.getInstance().put(country.getFlag(), svg);
                return svg;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            byte[] flag = Localisation.getCountryFlagSVG(country.getCountry().getIso2(), Form.SQUARE);
            String path = FileSaver.get().save(flag, imageView.getContext().getCacheDir().getPath(), country.getCountry().getIso2() + ".svg");
            //
            country.setFlag(path);
            db.countryDAO().update(country);
            try {
                SVG svg = SVG.getFromInputStream(new FileInputStream(country.getFlag()));
                SVGCache.getInstance().put(country.getFlag(), svg);
                return svg;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void doInMainThread(SVG svg) {
        if (svg != null)
            imageView.setSVG(svg);
    }

    @Override
    public void progress(long totalBytes, long numberOfReadBytes, int percentage) {

    }
}
