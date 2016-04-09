package uet.vav.stuber.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.io.Serializable;

import uet.vav.stuber.R;
import uet.vav.stuber.activities.MainActivity;
import uet.vav.stuber.adapters.DirectListAdapter;
import uet.vav.stuber.cores.CoreFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DirectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DirectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DirectFragment extends CoreFragment implements Serializable {

    private UltimateRecyclerView mDirectListView;
    private DirectListAdapter mDirectListAdapter;

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
        mDirectListAdapter = new DirectListAdapter(IRApplication.mRoomList, HomeActivity.this);
        mDirectListView.setHasFixedSize(true);
        mDirectListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mDirectListView.setAdapter(mDirectListAdapter);

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

    @Override
    public void onClick(View view) {
    }

    @Override
    protected void initModels() {
        initListView();
    }

    @Override
    protected void initViews(View v) {
        mDirectListView = (UltimateRecyclerView) v.findViewById(R.id.);
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

    public static DirectFragment getInstance(MainActivity activity) {
        if (mInstance == null) {
            mInstance = new DirectFragment();
        }
        mActivity = activity;
        return mInstance;
    }
}
