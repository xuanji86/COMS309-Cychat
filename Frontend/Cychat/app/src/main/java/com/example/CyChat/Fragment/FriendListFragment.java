package com.example.CyChat.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.CyChat.Activity.AddFriendsActivity;
import com.example.CyChat.Activity.FriendRequestActivity;
import com.example.CyChat.Activity.PersonActivity;
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

public class    FriendListFragment extends Fragment {

    private Button bt_addFriend, bt_FriendRequest,bt_FriendRefresh;
    private ArrayList<Friends> friendsList;
    private static FriendListFragment hold;

    public FriendListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rv = inflater.inflate(R.layout.friends_layout, container, false);
        ListView listView = rv.findViewById(R.id.friendList);
        bt_addFriend = rv.findViewById(R.id.bt_addFriend);
        bt_FriendRequest = rv.findViewById(R.id.bt_friendRequest);
        bt_FriendRefresh = rv.findViewById(R.id.Button_FriendRefresh);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FriendListFragment.this.getActivity(), PersonActivity.class);
                intent.putExtra("Fullname", friendsList.get(position).getFullName());
                intent.putExtra("ISU_Email",friendsList.get(position).getfISU_Email());
                startActivityForResult(intent,1);
            }
        });

        bt_addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddFriendsActivity.class);
                startActivityForResult(intent,1);
            }
        });
        bt_FriendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FriendRequestActivity.class);
                startActivityForResult(intent,1);
            }
        });
        ServerRequest serverRequest = new ServerRequest();
        friendsList = new ArrayList<Friends>();
        try {
            getFriendList(serverRequest,listView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bt_FriendRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getFriendList(serverRequest,listView);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return rv;
    }

    private void getFriendList(ServerRequest serverRequest, ListView lv) throws JSONException {
        IVolleyListener listener = new IVolleyListener() {
            @Override
            public void onSuccess(String s) throws JSONException {

            }

            @Override
            public void onSuccess(JSONArray arr) throws JSONException {
                List<HashMap<String,Object>> listData = new ArrayList();
                for(int i = 0; i < arr.length(); i++){
                    JSONObject temp = arr.getJSONObject(i);
                    Integer id = temp.getInt("id");
                    String e = temp.getString("email");
                    String firstName = temp.getString("firstName");
                    String lastName = temp.getString("lastName");
                    friendsList.add(i, new Friends(id, e, firstName, lastName, "null",null));
                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("name",firstName +" " + lastName);
                    hashMap.put("email", e);
                    listData.add(hashMap);
                }
                String[] form = new String[]{"name", "email"};
                int[] to = new int[]{R.id.friend_name_display,R.id.friend_email};
                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listData,R.layout.friend_list_layout,form,to);
                lv.setAdapter(simpleAdapter);
            }

            @Override
            public void onError(String s) {

            }

        };
        final String url = "http://10.24.227.134:8080/users/"+ LoginLogic.hold.getId() +"/friends";
        JSONArray jsonArray = new JSONArray();
        serverRequest.sendToServer(url, jsonArray, "POST",listener);
    }

    public ArrayList<Friends> getList(){
        return friendsList;
    }
}
