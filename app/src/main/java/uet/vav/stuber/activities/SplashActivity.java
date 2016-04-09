package uet.vav.stuber.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import uet.vav.stuber.R;
import uet.vav.stuber.application.StuberApplication;
import uet.vav.stuber.cores.CoreActivity;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btLogin:
                Intent intentLogin = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.btSignup:
                Intent intentRegister = new Intent(SplashActivity.this, SignUpActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    @Override
    public void initViews() {
        mBtLogin = (Button) findViewById(R.id.btLogin);
        mBtSignup = (Button) findViewById(R.id.btSignup);
    }

    @Override
    public void initModels() {

    }

    @Override
    public void initListeners() {
        mBtLogin.setOnClickListener(this);
        mBtSignup.setOnClickListener(this);
    }

    @Override
    public void initAnimations() {
    }
}
