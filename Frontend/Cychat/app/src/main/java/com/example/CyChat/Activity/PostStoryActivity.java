package com.example.CyChat.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CyChat.Logic.PostStoryLogic;
import com.example.CyChat.Logic.ServerRequest;
import com.example.CyChat.Story;
import com.example.myapplication.R;

import org.json.JSONException;

public class PostStoryActivity extends AppCompatActivity {
    private Button bt_postStory;
    private EditText enter_StoryText;
    private String StoryText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poststory_layout);
        init();
    }

    private void init() {
        bt_postStory = findViewById(R.id.Button_PostStory);
        enter_StoryText = findViewById(R.id.editText_Storytext);
        ServerRequest serverRequest = new ServerRequest();
        PostStoryLogic logic = new PostStoryLogic(this,serverRequest);

        bt_postStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoryText = enter_StoryText.getText().toString();
                if(TextUtils.isEmpty(StoryText)){
                    Toast.makeText(PostStoryActivity.this, "Please enter your username(ISU Email)", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    try {
                        logic.postStory(StoryText);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
