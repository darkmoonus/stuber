package uet.vav.stuber.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import uet.vav.stuber.R;
import uet.vav.stuber.activities.MainActivity;
import uet.vav.stuber.adapters.DirectListAdapter;
import uet.vav.stuber.cores.CoreFragment;
import uet.vav.stuber.models.User;

public class DirectFragment extends CoreFragment implements Serializable {

    private UltimateRecyclerView mDirectListView;
    private DirectListAdapter mDirectListAdapter;
    private ArrayList<User> mUserList;
    private int limit = 10;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_direct, container, false);
        initViews(view);
        initListener();
        initModels();
        initAnimations();
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

            }
        });

//        mListView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
//            @Override
//            public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
//                if (scrollY > mScroll) {
//                    mAddRoom.hide();
//                } else {
//                    mAddRoom.show();
//                }
//                mScroll = scrollY;
//            }
//            @Override
//            public void onDownMotionEvent() {
//
//            }
//            @Override
//            public void onUpOrCancelMotionEvent(ObservableScrollState observableScrollState) {
//                if (observableScrollState == ObservableScrollState.DOWN) {
//                    loge("End Down");
//                } else if (observableScrollState == ObservableScrollState.UP) {
//                    loge("End Up");
//                } else if (observableScrollState == ObservableScrollState.STOP) {
//                    loge("Stop");
//                }
//            }
//        });
    }

    public void loadData(int page, int limit) {

    }

    @Override
    public void onClick(View view) {
    }

    @Override
    protected void initModels() {
        mUserList = new ArrayList<>();
        mUserList.add(new User("1", "Vũ Việt Anh", "hunter@darkmoon.us", 22, "175 Xuân Thuỷ, Cầu Giấy, Hà Nội", "Android, Java, C++, MySQL", 2.87, 17.5, "175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL 175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL", "175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL"));
        mUserList.add(new User("1", "Vũ Việt Anh", "hunter@darkmoon.us", 22, "175 Xuân Thuỷ, Cầu Giấy, Hà Nội", "Android, Java, C++, MySQL", 2.87, 17.5, "175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL 175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL", "175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL"));
        mUserList.add(new User("1", "Vũ Việt Anh", "hunter@darkmoon.us", 22, "175 Xuân Thuỷ, Cầu Giấy, Hà Nội", "Android, Java, C++, MySQL", 3.87, 17.5, "175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL 175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL", "175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL"));
        mUserList.add(new User("1", "Vũ Việt Anh", "hunter@darkmoon.us", 22, "175 Xuân Thuỷ, Cầu Giấy, Hà Nội", "Android, Java, C++, MySQL", 1.87, 17.5, "175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL 175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL", "175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL"));
        mUserList.add(new User("1", "Vũ Việt Anh", "hunter@darkmoon.us", 22, "175 Xuân Thuỷ, Cầu Giấy, Hà Nội", "Android, Java, C++, MySQL", 3.87, 17.5, "175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL 175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL", "175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL175 Xuân Thuỷ, Cầu Giấy, Hà Nội\", \"Android, Java, C++, MySQL"));
        initListView();
        loadData(page, limit);
    }

    @Override
    protected void initViews(View v) {
        mDirectListView = (UltimateRecyclerView) v.findViewById(R.id.directList);
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
