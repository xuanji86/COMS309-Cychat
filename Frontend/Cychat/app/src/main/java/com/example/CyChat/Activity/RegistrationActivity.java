package com.example.CyChat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.CyChat.Logic.RegistrationLogic;
import com.example.CyChat.Logic.ServerRequest;
import com.example.myapplication.R;

import org.json.JSONException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity{
    private String FirstName, LastName, ISU_Email, Password,PasswordAgain;
    private EditText enter_FirstName, enter_LastName,enter_ISU_Email, enter_Password, enter_passwordAgain;
    private Button Bt_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }
    private void init(){
        Bt_Register = findViewById(R.id.Button_Register);
        enter_FirstName = findViewById(R.id.editTextTextFirstName);
        enter_LastName = findViewById(R.id.editTextTextLastName);
        enter_ISU_Email = findViewById(R.id.editTextTextEmailAdress);
        enter_Password = findViewById(R.id.editTextTextPassword2);
        enter_passwordAgain = findViewById(R.id.editTextTextPassword3);

        ServerRequest serverRequest = new ServerRequest();
        final RegistrationLogic logic = new RegistrationLogic(this, serverRequest);

        Bt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if(TextUtils.isEmpty(FirstName)){
                    Toast.makeText(RegistrationActivity.this, "Please enter your first name ", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(LastName)){
                    Toast.makeText(RegistrationActivity.this, "Please enter your Last name ", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(ISU_Email)){
                    Toast.makeText(RegistrationActivity.this, "Please enter your ISU Email ", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(Password)){
                    Toast.makeText(RegistrationActivity.this, "Please enter your password ", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(PasswordAgain)){
                    Toast.makeText(RegistrationActivity.this, "Please re-enter your password ", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!Password.equals(PasswordAgain)) {
                    Toast.makeText(RegistrationActivity.this, "Please check your password again", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!isValidISUEmail(ISU_Email)){
                    Toast.makeText(RegistrationActivity.this, "This Email is not a valid ISU Email address", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    try {
                        logic.registerUser(FirstName, LastName, ISU_Email, Password);
                    } catch (JSONException e) {
                     e.printStackTrace();
                    }
                    RegistrationActivity.this.finish();
                }
            }
        });

    }
        private void getEditString(){
        FirstName = enter_FirstName.getText().toString().trim();
        LastName = enter_LastName.getText().toString().trim();
        ISU_Email = enter_ISU_Email.getText().toString().trim();
        Password = enter_Password.getText().toString().trim();
        PasswordAgain = enter_passwordAgain.getText().toString().trim();
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