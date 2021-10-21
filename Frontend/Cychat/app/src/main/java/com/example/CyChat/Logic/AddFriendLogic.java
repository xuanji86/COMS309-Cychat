package com.example.CyChat.Logic;

import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.CyChat.Activity.AddFriendsActivity;
import com.example.CyChat.Interface.IServerRequest;
import com.example.CyChat.Interface.IVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AddFriendLogic implements IVolleyListener{
    IServerRequest serverRequest;
    AddFriendsActivity r;
    public AddFriendLogic(AddFriendsActivity r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void addUser(String ISU_Email) throws JSONException {
        String url = "http://10.24.227.134:8080/users/" + LoginLogic.hold.getId() + "/request";
        JSONObject newUserObj = new JSONObject();
        newUserObj.put("email", ISU_Email);

        serverRequest.sendToServer(url, newUserObj, "POST");
    }
    @Override
    public void onSuccess(String res) {
        if(res.contains("success")){
            Toast.makeText(r.getApplicationContext(), "Successfully send request!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(r.getApplicationContext(), "Can't find user!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(JSONArray arr) throws JSONException {

    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(r.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
        System.out.println(errorMessage);
        Toast.makeText(r.getApplicationContext(), "Error with request, please try again", Toast.LENGTH_SHORT).show();
    }


}
