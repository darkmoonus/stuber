package uet.vav.stuber.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import cz.msebera.android.httpclient.Header;
import uet.vav.stuber.R;
import uet.vav.stuber.cores.CoreActivity;

public class LogInActivity extends CoreActivity {

    private Button loginButton;
    private EditText password;
    private AutoCompleteTextView username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initModels();
        initListeners();
        initAnimations();

        if (Build.VERSION.SDK_INT >= 21) {
            setStatusBarColor();
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void initViews() {
        loginButton = (Button) findViewById(R.id.confirm_sign_in_button);
        username = (AutoCompleteTextView) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

    @Override
    public void initModels() {

    }

    @Override
    public void initListeners() {
        loginButton.setOnClickListener(this);
    }

    @Override
    public void initAnimations() {

    }

    public void login(String email, String pass) {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", pass);
        showProgressDialog("Login", "Loging in...");
        J4FClient.post(Configs.LOGIN, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getString("status").equals("ok")) {
                        JSONObject data = response.getJSONObject("data");
                        String id = data.getString("id");
                        String username = data.getString("username");
                        String avatar = data.getString("avatar");
                        Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                        MyApplication.USER_ID = id;
                        intent.putExtra("id", id);
                        intent.putExtra("username", username);
                        intent.putExtra("avatar", avatar);
                        finish();
                        startActivity(intent);
                        removePreviousDialog("Login");
                    }
                } catch (JSONException e) {
                    removePreviousDialog("Login");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                removePreviousDialog("Login");
                Log.e("onFailure", e.toString());
                Log.e("errorResponse", errorResponse);
            }
        });
    }

    public boolean validate(String email, String password) {
        if (!email.equals("") && !password.equals("")) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_sign_in_button:
                String email = username.getText().toString();
                String pass = password.getText().toString();
                if (validate(email, pass)) {
                    login(email, pass);
                } else {
                    Snackbar.make(v, "Opps, some fields are empty !", Snackbar.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

}
