package com.example.CyChat.Logic;

import android.content.Intent;
import android.widget.Toast;

import com.example.CyChat.Activity.AcceptOrNotActivity;
import com.example.CyChat.Activity.PersonActivity;
import com.example.CyChat.Fragment.FriendListFragment;
import com.example.CyChat.Interface.IServerRequest;
import com.example.CyChat.Interface.IVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApproveOrRejectLogic implements IVolleyListener {
    IServerRequest serverRequest;
    AcceptOrNotActivity r;
    public ApproveOrRejectLogic(AcceptOrNotActivity r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void ApproveORRejectRequest(String yesOrNo, String ISU_Email)throws JSONException{
        String url =  "http://10.24.227.134:8080/users/"+ LoginLogic.hold.getId() + "/approveRequest/"+ yesOrNo;
        JSONObject newUserObj = new JSONObject();
        newUserObj.put("email", ISU_Email);
        serverRequest.sendToServer(url, newUserObj, "POST");
    }
    @Override
    public void onSuccess(String res) throws JSONException {
        Toast.makeText(r.getApplicationContext(),"Success", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(r.getApplicationContext(), FriendListFragment.class);

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
