package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Bc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bc);
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                double hou,kuan,rm,rel,shengchang,area,li,li2,duan,dh,dhl;
                int yuan;
                //get data
                EditText ehou = (EditText) findViewById (R.id.editTextText2);
                hou= Double.parseDouble(ehou.getText().toString());
                EditText ekuan = (EditText) findViewById (R.id.editTextText3);
                kuan=Double.parseDouble(ekuan.getText().toString());
                EditText ekangla = (EditText) findViewById (R.id.editTextText4);
                rm=Double.parseDouble(ekangla.getText().toString());
                EditText equfu = (EditText) findViewById (R.id.editTextText5);
                rel=Double.parseDouble(equfu.getText().toString());
                EditText eshengchang = (EditText) findViewById (R.id.editTextText6);
                shengchang=Double.parseDouble(eshengchang.getText().toString());
                //get result text
                TextView yscc= (TextView) findViewById(R.id.textyscc);
                TextView qfl= (TextView) findViewById(R.id.textqfl);
                TextView qfqd= (TextView) findViewById(R.id.textqfqd);
                TextView zdl= (TextView) findViewById(R.id.textzdl);
                TextView klqd= (TextView) findViewById(R.id.textklqd);
                TextView ysbj= (TextView) findViewById(R.id.textysbj);
                TextView dhbj= (TextView) findViewById(R.id.textdhbj);
                TextView scl= (TextView) findViewById(R.id.textscl);
                TextView dhcc= (TextView) findViewById(R.id.textdhcc);
                TextView ssl= (TextView) findViewById(R.id.textssl);
                //calc
                kuan = kuan - 0.5 + random.nextDouble();
                hou = hou - 0.5 + random.nextDouble();
                rel = rel -0.5 + random.nextDouble();
                rm = rm -0.5 + random.nextDouble();
                area = kuan * hou;
                li = rel * area /1000.0;
                li2 = rm * area /1000.0;
                yuan =(int)((5.56 * Math.sqrt(area) +2.5)/5) *5;
                duan = (shengchang*0.01 + 1)*yuan;
                dh = 65 + random.nextDouble()*10;
                dhl = Math.sqrt(1-0.01*dh);
                yscc.setText(String.format("%.2f", kuan) + "*" + String.format("%.2f", hou));
                qfl.setText(String.format("%.2f", li));
                qfqd.setText(String.format("%.2f", rel));
                zdl.setText(String.format("%.2f", li2));
                klqd.setText(String.format("%.2f", rm));
                ysbj.setText(Integer.toString(yuan));
                dhbj.setText(String.format("%.2f",duan));
                scl.setText(String.format("%.1f",shengchang));
                dhcc.setText(String.format("%.2f",dhl*hou) + "*" +String.format("%.2f",dhl*kuan));
                ssl.setText(String.format("%.2f",dh));
            }
        });
    }

}