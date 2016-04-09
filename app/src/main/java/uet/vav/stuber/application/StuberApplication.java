package uet.vav.stuber.application;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseACL;

import uet.vav.stuber.utils.ParseUtils;

/**
 * Created by darkmoonus on 4/9/16.
 */
public class StuberApplication extends Application {
    private static StuberApplication mInstance;

    public static String USER_ID;

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
//        Parse.initialize(this);
        ParseUtils.registerParse(this);

        // Anonymously use application
        //ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        mInstance = this;
    }

    public static synchronized StuberApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }
}
