package uet.vav.stuber.cores;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import uet.vav.stuber.dialogs.DialogProgress;

/**
 * Created by darkmoonus on 4/9/16.
 */
public abstract class CoreActivity extends AppCompatActivity implements View.OnClickListener {
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

    public abstract void initViews();

    public abstract void initModels();

    public abstract void initListeners();

    public abstract void initAnimations();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(int colorId) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(colorId));
    }
}
