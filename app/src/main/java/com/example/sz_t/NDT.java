package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NDT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndt);

        Button b_tofd = (Button) findViewById(R.id.btn_tofd);
        Button b_paut = (Button) findViewById(R.id.btn_paut);

        b_tofd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //监听按钮，如果点击，就跳转
                Intent intent = new Intent();
                //前一个（MainActivity.this）是目前页面，后面一个是要跳转的下一个页面
                intent.setClass(NDT.this,TOFD.class);
                startActivity(intent);
            }
        });

        b_paut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //监听按钮，如果点击，就跳转
                Intent intent = new Intent();
                //前一个（MainActivity.this）是目前页面，后面一个是要跳转的下一个页面
                intent.setClass(NDT.this,PAUT.class);
                startActivity(intent);
            }
        });
    }
}