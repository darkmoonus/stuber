package uet.vav.stuber.cores;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;

/**
 * Created by darkmoonus on 4/9/16.
 */
public abstract class CoreFragment extends Fragment implements View.OnClickListener {

    private static final long serialVersionUID = 7080889824192321168L;
    protected FragmentManager mFragmentManager;
    protected CoreActivity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getFragmentManager();
        mContext = (CoreActivity) getActivity();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public abstract void onClick(View view);

    public void finishFragment() {
        try {
            mFragmentManager.popBackStack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void initModels();

    protected abstract void initViews(View view);

    protected abstract void initListener();

    protected abstract void initAnimations();
}
