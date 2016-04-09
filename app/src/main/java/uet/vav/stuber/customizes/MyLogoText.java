package uet.vav.stuber.customizes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import uet.vav.stuber.utils.Constants;

public class MyLogoText extends TextView {

    public MyLogoText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyLogoText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyLogoText(Context context) {
        super(context);
        init();
    }

    @SuppressLint("NewApi")
    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + Constants.LOGO_FONT + ".ttf");
            setTypeface(tf);
        }
    }

}