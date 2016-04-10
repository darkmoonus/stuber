package uet.vav.stuber.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.List;

import uet.vav.stuber.R;
import uet.vav.stuber.application.StuberApplication;
import uet.vav.stuber.cores.CoreActivity;
import uet.vav.stuber.customizes.MyTextView;
import uet.vav.stuber.models.User;
import uet.vav.stuber.utils.Constants;

public class UserDetailActivity extends CoreActivity {

    private String userID;
    private ActionBar actionBar;
    private FloatingActionButton fab;
    private RatingBar rating;
    private MyTextView tvRatingString;
    private MyTextView tvRateHire;
    private MyTextView tvEmail;
    private MyTextView tvAddress;
    private MyTextView tvSkills;
    private MyTextView tvExperiences;
    private MyTextView tvProject;
    private LinearLayout contentLayout, loadingLayout;

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
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("objectId", userID);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    ParseObject object = objects.get(0);
                    String email = object.getString(Constants.PROFILE_EMAIl);
                    String skills = object.getString(Constants.PROFILE_SKILLS);
                    String experience = object.getString(Constants.PROFILE_EXPERIENCE);
                    double hireRate = object.getDouble(Constants.PROFILE_HIRERATE);
                    String address = object.getString(Constants.PROFILE_ADRESS);
                    String project = object.getString(Constants.PROFILE_PROJECTS);
                    double ratingX = object.getDouble(Constants.PROFILE_RATING);

                    tvRatingString.setText(ratingX + "/5.0");
                    tvRateHire.setText(hireRate + "$/hr");
                    tvAddress.setText(address);
                    tvProject.setText(project);
                    tvExperiences.setText(experience);
                    tvSkills.setText(skills);
                    tvEmail.setText(email);
                    rating.setRating((float) ratingX);
                    LayerDrawable stars = (LayerDrawable) rating.getProgressDrawable();
                    stars.getDrawable(2).setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                    loadingLayout.setVisibility(View.GONE);
                    contentLayout.setVisibility(View.VISIBLE);
                } else {
                    // error
                }
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        userID = intent.getStringExtra("uid");
        String uName = intent.getStringExtra("name");
        actionBar.setTitle(uName);
        setStatusBarColor(R.color.colorPrimary);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(UserDetailActivity.this, ChatActivity.class);
                intent.putExtra("uid", StuberApplication.USER_ID);
                intent.putExtra("pid", userID);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initViews() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        rating = (RatingBar) findViewById(R.id.ratingBar);
        tvRatingString = (MyTextView) findViewById(R.id.tvRating);
        tvRateHire = (MyTextView) findViewById(R.id.ratingHire);
        tvEmail = (MyTextView) findViewById(R.id.email);
        tvAddress = (MyTextView) findViewById(R.id.address);
        tvSkills = (MyTextView) findViewById(R.id.skills);
        tvProject = (MyTextView) findViewById(R.id.projects);
        tvExperiences = (MyTextView) findViewById(R.id.experience);
        contentLayout = (LinearLayout) findViewById(R.id.content);
        loadingLayout = (LinearLayout) findViewById(R.id.loadingLayout);
    }

    @Override
    public void initModels() {
        setupToolbar();

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
