package uet.vav.stuber.application;

import android.app.Application;

/**
 * Created by darkmoonus on 4/9/16.
 */
public class StuberApplication extends Application {
    public static String USER_ID;

    private static StuberApplication mInstance = null;
    public static synchronized StuberApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
