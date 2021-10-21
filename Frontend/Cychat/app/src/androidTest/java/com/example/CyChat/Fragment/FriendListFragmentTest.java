package com.example.CyChat.Fragment;

import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.test.filters.LargeTest;

import com.example.CyChat.Activity.MainPageActivity;
import com.example.CyChat.Friends;
import com.example.CyChat.Logic.ServerRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@LargeTest
public class FriendListFragmentTest {


    @Mock
    Fragment FriendListFragment;
    @Mock
    ArrayList<Friends> friendsList;

    @Before
    public void setUp() throws Exception {
        FriendListFragment = new FriendListFragment();
    }

    @Test
    public void friendListTest() throws JSONException {
        assertNotNull(FriendListFragment);
    }

    @Test
    public void getFriendList() {
        assertNotNull(friendsList);
    }
}