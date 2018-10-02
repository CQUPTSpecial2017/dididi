package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    ImageView giftImage;
    ImageView loginButton;
    ImageView playButton;
    CallbackManager callbackManager;
    AccessToken accessToken;

    private String userId = "";
    private String name = "";
    private String imgUrl = "";

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {      //判断标志位
                case 1:
                    Intent intent = new Intent(LoginActivity.this, WinActivity.class);
                    startActivity(intent);
                    finish();
                    /**
                     获取数据，更新UI
                     */
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
    }

    public void init() {
        callbackManager = CallbackManager.Factory.create();
        playButton = (ImageView) findViewById(R.id.playButton);
        loginButton = (ImageView) findViewById(R.id.loginButton);
        giftImage = (ImageView) findViewById(R.id.giftImage);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
                Intent intent = new Intent(LoginActivity.this, WinActivity.class);
                startActivity(intent);
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("sadhjfkahslkfhaklshfklashklfahsklhalshkashkla");
                accessToken = loginResult.getAccessToken();
                GraphRequest request=GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Message message=new Message();
                        message.what=1;
                        handler.sendMessage(message);
                    }
                });
                Toast.makeText(getApplicationContext(), "facebook登录成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "facebook登录取消", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "facebook登录错误", Toast.LENGTH_SHORT).show();

            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, VideoActivity.class);
                startActivity(intent);

            }
        });
        giftImage.bringToFront();
        Glide.with(this).load(R.drawable.gift2).into(giftImage);


    }



}
