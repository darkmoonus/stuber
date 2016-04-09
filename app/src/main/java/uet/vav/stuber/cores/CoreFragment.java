package uet.vav.stuber.cores;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.parse.ParseUser;
import uet.vav.stuber.utils.Constants;

/**
 * Created by darkmoonus on 4/9/16.
 */
public abstract class CoreFragment extends Fragment implements View.OnClickListener {
    private static final String LOG_TAG = "CoreFragment";

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

    public void sendUserInfoToActivity(ParseUser user, final Class<? extends Activity> activity) {
        if (user == null) {
            Log.wtf(LOG_TAG, "User is null.");
        } else {
            Intent intent = new Intent(mContext, activity);
            intent.putExtra(Constants.PROFILE_ID, user.getObjectId());
            intent.putExtra(Constants.PROFILE_USERNAME, user.getUsername());
            intent.putExtra(Constants.PROFILE_NAME, user.getString(Constants.PROFILE_NAME));
            intent.putExtra(Constants.PROFILE_EMAIl, user.getEmail());
            intent.putExtra(Constants.PROFILE_AVATAR_URL, user.getString(Constants.PROFILE_AVATAR_URL));
            mContext.finish();
            startActivity(intent);
        }
    }
}
