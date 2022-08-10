package com.beastwall.world.activity;

import android.os.Bundle;

import com.beastwall.world.R;
import com.beastwall.world.database.DataBase;
import com.beastwall.world.database.SQLITEDatabase;
import com.beastwall.world.fragment.DownloadFragment;

/**
 * @author Abdel Wadoud Rasmi
 */
public class MainActivity extends FragmentActivity {
    public static MainActivity instance;
    private SQLITEDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        //
        db = DataBase.get(this);

        //display main content
        displayFragment(DownloadFragment.newInstance(db), R.id.main_container, false);

    }


}