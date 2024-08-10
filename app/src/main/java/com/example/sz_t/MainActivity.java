package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //id输入框
    public EditText textid;
    //密码输入框
    public EditText textpwd;
    //登录
    public Button btn_login;
    //注册按钮
    public Button btn_registered;
    public Button btn_ip;
    //获取ip地址
    public EditText textip;
    //数据库登录返回状态
    public String res,ip;

    public DBConnection dbConnection= new DBConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
    }

    private void initview() {
        // 控件的初始化
        textid = findViewById(R.id.textid);
        textpwd = findViewById(R.id.textpwd);
        textip = (EditText) findViewById(R.id.text_ip);
        btn_login = findViewById(R.id.btn_lashen);
        btn_registered = findViewById(R.id.btn_registered);
        btn_ip = findViewById(R.id.button_t);

        //初始化数据库
        btn_ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable(){
                    public void run(){
                        ip = textip.getText().toString();
                        if(ip.equals("superlogin") ){
                            Intent intent2 = new Intent(MainActivity.this, TheSecondMain.class);
                            startActivity(intent2);
                            return ;
                        }
                        res = dbConnection.getConnection(ip);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, res, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).start();
            }
        });



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dbConnection.resconn == "驱动加载成功,数据库连接成功！") {
                    //建立新子线程，主线程中不可运行数据库操作
                    new Thread(new Runnable() {
                        public void run() {
                            //DBConnection dbConnection = new DBConnection();
                            //ip = textip.getText().toString();
                            //dbConnection.getConnection(ip);
                            dbConnection.Login_GO(textid.getText().toString().trim(), textpwd.getText().toString().trim());
                            if (dbConnection.th == true) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        /*
                        如果找到，toast提示“登录成功”，后跳转到一个新的页面
                         */
                                Intent intent2 = new Intent(MainActivity.this, TheSecondMain.class);
                                startActivity(intent2);
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });

        btn_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbConnection.resconn == "驱动加载成功,数据库连接成功！"){
                    Intent intent = new Intent(MainActivity.this, Registered.class);
                    intent.putExtra("ip",ip );
                    //注册页面跳转
                    startActivity(intent);
                }
            }
        });
    }

}