package uet.vav.stuber.cores;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.parse.ParseUser;
import uet.vav.stuber.dialogs.DialogProgress;
import uet.vav.stuber.uet.vav.dialogs.DialogProgressWithPositiveButton;
import uet.vav.stuber.utils.Constants;

/**
 * Created by darkmoonus on 4/9/16.
 */
public abstract class CoreActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG_TAG = "CoreActivity";

    @Override
    public abstract void onClick(View v);

    private FragmentManager mFragmentManager = getSupportFragmentManager();

    public void removePreviousDialog(String tag) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment prev = mFragmentManager.findFragmentByTag(tag);
        if (prev != null) ft.remove(prev);
        ft.commit();
    }

    private DialogFragment mDialog;

    public DialogFragment showProgressDialog(final String tag, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                removePreviousDialog(tag);
                mDialog = DialogProgress.newInstance(CoreActivity.this, msg);
                mDialog.show(getSupportFragmentManager(), tag);
            }
        });
        return mDialog;
    }

    public DialogFragment showProgressDialogWithPositiveButton(final String tag, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                removePreviousDialog(tag);
                mDialog = DialogProgressWithPositiveButton.newInstance(CoreActivity.this, msg);
                mDialog.show(getSupportFragmentManager(), tag);
            }
        });
        return mDialog;
    }

    public abstract void initViews();

    public abstract void initModels();

    public abstract void initListeners();

    public abstract void initAnimations();

    public void sendUserInfoToActivity(ParseUser user, final Class<? extends Activity> activity) {
        if (user == null) {
            Log.wtf(LOG_TAG, "User is null.");
        } else {
            Intent intent = new Intent(CoreActivity.this, activity);
            intent.putExtra(Constants.PROFILE_ID, user.getObjectId());
            intent.putExtra(Constants.PROFILE_USERNAME, user.getUsername());
            intent.putExtra(Constants.PROFILE_NAME, user.getString(Constants.PROFILE_NAME));
            intent.putExtra(Constants.PROFILE_EMAIl, user.getEmail());
            intent.putExtra(Constants.PROFILE_AVATAR_URL, user.getString(Constants.PROFILE_AVATAR_URL));
            finish();
            startActivity(intent);
        }
    }
}
