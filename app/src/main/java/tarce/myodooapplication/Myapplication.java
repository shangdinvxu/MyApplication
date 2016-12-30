package tarce.myodooapplication;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Daniel.Xu on 2016/12/30.
 */

public class Myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
