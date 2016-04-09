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

import uet.vav.stuber.R;
import uet.vav.stuber.cores.CoreActivity;

public class UserDetailActivity extends CoreActivity {

    private String userID;
    private ActionBar actionBar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        initViews();
        initModels();
        initListeners();
        initAnimations();

        setStatusBarColor(R.color.colorPrimary);
        loadUserData();
    }

    public void loadUserData() {
        actionBar.setTitle(userID);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                break;
        }
    }

    @Override
    public void initViews() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void initModels() {
        setupToolbar();
        Intent intent = getIntent();
        userID = intent.getStringExtra("uid");
    }

    @Override
    public void initListeners() {
        fab.setOnClickListener(this);
    }

    @Override
    public void initAnimations() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
