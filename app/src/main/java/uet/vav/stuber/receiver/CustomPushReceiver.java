package uet.vav.stuber.receiver;

/**
 * Created by TuanTQ on 4/10/16.
 */
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import uet.vav.stuber.application.StuberApplication;
import uet.vav.stuber.utils.NotificationUtils;


public class CustomPushReceiver extends ParsePushBroadcastReceiver {
    private final String TAG = CustomPushReceiver.class.getSimpleName();

    private NotificationUtils notificationUtils;

    private Intent parseIntent;

    public CustomPushReceiver() {
        super();
    }

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);

        if (intent == null)
            return;

        try {
            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

            if (json.has("confirmation")) {
                Intent pushIntent = new Intent();
                pushIntent.setClassName(context, "uet.vav.stuber.activities.ChatActivity");
                pushIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                pushIntent.putExtra("uid", StuberApplication.USER_ID);
                pushIntent.putExtra("pid", json.getString("message"));
                context.startActivity(pushIntent);
            } else {
                Log.e(TAG, "Push received: " + json);
                Intent pushIntent = new Intent();
                pushIntent.setClassName(context, "uet.vav.stuber.activities.PairedActivity");
                pushIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                pushIntent.putExtra("message", intent.getExtras().getString("com.parse.Data"));
                context.startActivity(pushIntent);
            }

        } catch (JSONException e) {
            Log.e(TAG, "Push message json exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPushDismiss(Context context, Intent intent) {
        super.onPushDismiss(context, intent);
    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);
    }

    /**
     * Parses the push notification json
     *
     * @param context
     * @param json
     */
    private void parsePushJson(Context context, JSONObject json) {
        try {
//            boolean isBackground = json.getBoolean("is_background");
            JSONObject data = json.getJSONObject("data");
            String title = data.getString("alert");
            String message = data.getString("message");

            Intent pushIntent = new Intent();
            pushIntent.setClassName(context, "uet.vav.stuber.activities.PairedActivity");
            pushIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pushIntent.putExtra("message", message);
            context.startActivity(pushIntent);

//            Intent resultIntent = new Intent(context, PairedActivity.class);
//            showNotificationMessage(context, "Stuber", title, resultIntent);
        } catch (JSONException e) {
            Log.e(TAG, "Push message json exception: " + e.getMessage());
        }
    }


    /**
     * Shows the notification message in the notification bar
     * If the app is in background, launches the app
     *
     * @param context
     * @param title
     * @param message
     * @param intent
     */
    private void showNotificationMessage(Context context, String title, String message, Intent intent) {

        notificationUtils = new NotificationUtils(context);
        intent.putExtras(parseIntent.getExtras());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, intent);
    }
}
