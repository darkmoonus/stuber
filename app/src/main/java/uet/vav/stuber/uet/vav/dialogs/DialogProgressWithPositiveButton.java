package uet.vav.stuber.uet.vav.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import uet.vav.stuber.R;
import uet.vav.stuber.customizes.MyGifView;
import uet.vav.stuber.customizes.MyTextView;

public class DialogProgressWithPositiveButton extends DialogFragment {
    public Context mContext;
    public LayoutInflater mInflater;
    protected Dialog mDialog;
    protected MyTextView mContent;
    protected String mContentText;
    protected Button mBtnOK;

    public DialogProgressWithPositiveButton() {

    }

    public DialogProgressWithPositiveButton(Context context, String content) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mContentText = content;
    }

    public static DialogFragment newInstance(Context context, String content) {
        DialogProgressWithPositiveButton dialog = new DialogProgressWithPositiveButton(context, content);
        Bundle bundle = new Bundle();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {

        }
    }

    @SuppressLint("InflateParams")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDialog = new Dialog(mContext);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = mInflater.inflate(R.layout.dialog_progress_positive_button, null);

        initViews(view);
        setViews();
        initOnClick();
        initAnimation();

        mDialog.setContentView(view);
        return mDialog;
    }

    public void initViews(View view) {
        mContent = (MyTextView) view.findViewById(R.id.dialog_content);
        mBtnOK = (Button) view.findViewById(R.id.btn_ok);

    }

    public void setViews() {
        mContent.setText(String.valueOf(mContentText.charAt(0)).toUpperCase()
                + mContentText.subSequence(1, mContentText.length()));
    }

    public void initOnClick() {
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void initAnimation() {

    }
}
