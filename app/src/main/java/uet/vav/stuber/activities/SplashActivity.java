package uet.vav.stuber.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;
import uet.vav.stuber.R;
import uet.vav.stuber.cores.CoreActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends CoreActivity {
    private static final String LOG_TAG = "SplashActivity";
    private Button mBtLogin, mBtSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        initViews();
        initModels();
        initListeners();
        initAnimations();

        // Check user session cache
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            sendUserInfoToActivity(currentUser, MainActivity.class);
        } else {
            Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initViews() {
    }

    @Override
    public void initModels() {

    }

    @Override
    public void initListeners() {
    }

    @Override
    public void initAnimations() {
    }
}
