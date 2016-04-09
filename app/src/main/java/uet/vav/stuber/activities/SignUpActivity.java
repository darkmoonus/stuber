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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
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
        final ParseUser user = new ParseUser();
        user.put(Constants.PROFILE_NAME, name);
        user.setUsername(email);
        user.setEmail(email);
        user.setPassword(pass);

        System.out.println(user);
        showProgressDialog("Signup", "Registering your account...");
        user.signUpInBackground(
                new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            StuberApplication.USER_ID = email;
                            sendUserInfoToActivity(user, MainActivity.class);
                            removePreviousDialog("Signup");
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            removePreviousDialog("Signup");
                            showProgressDialogWithPositiveButton("LoginFailed", e.getMessage());
                            Log.e("onFailure", e.toString());
                        }
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                String name = fullname.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
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
