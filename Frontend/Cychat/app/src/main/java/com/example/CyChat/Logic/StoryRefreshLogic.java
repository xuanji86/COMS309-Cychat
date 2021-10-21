package com.example.CyChat.Logic;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.CyChat.Fragment.StoryFragment;
import com.example.CyChat.Friends;
import com.example.CyChat.Interface.IServerRequest;
import com.example.CyChat.Interface.IVolleyListener;
import com.example.CyChat.Story;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoryRefreshLogic implements IVolleyListener {
    IServerRequest serverRequest;
    StoryFragment r;
    ListView lv;
    private ArrayList<Story> StoryList;

    public StoryRefreshLogic(StoryFragment r, IServerRequest serverRequest, ListView lv){
        this.r =r;
        this.serverRequest = serverRequest;
        this.lv = lv;
        serverRequest.addVolleyListener(this);
    }

    public void storyRefresh() throws JSONException{
        final String url = "http://10.24.227.134:8080/users/" + LoginLogic.hold.getId() + "/stories";
        JSONArray jsonArray = new JSONArray();
        serverRequest.sendToServer(url, jsonArray, "POST");
    }

    public ArrayList<Story> getList(){
        return StoryList;
    }

    @Override
    public void onSuccess(String s) throws JSONException {

    }

    @Override
    public void onSuccess(JSONArray arr) throws JSONException {
        //ArrayList<Story> sList = new ArrayList<Story>(arr.length());
        List<HashMap<String,Object>> listData = new ArrayList();
        for(int i = 0; i < arr.length(); i++){
            JSONObject temp = arr.getJSONObject(i);
            HashMap<String,Object> hashMap = new HashMap<>();
            //String imgurl = temp.getString("imgurl");
            String textArea = temp.getString("textArea");
            //String ISU_Email= temp.getString("email");
            //Integer SID = temp.getInt("id");
            String userName = temp.getString("userName");
            hashMap.put("userName",userName);
            hashMap.put("Story",textArea);
            listData.add(hashMap);
            //sList.add(i, new Story(null,textArea,null,firstName,lastName,null));
        }
        String[] form = new String[]{"userName", "Story"};
        int[] to = new int[]{R.id.friend_name_display,R.id.friend_email};
        SimpleAdapter simpleAdapter = new SimpleAdapter(r.getContext(),listData,R.layout.story_item_layout,form,to);
        lv.setAdapter(simpleAdapter);
        //StoryList = sList;

    }

    @Override
    public void onError(String errorMessage) {
//        System.out.println("flag");
//        Toast.makeText(r.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
//        Toast.makeText(r.getContext(), "Error with request, please try again", Toast.LENGTH_SHORT).show();
    }
}
