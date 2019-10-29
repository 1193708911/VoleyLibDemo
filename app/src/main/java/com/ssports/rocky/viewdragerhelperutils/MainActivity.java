package com.ssports.rocky.viewdragerhelperutils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ssports.rocky.http_lib.CallBack;
import com.ssports.rocky.http_lib.Voley;


public class MainActivity extends AppCompatActivity {
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Voley.sendJsonRequest(null, "https://api.apiopen.top/singlePoetry", Entity.class, new CallBack<Entity>() {
                    @Override
                    public void onSuccess(Entity entity) {
                        mButton.setText(entity.getResult());
                    }

                    @Override
                    public void onFailure() {
                        mButton.setText("failure");
                    }
                });
            }
        });
    }
}
