package com.cynbean.keep;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cynbean.keep.entity.User;
import com.cynbean.keep.request.UserRequest;
import com.cynbean.keep.util.BaseApplication;
import com.cynbean.keep.util.DataResponse;

import java.sql.DatabaseMetaData;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tcf24 on 2016/5/30.
 */
public class PasswordActivity extends AppCompatActivity {


    @Bind(R.id.input_password)
    EditText etPassword;
    @Bind(R.id.input_password2)
    EditText etPassword2;
    @Bind(R.id.btn_verify)
    Button _commitButton;

    private BaseApplication application = BaseApplication.getInstance();
    private UserRequest userRequest = new UserRequest();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        ButterKnife.bind(this);

        _commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = etPassword.getText().toString();
                String pwd2 = etPassword2.getText().toString();

                if (pwd.length() >= 6 && pwd != null && pwd != "" && pwd.equals(pwd2)) {

                    userRequest.updateAsynRequest(application.getToken(), pwd, new DataResponse<Map<String, Object>>() {
                        @Override
                        public List<Map<String, Object>> onData(Map<String, Object> data) {
                            try {
                                boolean flag = (boolean) data.get("flag");
                                String msg = (String) data.get("msg");
                                Toast.makeText(PasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
                                if (flag) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    });

                } else {
                    Toast.makeText(PasswordActivity.this, "密码长度须大于6位，并且两次输入一致", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
