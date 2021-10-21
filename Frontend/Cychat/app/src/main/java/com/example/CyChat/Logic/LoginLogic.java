package com.example.CyChat.Logic;

import android.content.Intent;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.CyChat.Activity.LoginActivity;
import com.example.CyChat.Activity.MainPageActivity;
import com.example.CyChat.Friends;
import com.example.CyChat.IdHolder;
import com.example.CyChat.Interface.IServerRequest;
import com.example.CyChat.Interface.IVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginLogic implements IVolleyListener {

    public static IdHolder hold;
    LoginActivity r;
    IServerRequest serverRequest;
    Integer id;

    public LoginLogic(LoginActivity r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void loginUser(String ISU_Email, String password) throws JSONException{
        String url = "http://10.24.227.134:8080/users/login";
        JSONObject newUserObj = new JSONObject();
        newUserObj.put("email", ISU_Email);
        newUserObj.put("password",password);

        serverRequest.sendToServer(url, newUserObj, "POST");
    }

    @Override
    public void onSuccess(String res) {
        if (res.contains("id")){
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(res);
            String getID = m.replaceAll("").trim();
            id = Integer.valueOf(getID).intValue();
            hold = new IdHolder(id);
            Toast.makeText(r.getApplicationContext(), "Welcome to CyChat!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(r.getApplicationContext(), MainPageActivity.class);
            r.startActivity(intent);
        }else {
            Toast.makeText(r.getApplicationContext(), "Please check your username or password and try again!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(r.getApplicationContext(), LoginActivity.class);
            r.startActivity(intent);
        }
    }

    @Override
    public void onSuccess(JSONArray arr) throws JSONException {

    }


    @Override
    public void onError(String errorMessage) {
        Toast.makeText(r.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
        Toast.makeText(r.getApplicationContext(), "Error with request, please try again", Toast.LENGTH_SHORT).show();
    }
}
