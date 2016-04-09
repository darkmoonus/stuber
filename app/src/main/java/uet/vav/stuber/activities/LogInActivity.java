package uet.vav.stuber.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import uet.vav.stuber.R;
import uet.vav.stuber.cores.CoreActivity;

public class LoginActivity extends CoreActivity {

    private Button loginButton;
    private EditText password;
    private AutoCompleteTextView username;
    private TextView sigupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initModels();
        initListeners();
        initAnimations();

        if (Build.VERSION.SDK_INT >= 21) {
            setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public void initViews() {
        loginButton = (Button) findViewById(R.id.confirm_sign_in_button);
        sigupLink = (TextView) findViewById(R.id.link_to_singup);
        username = (AutoCompleteTextView) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

    @Override
    public void initModels() {

    }

    @Override
    public void initListeners() {
        loginButton.setOnClickListener(this);
        sigupLink.setOnClickListener(this);
    }

    @Override
    public void initAnimations() {

    }

    public void login(String email, String pass) {
        showProgressDialog("Login", "Logging in...");
        ParseUser.logInInBackground(email, pass, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    sendUserInfoToActivity(user, MainActivity.class);
                    removePreviousDialog("Login");
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    removePreviousDialog("Login");
                    showProgressDialogWithPositiveButton("LoginFailed", "Login failed!");
                    e.printStackTrace();
                }
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
                String email = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (validate(email, pass)) {
                    login(email, pass);
                } else {
                    Snackbar.make(v, "Opps, some fields are empty !", Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.link_to_singup:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }

}
