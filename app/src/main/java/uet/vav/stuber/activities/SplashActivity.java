package uet.vav.stuber.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import uet.vav.stuber.R;
import uet.vav.stuber.cores.CoreActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends CoreActivity {
    private static final String LOG_TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

            }
        });

        System.err.println(testObject);
        Log.wtf(LOG_TAG, testObject.toString());

    }
}
