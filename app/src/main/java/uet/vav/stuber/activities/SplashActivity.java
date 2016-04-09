package uet.vav.stuber.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import uet.vav.stuber.R;
import uet.vav.stuber.cores.CoreActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends CoreActivity {

    private Button mBtLogin;

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
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void initViews() {
        mBtLogin = (Button) findViewById(R.id.btLogin);
    }

    @Override
    public void initModels() {

    }

    @Override
    public void initListeners() {
        mBtLogin.setOnClickListener(this);
    }

    @Override
    public void initAnimations() {

    }
}
