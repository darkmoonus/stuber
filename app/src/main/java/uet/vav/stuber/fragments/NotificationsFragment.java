package uet.vav.stuber.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.Serializable;

import uet.vav.stuber.R;
import uet.vav.stuber.activities.MainActivity;
import uet.vav.stuber.cores.CoreFragment;
import uet.vav.stuber.customizes.MyGifView;

public class NotificationsFragment extends CoreFragment implements Serializable {

    private LinearLayout contentLayout, loadingLayout;
    protected MyGifView mLoadingView;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        initViews(view);
        initListener();
        initModels();
        initAnimations();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contentLayout.setVisibility(View.VISIBLE);
                        loadingLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();

        return view;
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    protected void initModels() {
        mLoadingView.setMovieResource(R.mipmap.progress);
    }

    @Override
    protected void initViews(View v) {
        contentLayout = (LinearLayout) v.findViewById(R.id.content);
        loadingLayout = (LinearLayout) v.findViewById(R.id.loadingLayout);
        mLoadingView = (MyGifView) v.findViewById(R.id.progressLoading);
    }

    @Override
    protected void initAnimations() {

    }

    @Override
    protected void initListener() {
    }

    public static final long serialVersionUID = 6036846677812555352L;
    public static MainActivity mActivity;
    public static NotificationsFragment mInstance;

    public static CoreFragment getInstance(MainActivity activity) {
        if (mInstance == null) {
            mInstance = new NotificationsFragment();
        }
        mActivity = activity;
        return mInstance;
    }
}
