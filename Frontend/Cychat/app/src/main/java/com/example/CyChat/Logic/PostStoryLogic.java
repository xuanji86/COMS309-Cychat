package com.example.CyChat.Logic;

import android.content.Intent;
import android.widget.Toast;

import com.example.CyChat.Activity.PostStoryActivity;
import com.example.CyChat.Fragment.FriendListFragment;
import com.example.CyChat.Fragment.StoryFragment;
import com.example.CyChat.Interface.IServerRequest;
import com.example.CyChat.Interface.IVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostStoryLogic implements IVolleyListener {
    IServerRequest serverRequest;
    PostStoryActivity r;

    public PostStoryLogic(PostStoryActivity r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }
    public void postStory(String StoryText) throws JSONException{
        String url = "http://10.24.227.134:8080/users/"+ LoginLogic.hold.getId()+"/stories/create";
        JSONObject newUserObj = new JSONObject();
        newUserObj.put("textArea", StoryText);
        serverRequest.sendToServer(url, newUserObj, "POST");
    }

    @Override
    public void onSuccess(String res) throws JSONException {
        if(res.contains("success")){
            Toast.makeText(r.getApplicationContext(), "Successfully post story", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(r.getApplicationContext(), "Can't post story, please try again later", Toast.LENGTH_SHORT).show();
        }
        //Intent intent = new Intent(r.getApplicationContext(), StoryFragment.class);
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
