package uet.vav.stuber.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import uet.vav.stuber.R;
import uet.vav.stuber.activities.MainActivity;
import uet.vav.stuber.cores.CoreFragment;

public class SettingsFragment extends CoreFragment implements Serializable {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initViews(view);
        initListener();
        initModels();
        initAnimations();
        return view;
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    protected void initModels() {
    }

    @Override
    protected void initViews(View v) {

    }

    @Override
    protected void initAnimations() {

    }

    @Override
    protected void initListener() {
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
