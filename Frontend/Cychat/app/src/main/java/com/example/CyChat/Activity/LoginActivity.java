package com.example.CyChat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.CyChat.Logic.LoginLogic;
import com.example.CyChat.Logic.ServerRequest;
import com.example.myapplication.R;

import org.json.JSONException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private Button bt_Signin, bt_Signup;
    private String ISU_Email, password, spPassword;
    private EditText enter_username, enter_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    private void init(){
        bt_Signup = findViewById(R.id.Signup_button);
        bt_Signin = findViewById(R.id.Signin_button);
        enter_username = findViewById(R.id.editTextUserName);
        enter_password = findViewById(R.id.editTextPassword);

        ServerRequest serverRequest = new ServerRequest();
        LoginLogic logic = new LoginLogic(this, serverRequest);
        bt_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivityForResult(intent,1);
            }
        });
        bt_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ISU_Email = enter_username.getText().toString().trim();
                password = enter_password.getText().toString().trim();
                if(TextUtils.isEmpty(ISU_Email)){
                    Toast.makeText(LoginActivity.this, "Please enter your username(ISU Email)", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!isValidISUEmail(ISU_Email)){
                    Toast.makeText(LoginActivity.this, "This Email is not a valid ISU Email address", Toast.LENGTH_SHORT).show();
                    return;
                }else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    try{
                        logic.loginUser(ISU_Email,password);
                        //success login, move to main page

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * check the email is valid and is isu email address
     * @param ISU_Email
     * @return if this email are valid or not
     */
    private boolean isValidISUEmail(String ISU_Email){
        boolean isValid = false;
        try{
            String check = "^[a-zA-Z0-9_-]+@iastate.edu";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(ISU_Email);
            isValid = matcher.matches();
        }catch(Exception e){
            isValid = false;
        }
        return isValid;
    }

}