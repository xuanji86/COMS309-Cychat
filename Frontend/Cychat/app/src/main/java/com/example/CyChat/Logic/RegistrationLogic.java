package com.example.CyChat.Logic;

import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.CyChat.Activity.RegistrationActivity;
import com.example.CyChat.Friends;
import com.example.CyChat.Interface.IServerRequest;
import com.example.CyChat.Interface.IVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegistrationLogic implements IVolleyListener {
    RegistrationActivity r;
    IServerRequest serverRequest;
    public RegistrationLogic(RegistrationActivity r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void registerUser(String firstName, String lastName, String ISU_Email, String password) throws JSONException{
        String url = "http://10.24.227.134:8080/users/create";
        JSONObject newUserObj = new JSONObject();
        newUserObj.put("firstName",firstName);
        newUserObj.put("lastName",lastName);
        newUserObj.put("email",ISU_Email);
        newUserObj.put("password",password);

        serverRequest.sendToServer(url, newUserObj, "POST");

    }

    @Override
    public void onSuccess(String res){
        if (res.contains("success")) {
            Toast.makeText(r.getApplicationContext(), "Successfully signed up!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(r.getApplicationContext(), "Email already exist, please try with another email.", Toast.LENGTH_SHORT).show();
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
