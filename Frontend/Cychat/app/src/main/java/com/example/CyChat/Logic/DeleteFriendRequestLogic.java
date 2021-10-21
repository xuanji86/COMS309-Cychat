package com.example.CyChat.Logic;

import android.app.Person;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.CyChat.Activity.AddFriendsActivity;
import com.example.CyChat.Activity.PersonActivity;
import com.example.CyChat.Interface.IServerRequest;
import com.example.CyChat.Interface.IVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeleteFriendRequestLogic implements IVolleyListener {
    IServerRequest serverRequest;
    PersonActivity r;
    public DeleteFriendRequestLogic(PersonActivity r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }
    public void deleteUser(String ISU_Email) throws JSONException{
        String url = "http://10.24.227.134:8080/users/"+LoginLogic.hold.getId()+"/disconnect";
        JSONObject newUserObj = new JSONObject();
        newUserObj.put("email", ISU_Email);
        serverRequest.sendToServer(url, newUserObj, "POST");
    }

    @Override
    public void onSuccess(String res) throws JSONException {
        if(res.contains("success")){
            Toast.makeText(r.getApplicationContext(), "Successfully delete user!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(r.getApplicationContext(), "Can't delete this user", Toast.LENGTH_SHORT).show();
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
