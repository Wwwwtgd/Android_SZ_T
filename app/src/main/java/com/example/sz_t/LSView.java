package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LSView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsview);

        Button b_nj = (Button) findViewById(R.id.btn_nj);
        Button b_xfz = (Button) findViewById(R.id.btn_xfz);
        Button b_yd = (Button) findViewById(R.id.btn_yd);

        b_nj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //监听按钮，如果点击，就跳转
                Intent intent = new Intent();
                //前一个（MainActivity.this）是目前页面，后面一个是要跳转的下一个页面
                intent.setClass(LSView.this,Nj.class);
                startActivity(intent);
            }
        });

        b_xfz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LSView.this,Xfz.class);
                startActivity(intent);
            }
        });

        b_yd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LSView.this,LsYd.class);
                startActivity(intent);
            }
        });
    }
}