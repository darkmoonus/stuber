package uet.vav.stuber.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uet.vav.stuber.R;
import uet.vav.stuber.activities.MainActivity;
import uet.vav.stuber.adapters.DirectListAdapter;
import uet.vav.stuber.cores.CoreFragment;
import uet.vav.stuber.customizes.MyGifView;
import uet.vav.stuber.models.User;
import uet.vav.stuber.utils.Constants;

public class DirectFragment extends CoreFragment implements Serializable {

    private UltimateRecyclerView mDirectListView;
    private DirectListAdapter mDirectListAdapter;
    private LinearLayout mEmptyView;
    private ArrayList<User> mUserList = new ArrayList<>();
    private int limit = 10;
    private int page = 1;
    protected MyGifView mLoadingView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_direct, container, false);
        initViews(view);
        initListener();
        initModels();
        initAnimations();

        initListView();
        loadData(page, limit);
        return view;
    }

    public void initListView() {
        mDirectListAdapter = new DirectListAdapter(mUserList, mActivity);
        mDirectListView.setHasFixedSize(true);
        mDirectListView.setLayoutManager(new LinearLayoutManager(mActivity));
        mDirectListView.setAdapter(mDirectListAdapter);
        mDirectListView.enableLoadmore();
        mDirectListView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {

            }
        });
        mDirectListView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
//                loadData(page, limit);
            }
        });
    }

    public void loadData(int page, int limit) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.findInBackground(new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> userObjects, ParseException error) {
                        if (userObjects != null) {
                            mDirectListAdapter.clearData();
                            for (int i = 0; i < userObjects.size(); i++) {
                                ParseUser pu = userObjects.get(i);
                                String id = pu.getObjectId();
                                String email = pu.getString(Constants.PROFILE_EMAIl);
                                String name = pu.getString(Constants.PROFILE_NAME);
                                String skills = pu.getString(Constants.PROFILE_SKILLS);
                                String experience = pu.getString(Constants.PROFILE_EXPERIENCE);
                                double hireRate = pu.getDouble(Constants.PROFILE_HIRERATE);
                                String address = pu.getString(Constants.PROFILE_ADRESS);
                                String project = pu.getString(Constants.PROFILE_PROJECTS);
                                double rating = pu.getDouble(Constants.PROFILE_RATING);
                                int age = pu.getInt("Old");
                                User u = new User(id, name, email, age, address, skills, rating, hireRate, experience, project);
                                u.randomTag = (int) Math.random() * 8 + 1;
                                mDirectListAdapter.addItem(u);
                            }

                            mActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mEmptyView.setVisibility(View.GONE);
                                    mDirectListView.setVisibility(View.VISIBLE);
                                }
                            });
                        } else {

                        }
                    }
                });
            }
        }).start();
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
        mDirectListView = (UltimateRecyclerView) v.findViewById(R.id.directList);
        mEmptyView = (LinearLayout) v.findViewById(R.id.empty_view);
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
    public static DirectFragment mInstance;

    public static CoreFragment getInstance(MainActivity activity) {
        if (mInstance == null) {
            mInstance = new DirectFragment();
        }
        mActivity = activity;
        return mInstance;
    }
}
