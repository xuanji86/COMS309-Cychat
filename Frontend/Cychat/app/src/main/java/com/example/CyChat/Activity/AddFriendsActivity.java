package com.example.CyChat.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CyChat.Friends;
import com.example.CyChat.Interface.IVolleyListener;
import com.example.CyChat.Logic.AddFriendLogic;
import com.example.CyChat.Logic.LoginLogic;
import com.example.CyChat.Logic.ServerRequest;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddFriendsActivity extends AppCompatActivity {
    private EditText searchText;
    private Button btn_AddFriend;
    private String ISU_Email;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friend_layout);
        searchText = findViewById(R.id.tv_enterSearch);
        btn_AddFriend = findViewById(R.id.bt_Add);
        init();
    }

    private void init() {

        ServerRequest serverRequest = new ServerRequest();
        AddFriendLogic logic = new AddFriendLogic(this, serverRequest);
        btn_AddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ISU_Email = searchText.getText().toString().trim();
                if(TextUtils.isEmpty(ISU_Email)){
                    Toast.makeText(AddFriendsActivity.this, "Please enter your username(ISU Email)", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!isValidISUEmail(ISU_Email)){
                    Toast.makeText(AddFriendsActivity.this, "This Email is not a valid ISU Email address", Toast.LENGTH_SHORT).show();
                    return;
                }else if(ISU_Email.isEmpty()){
                    Toast.makeText(AddFriendsActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    try{
                        logic.addUser(ISU_Email);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

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
