package uet.vav.stuber.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import uet.vav.stuber.R;
import uet.vav.stuber.application.StuberApplication;
import uet.vav.stuber.cores.CoreActivity;
import uet.vav.stuber.utils.Network;

public class PairedActivity extends CoreActivity {
    private String userID, partnerId;
    private Button mBtAccept, mBtDecline;
    private String mTutee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paired);
        initViews();
        initModels();
        initListeners();
        initAnimations();

        Intent intent = getIntent();
        mTutee = intent.getStringExtra("message");
        try {
            mTutee = (new JSONObject(mTutee)).getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accept:
                try {
                    JSONObject data = new JSONObject();
                    data.put("tutor", ParseUser.getCurrentUser().getEmail());
                    data.put("tutee", mTutee);
                    HttpEntity entity = new StringEntity(data.toString());
                    Log.d("pushedData", data.toString());

                    Network.post(this, "https://api.parse.com/1/functions/sendConfirm",
                            entity, new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    Log.d("onSuccess", new String(responseBody));

                                    Intent intent = new Intent(PairedActivity.this, ChatActivity.class);
                                    intent.putExtra("uid", StuberApplication.USER_ID);
                                    intent.putExtra("pid", partnerId);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                    showProgressDialogWithPositiveButton("error", new String(responseBody));
                                }
                            });

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
