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

public class ChatActivity extends CoreActivity {

//    private ActionBar actionBar;
    private String userID, partnerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initViews();
        initModels();
        initListeners();
        initAnimations();
        loadConversation();
    }

    private void setupToolbar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        Intent intent = getIntent();
        userID = intent.getStringExtra("uid");
        partnerId = intent.getStringExtra("pid");
        setStatusBarColor(R.color.conversation_chat);
//        actionBar.setTitle(userID);
    }

    private void loadConversation() {

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

    }

    @Override
    public void initModels() {
        setupToolbar();

    }


    @Override
    public void initListeners() {

    }

    @Override
    public void initAnimations() {

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
