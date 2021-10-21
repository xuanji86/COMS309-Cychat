package com.example.CyChat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.CyChat.Fragment.ChatFragment;
import com.example.CyChat.Fragment.FriendListFragment;
import com.example.CyChat.Fragment.SettingsFragment;
import com.example.CyChat.Fragment.StoryFragment;
import com.example.myapplication.R;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * This is char Fragment
     */
    private ChatFragment chatFragment;

    /**
     * This is friend list Fragment
     */
    private FriendListFragment friendListFragment;

    /**
     * This is story Fragment
     */
    private StoryFragment storyFragment;

    /**
     * This is setting Fragment
     */
    private SettingsFragment settingFragment;

    /**
     * chat layout
     */
    private View chatLayout;

    /**
     * friend list layout
     */
    private View friendsLayout;

    /**
     * story layout
     */
    private View storyLayout;

    /**
     * setting layout
     */
    private View settingsLayout;
    /**
     * Fragment manager
     */
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        initViews();
        fragmentManager = getSupportFragmentManager();
        // select first tab when start
        setTabSelection(0);
    }

    /**
     * init view, set up click
     */

    private void initViews() {
        chatLayout = findViewById(R.id.chat_layout);
        friendsLayout = findViewById(R.id.friends_layout);
        storyLayout = findViewById(R.id.story_layout);
        settingsLayout = findViewById(R.id.settings_layout);
        chatLayout.setOnClickListener(this);
        friendsLayout.setOnClickListener(this);
        storyLayout.setOnClickListener(this);
        settingsLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_layout:
                // tap chat tab, select first tab
                setTabSelection(0);
                break;
            case R.id.friends_layout:
                // tap firends tab, select second tab
                setTabSelection(1);
                break;
            case R.id.story_layout:
                // tap story tab, select third tab
                setTabSelection(2);
                break;
            case R.id.settings_layout:
                // tab setting tab, select fourth tab
                setTabSelection(3);
                break;
            default:
                break;
        }

    }

    /**
     * Depend the index input select tab
     *
     * @param index
     * index of tab。0 for chat，1 for friends，2 for story，3 for setting。
     */
    private void setTabSelection(int index) {
        // every time clean the tab select last time
        clearSelection();
        // start Fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // hide all Fragment，avoid multiple Fragment show on the screen
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
                chatLayout.setBackgroundColor(0xff0000ff);

                if (chatFragment == null) {
                    // if ChatFragment is null，then create one
                    chatFragment = new ChatFragment();
                    fragmentTransaction.add(R.id.content, chatFragment);
                } else {
                    // if ChatFragment isn't null，then show it
                    fragmentTransaction.show(chatFragment);
                }
                break;
            case 1:
                // change color after tap the tab
                friendsLayout.setBackgroundColor(0xff0000ff);
                if (friendListFragment == null) {
                    // If friendlistFragment is null，then create one
                friendListFragment = new FriendListFragment();
                    fragmentTransaction.add(R.id.content, friendListFragment);
                } else {
                    // if not null, then show it
                    fragmentTransaction.show(friendListFragment);
                }
                break;
            case 2:
                // change color after tap the tab
                storyLayout.setBackgroundColor(0xff0000ff);
                if (storyFragment == null) {
                    // if storyFragment is null，then create one
                    storyFragment = new StoryFragment();
                    fragmentTransaction.add(R.id.content, storyFragment);
                } else {
                    // if exist, then show it
                    fragmentTransaction.show(storyFragment);
                }
                break;
            case 3:
            default:
                // change color after tap the tab
                settingsLayout.setBackgroundColor(0xff0000ff);
                if (settingFragment == null) {
                    // if SettingFragment is null,then create one
                    settingFragment = new SettingsFragment();
                    fragmentTransaction.add(R.id.content, settingFragment);
                } else {
                    //  if exist, then show it
                    fragmentTransaction.show(settingFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    /**
     * set all Fragment to hide
     *
     * @param transaction
     *
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (chatFragment != null) {
            transaction.hide(chatFragment);
        }
        if (friendListFragment != null) {
            transaction.hide(friendListFragment);
        }
        if (storyFragment != null) {
            transaction.hide(storyFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

    /**
     * clean all select state
     */
    private void clearSelection() {
        chatLayout.setBackgroundColor(0xffffffff);
        friendsLayout.setBackgroundColor(0xffffffff);
        storyLayout.setBackgroundColor(0xffffffff);
        settingsLayout.setBackgroundColor(0xffffffff);
    }

}