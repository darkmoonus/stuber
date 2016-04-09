package uet.vav.stuber.utils;

/**
 * Created by TuanTQ on 4/10/16.
 */
import android.content.Context;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import uet.vav.stuber.R;
public class ParseUtils {

    private static String TAG = ParseUtils.class.getSimpleName();

//    public static void verifyParseConfiguration(Context context) {
//        if (TextUtils.isEmpty(R.string.PARSE_APPLICATION_ID) || TextUtils.isEmpty(AppConfig.PARSE_CLIENT_KEY)) {
//            Toast.makeText(context, "Please configure your Parse Application ID and Client Key in AppConfig.java", Toast.LENGTH_LONG).show();
//            ((Activity) context).finish();
//        }
//    }

    public static void registerParse(Context context) {
        // initializing parse library
        Parse.initialize(context, context.getString(R.string.parse_app_id), context.getString(R.string.parse_client_key));
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParsePush.subscribeInBackground(context.getString(R.string.parse_channel), new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.e(TAG, "Successfully subscribed to Parse!");
            }
        });
    }

    public static void subscribeWithEmail(String email) {
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();

        installation.put("email", email);

        installation.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    Log.e(TAG, "Successfully subscribed to Parse with email!");
                }
            }
        });
    }
}
