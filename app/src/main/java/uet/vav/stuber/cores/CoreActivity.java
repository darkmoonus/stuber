package uet.vav.stuber.cores;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import uet.vav.stuber.uet.vav.dialogs.DialogProgress;

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
}
