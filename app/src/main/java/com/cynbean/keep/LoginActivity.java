package com.cynbean.keep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cynbean.keep.request.UserRequest;
import com.cynbean.keep.util.BaseApplication;
import com.cynbean.keep.util.Constant;
import com.cynbean.keep.util.FileUtil;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tcf24 on 2016/5/27.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Bind(R.id.link_signup)
    TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String username = _emailText.getText().toString();
        String password = _passwordText.getText().toString();


        UserRequest userRequest = new UserRequest();
        userRequest.loginAsynRequest(username, password, new UserRequest.UserResponse<Map<String, Object>>() {
            @Override
            public void onData(Map<String, Object> data) {
                try {

                    Log.d("Login data", data.toString());
                    String token = (String) data.get("data");
                    boolean flag = (boolean) data.get("flag");
                    String msg = (String) data.get("msg");

                    if(!token.equals("")){
                        BaseApplication application = BaseApplication.getInstance();
                        application.setToken(token);
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivityForResult(intent, 0);
                    }


//                    if (!token.equals("") && token != null) {
//                        try {
////                          FileUtils.writeStringToFile(new File(Constant.TOKEN_FILE),token);
//                            FileUtil.writeFile(Constant.TOKEN_FILE, token, LoginActivity.this);
//                            Log.d("token", token);
//                            System.out.println("token" + token);
//                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                            startActivityForResult(intent, 0);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            Toast.makeText(LoginActivity.this, "写入token失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "登录失败。", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        BaseApplication application = BaseApplication.getInstance();
//
//        if(application.getToken() != ""){
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivityForResult(intent, 0);
//        }else{
//            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
//        }


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
