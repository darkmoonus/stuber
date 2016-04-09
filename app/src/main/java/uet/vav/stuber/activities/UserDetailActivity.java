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
import android.widget.RatingBar;

import uet.vav.stuber.R;
import uet.vav.stuber.application.StuberApplication;
import uet.vav.stuber.cores.CoreActivity;
import uet.vav.stuber.customizes.MyTextView;
import uet.vav.stuber.models.User;

public class UserDetailActivity extends CoreActivity {

    private String userID;
    private ActionBar actionBar;
    private FloatingActionButton fab;
    private User user;
    private RatingBar rating;
    private MyTextView tvRatingString;
    private MyTextView tvRateHire;
    private MyTextView tvEmail;
    private MyTextView tvAddress;
    private MyTextView tvExperiences;
    private MyTextView tvProject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        initViews();
        initModels();
        initListeners();
        initAnimations();

        loadUserData();
    }

    public void loadUserData() {

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
//                Intent intent = new Intent(UserDetailActivity.this, ChatActivity.class);
                Intent intent = new Intent(UserDetailActivity.this, PairedActivity.class);
                intent.putExtra("uid", StuberApplication.USER_ID);
                intent.putExtra("pid", userID);
                startActivity(intent);
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
        setStatusBarColor(R.color.colorPrimary);
        actionBar.setTitle(userID);
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
