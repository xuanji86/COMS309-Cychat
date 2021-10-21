package com.example.CyChat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.CyChat.Fragment.FriendListFragment;
import com.example.CyChat.Friends;
import com.example.CyChat.Interface.IVolleyListener;
import com.example.CyChat.Logic.LoginLogic;
import com.example.CyChat.Logic.ServerRequest;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FriendRequestActivity extends AppCompatActivity {
    private ListView requestList;
    private static ArrayList<Friends> potentialFriends;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_new_friend_layout);
        requestList = (ListView)findViewById(R.id.list_add_receive);
        requestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FriendRequestActivity.this, AcceptOrNotActivity.class);
                intent.putExtra("name", potentialFriends.get(position).getFullName());
                intent.putExtra("email",potentialFriends.get(position).getfISU_Email());
                startActivityForResult(intent,1);
            }
        });
        ServerRequest serverRequest = new ServerRequest();
        try {
            refreshRequestList(serverRequest, requestList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Friends>  getPFList(){
        return potentialFriends;
    }

    public void refreshRequestList(ServerRequest serverRequest, ListView lv) throws JSONException {
        IVolleyListener listener = new IVolleyListener() {
            @Override
            public void onSuccess(String s) throws JSONException {

            }

            @Override
            public void onSuccess(JSONArray arr) throws JSONException {
                List<HashMap<String,Object>> listData = new ArrayList();
                ArrayList<Friends> pf = new ArrayList<>();
                for(int i = 0; i < arr.length(); i++){
                    JSONObject temp = arr.getJSONObject(i);
                    Integer id = temp.getInt("id");
                    String e = temp.getString("email");
                    String firstName = temp.getString("firstName");
                    String lastName = temp.getString("lastName");
                    pf.add(i, new Friends(id, e, firstName, lastName, "null",null));
                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("name",firstName +" " + lastName);
                    hashMap.put("email", e);
                    listData.add(hashMap);
                }
                String[] form = new String[]{"name", "email"};
                int[] to = new int[]{R.id.friend_name_display,R.id.friend_email};
                SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),listData,R.layout.friend_list_layout,form,to);
                lv.setAdapter(simpleAdapter);
                potentialFriends = pf;
            }

            @Override
            public void onError(String s) {

            }
        };

        final String url = "http://10.24.227.134:8080/users/"+ LoginLogic.hold.getId() +"/potential_friends";
        JSONArray jsonArray = new JSONArray();
        serverRequest.sendToServer(url, jsonArray, "POST",listener);
    }

}
