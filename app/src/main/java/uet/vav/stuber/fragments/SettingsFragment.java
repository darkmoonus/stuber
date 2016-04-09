package uet.vav.stuber.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import android.widget.Button;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import uet.vav.stuber.R;
import uet.vav.stuber.activities.MainActivity;
import uet.vav.stuber.activities.SplashActivity;
import uet.vav.stuber.cores.CoreFragment;

public class SettingsFragment extends CoreFragment implements Serializable {
    private static final String LOG_TAG = "SettingsFragment";
    private Button mBtnLogout;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        initViews(view);
        initListener();
        initModels();
        initAnimations();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_log_out:
                mActivity.showProgressDialog("Logout", "Logging out...");
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.wtf("ParseUser.getCurrentUser()", String.valueOf(ParseUser.getCurrentUser()));
                        if (e == null) {
                            Log.wtf(LOG_TAG, "Log out done!");
                            startActivity(new Intent(mContext, SplashActivity.class));
                        } else {
                            Log.wtf(LOG_TAG, "Can not log out!");
                        }
                        mActivity.removePreviousDialog("Logout");
                    }
                });
                break;
        }
    }

    @Override
    protected void initModels() {
    }

    @Override
    protected void initViews(View v) {
        mBtnLogout = (Button) view.findViewById(R.id.btn_log_out);
    }

    @Override
    protected void initAnimations() {

    }

    @Override
    protected void initListener() {
        mBtnLogout.setOnClickListener(this);
    }

    public static final long serialVersionUID = 6036846677812555352L;
    public static MainActivity mActivity;
    public static SettingsFragment mInstance;

    public static CoreFragment getInstance(MainActivity activity) {
        if (mInstance == null) {
            mInstance = new SettingsFragment();
        }
        mActivity = activity;
        return mInstance;
    }

}
