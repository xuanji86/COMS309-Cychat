package com.example.CyChat.Logic;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.CyChat.AppController;
import com.example.CyChat.Interface.IServerRequest;
import com.example.CyChat.Interface.IVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerRequest implements IServerRequest {
    private String tag_json_obj = "json_obj_req";
    private IVolleyListener I;


    public void sendToServer(String url, JSONObject newUserObj, String methodType) throws JSONException{
        int method = Request.Method.GET;
        if(methodType.equals("POST")){
            method = Request.Method.POST;
        }

    JsonObjectRequest registerUserRequest = new JsonObjectRequest(method, url, newUserObj,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(response != null){
                        try {
                            I.onSuccess(response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        I.onError("Null Response object received");
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    I.onError(error.getMessage());
//                    error.printStackTrace();
                }
            });
        AppController.getInstance().addToRequestQueue(registerUserRequest, tag_json_obj);
    }

    @Override
    public void sendToServer(String url, JSONArray newUserArray, String methodType) throws JSONException {
        int method = Request.Method.GET;
        if(methodType.equals("POST")){
            method = Request.Method.POST;
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(method, url, newUserArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null){
                            try {
                                I.onSuccess(response);
                                System.out.print("here");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            I.onError("Null Response object received");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        I.onError(error.getMessage());
//                    error.printStackTrace();
                    }
                });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest, tag_json_obj);
    }

    @Override
    public void sendToServer(String url, JSONArray newUserArray, String methodType, IVolleyListener listener) throws JSONException {
        int method = Request.Method.GET;
        if(methodType.equals("POST")){
            method = Request.Method.POST;
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(method, url, newUserArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null){
                            try {
                                listener.onSuccess(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            listener.onError("Null Response object received");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.getMessage());
//                    error.printStackTrace();
                    }
                });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest, tag_json_obj);
    }



    @Override
    public void addVolleyListener(IVolleyListener logic) {
       I = logic;
    }
}
