package com.example.CyChat.Interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface IServerRequest {
    public void sendToServer(String url, JSONObject newUserObj, String methodType) throws JSONException;
    public void sendToServer(String url, JSONArray newUserArray, String methodType) throws JSONException;
    public void sendToServer(String url, JSONArray newUserArray, String methodType, IVolleyListener listener) throws JSONException;
    public void addVolleyListener(IVolleyListener logic);
}
