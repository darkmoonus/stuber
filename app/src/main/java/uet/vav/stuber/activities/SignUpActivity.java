package uet.vav.stuber.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import uet.vav.stuber.R;
import uet.vav.stuber.application.StuberApplication;
import uet.vav.stuber.cores.CoreActivity;
import uet.vav.stuber.utils.Constants;
import uet.vav.stuber.utils.Network;

public class SignUpActivity extends CoreActivity {

    private AutoCompleteTextView fullname;
    private AutoCompleteTextView email;
    private EditText password;
    private Button signup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initViews();
        initModels();
        initListeners();
        initAnimations();
    }

    @Override
    public void initViews() {
        fullname = (AutoCompleteTextView) findViewById(R.id.full_name);
        email = (AutoCompleteTextView) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signup = (Button) findViewById(R.id.email_sign_in_button);
    }

    @Override
    public void initModels() {

    }

    @Override
    public void initListeners() {
        signup.setOnClickListener(this);
    }

    @Override
    public void initAnimations() {

    }

    public boolean validate(String fullname, String email, String pass) {
        if (!fullname.equals("") && !email.equals("") && !pass.equals("")) {
            return true;
        }
        return false;
    }

    public void register(final String name, final String email, final String pass) {
        RequestParams params = new RequestParams();
        params.put("username", name);
        params.put("email", email);
        params.put("password", pass);
        showProgressDialog("Signup", "Siging up...");
        Network.post(Constants.SIGNUP_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getString("status").equals("ok")) {
                        JSONObject data = response.getJSONObject("data");
                        String id = data.getString("id");
                        String username = data.getString("username");
                        String avatar = data.getString("avatar");
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.putExtra("id", id);
                        StuberApplication.USER_ID = id;
                        intent.putExtra("username", username);
                        intent.putExtra("avatar", avatar);
                        startActivity(intent);
                        removePreviousDialog("Signup");
                    }
                } catch (JSONException e) {
                    removePreviousDialog("Signup");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                removePreviousDialog("Signup");
                Log.e("onFailure", e.toString());
                Log.e("errorResponse", errorResponse);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                String name = fullname.getText().toString();
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                if (validate(name, mail, pass)) {
                    register(name, mail, pass);
                } else {
                    Snackbar.make(v, "Opps, some fields are empty !", Snackbar.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }
}
