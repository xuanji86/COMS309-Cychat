package com.example.CyChat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CyChat.Logic.ApproveOrRejectLogic;
import com.example.CyChat.Logic.ServerRequest;
import com.example.myapplication.R;

import org.json.JSONException;

public class AcceptOrNotActivity extends AppCompatActivity{
    private Button bt_Accept, bt_Reject;
    private TextView tv_FullName;
    private String name;
    private String email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.accept_or_not_layout);
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        init();
    }
    private void init(){
        ServerRequest serverRequest = new ServerRequest();
        ApproveOrRejectLogic logic = new ApproveOrRejectLogic(this,serverRequest);
        bt_Accept = findViewById(R.id.Button_Accept);
        bt_Reject = findViewById(R.id.Button_Reject);
        tv_FullName = findViewById(R.id.tv_Fullname);
        tv_FullName.setText(name);

        bt_Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    logic.ApproveORRejectRequest("1",email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        bt_Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    logic.ApproveORRejectRequest("0", email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
