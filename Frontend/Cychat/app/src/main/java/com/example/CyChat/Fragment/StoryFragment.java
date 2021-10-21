package com.example.CyChat.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;

import com.example.CyChat.Activity.AddFriendsActivity;
import com.example.CyChat.Activity.PersonActivity;
import com.example.CyChat.Activity.PostStoryActivity;
import com.example.CyChat.Logic.ServerRequest;
import com.example.CyChat.Logic.StoryRefreshLogic;
import com.example.myapplication.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoryFragment extends Fragment {
    private Button bt_Refresh,bt_Post;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rv = inflater.inflate(R.layout.new_story_layout, container, false);
        bt_Refresh = rv.findViewById(R.id.Button_refresh);
        bt_Post = rv.findViewById(R.id.Button_post);
        ListView listView = rv.findViewById(R.id.storyList);
        //test(listView);

        ServerRequest serverRequest = new ServerRequest();
        StoryRefreshLogic logic = new StoryRefreshLogic(this,serverRequest,listView);
        bt_Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    logic.storyRefresh();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        bt_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PostStoryActivity.class);
                startActivityForResult(intent,1);
            }
        });
        return rv;
    }

//    /**
//     * UI test purpose
//     * @param lv
//     */
//    public void test(ListView lv){
//        List<HashMap<String,Object>> listData = new ArrayList();
//        HashMap<String,Object> hashMap1 = new HashMap<>();
//        hashMap1.put("name", "Charles Lin");
//        hashMap1.put("Story","On any other night, Sal Delafosse might have peeked out his window to see two girls walking under moonlight. Adele Vignes would have heard the floorboards creak. Even Lou LeBon, closing down the diner, might have seen the twins through the foggy glass panes. But on Founder’s Day, Lou’s Egg House closed early. Sal, feeling suddenly spry, rocked to sleep with his wife. Adele snored through her cups of rum punch, dreaming of dancing with her husband at homecoming. No one saw the twins sneak out, exactly how they’d intended.");
//        HashMap<String,Object> hashMap2 = new HashMap<>();
//        hashMap2.put("name","Anji Xu");
//        hashMap2.put("Story","Why are we still here? Just to suffer?");
//        listData.add(hashMap1);
//        listData.add(hashMap2);
//        String[] form = new String[]{"name", "Story"};
//        int[] to = new int[]{R.id.friend_name_display,R.id.friend_email};
//        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listData,R.layout.story_item_layout,form,to);
//        lv.setAdapter(simpleAdapter);
//    }
}
