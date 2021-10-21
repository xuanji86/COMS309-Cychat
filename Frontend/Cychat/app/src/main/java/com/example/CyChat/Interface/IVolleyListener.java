package com.example.CyChat.Interface;

import com.android.volley.VolleyError;
import com.example.CyChat.Friends;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public interface IVolleyListener {
    public void onSuccess(String s) throws JSONException;
    public void onSuccess(JSONArray arr) throws JSONException;
    public void onError(String s);
}
