package kasper.android.store_manager.core;

import android.app.Application;

import kasper.android.store_manager.helpers.DatabaseHelper;

/**
 * Created by keyhan1376 on 12/17/2017.
 */

public class Core extends Application {

    private static Core instance;
    public static Core getInstance() {
        return instance;
    }

    private DatabaseHelper databaseHelper;
    public DatabaseHelper getDatabaseHelper() {
        return this.databaseHelper;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public void startMachine(Runnable callback) {
        this.databaseHelper = new DatabaseHelper(this);
        callback.run();
    }

    public float dpToPx(float dp) {
        return this.getResources().getDisplayMetrics().density * dp;
    }

    public int getScreenWidth() {
        return this.getResources().getDisplayMetrics().widthPixels;
    }
}
