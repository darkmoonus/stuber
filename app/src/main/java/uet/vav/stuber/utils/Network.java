package uet.vav.stuber.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by nvg58 on 11/22/15.
 * Project Android
 */
public class Network {
    private static final String BASE_URL = "";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void postQuestion(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("X-Parse-Application-Id", "D54tVmK03qzubK2xJi4bmMmGS0lsXaoQQbBPXIAn");
        client.addHeader("X-Parse-REST-API-Key", "m6czzrv5NEUqtvehketSyAfBCLkoBpRoALDqYqAZ");
        client.addHeader("Content-Type", "application/json");
        client.post(url, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
