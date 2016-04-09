package uet.vav.stuber.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import uet.vav.stuber.R;
import uet.vav.stuber.application.StuberApplication;
import uet.vav.stuber.cores.CoreActivity;

public class PairedActivity extends CoreActivity {
    private String userID, partnerId;
    private Button mBtAccept, mBtDecline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paired);
        initViews();
        initModels();
        initListeners();
        initAnimations();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accept:
                Intent intent = new Intent(PairedActivity.this, ChatActivity.class);
                intent.putExtra("uid", StuberApplication.USER_ID);
                intent.putExtra("pid", partnerId);
                startActivity(intent);
                break;
            case R.id.decline:
                finish();
                break;
        }
    }

    @Override
    public void initViews() {
        mBtAccept = (Button) findViewById(R.id.accept);
        mBtDecline = (Button) findViewById(R.id.decline);
    }

    @Override
    public void initModels() {
        Intent intent = getIntent();
        userID = intent.getStringExtra("uid");
        partnerId = intent.getStringExtra("pid");
        setStatusBarColor(R.color.gray);
    }


    @Override
    public void initListeners() {
        mBtAccept.setOnClickListener(this);
        mBtDecline.setOnClickListener(this);
    }

    @Override
    public void initAnimations() {

    }
}
