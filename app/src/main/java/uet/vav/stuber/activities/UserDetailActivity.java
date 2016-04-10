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

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;

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
    private User user;
    private RatingBar rating;
    private MyTextView tvRatingString;
    private MyTextView tvRateHire;
    private MyTextView tvEmail;
    private MyTextView tvAddress;
    private MyTextView tvSkills;
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
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.getInBackground(userID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, com.parse.ParseException e) {
                if (e == null) {
                    String id = object.getObjectId();
                    String email = object.getString(Constants.PROFILE_EMAIl);
                    String name = object.getString(Constants.PROFILE_NAME);
                    String skills = object.getString(Constants.PROFILE_SKILLS);
                    String experience = object.getString(Constants.PROFILE_EXPERIENCE);
                    double hireRate = object.getDouble(Constants.PROFILE_HIRERATE);
                    String address = object.getString(Constants.PROFILE_ADRESS);
                    String project = object.getString(Constants.PROFILE_PROJECTS);
                    double ratingX = object.getDouble(Constants.PROFILE_RATING);
                    int age = object.getInt("Old");

                    tvRatingString.setText(rating + "");
                    tvRateHire.setText(hireRate + "/hr");
                    tvAddress.setText(address);
                    tvProject.setText(project);
                    tvExperiences.setText(experience);
                    tvSkills.setText(skills);
                    tvEmail.setText(email);
                    rating.setRating((float) ratingX);
                } else {
                    e.printStackTrace();
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
        rating = (RatingBar) findViewById(R.id.ratingBar);
        tvRatingString = (MyTextView) findViewById(R.id.tvRating);
        tvRateHire = (MyTextView) findViewById(R.id.ratingHire);
        tvEmail = (MyTextView) findViewById(R.id.email);
        tvAddress = (MyTextView) findViewById(R.id.address);
        tvSkills = (MyTextView) findViewById(R.id.skills);
        tvProject = (MyTextView) findViewById(R.id.projects);
        tvExperiences = (MyTextView) findViewById(R.id.experience);
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
