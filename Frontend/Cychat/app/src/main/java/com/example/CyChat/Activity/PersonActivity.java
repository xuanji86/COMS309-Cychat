package com.example.CyChat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CyChat.Logic.DeleteFriendRequestLogic;
import com.example.CyChat.Logic.ServerRequest;
import com.example.myapplication.R;

import org.json.JSONException;

public class PersonActivity extends AppCompatActivity {
    private Button bt_Chat, bt_Delete;
    private TextView tv_FullName;
    private String imgurl, fullName, ISU_Email;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_layout);
        Intent intent =getIntent();
        fullName = intent.getStringExtra("Fullname");
        ISU_Email = intent.getStringExtra("ISU_Email");
        init();
    }
    private void init(){
        bt_Chat = findViewById(R.id.Button_Chat);
        bt_Delete = findViewById(R.id.Button_Delete);
        tv_FullName = findViewById(R.id.tv_Fullname);
        tv_FullName.setText(fullName);

        ServerRequest serverRequest = new ServerRequest();
        DeleteFriendRequestLogic logic = new DeleteFriendRequestLogic(this,serverRequest);

        bt_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    logic.deleteUser(ISU_Email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
