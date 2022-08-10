package com.beastwall.world.cache;

import android.util.LruCache;

import com.caverock.androidsvg.SVG;

/**
 * @author AbdelWadoud Rasmi
 *
 * THe purpose of this Class is to cache SvgFiles
 */
public class SVGCache extends LruCache<String, SVG> {
    private static SVGCache cache;

    /**
     * get Instance
     */
    public static SVGCache getInstance() {
        if (cache == null)
            cache = new SVGCache(1000);
        return cache;
    }

    public SVGCache(int maxSize) {
        super(maxSize);
    }

}
