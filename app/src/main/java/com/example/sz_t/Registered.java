package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registered extends AppCompatActivity {

    public Button btn_registered;
    public EditText textid;
    public  EditText textpwd;
    public String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        Intent intent=getIntent();
//获取数据并显示到text中
        ip=intent.getStringExtra("ip");

        initview();
    }
    private void initview() {

        // 控件的初始化
        textid=findViewById(R.id.textid);
        textpwd=findViewById(R.id.textpwd);
        btn_registered=findViewById(R.id.btn_registered);

        btn_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    public void run() {
                        DBConnection dbConnection = new DBConnection();
                        dbConnection.getConnection(ip);
                        dbConnection.RegiSql(textid.getText().toString().trim(), textpwd.getText().toString().trim());
                        if (dbConnection.tp == true) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Toast.makeText(Registered.this, "注册成功！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Registered.this, "注册失败！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}