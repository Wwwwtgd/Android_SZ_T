package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class Nj extends AppCompatActivity {
    int d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nj);

        int list[] = {30,27,24,22,20,16,12};

        Spinner spinnerItems = findViewById(R.id.spinner);
        spinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                d= list[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();
                double p_y=0,ave_k,sum_k=0,pcc;
                double[] p=new double[8],k=new double[8];
                EditText ek[] = new EditText[8];
                //获取扭矩系数框s

                ek[0] = (EditText) findViewById (R.id.editTextNumber1);
                ek[1] = (EditText) findViewById (R.id.editTextNumber2);
                ek[2] = (EditText) findViewById (R.id.editTextNumber3);
                ek[3] = (EditText) findViewById (R.id.editTextNumber4);
                ek[4] = (EditText) findViewById (R.id.editTextNumber5);
                ek[5] = (EditText) findViewById (R.id.editTextNumber6);
                ek[6] = (EditText) findViewById (R.id.editTextNumber7);
                ek[7] = (EditText) findViewById (R.id.editTextNumber8);
                k[0]= Double.parseDouble(ek[0].getText().toString());
                double k_k;
                //随机扭矩系数
                for (int i = 1; i < 8; i++) {
                    if(random.nextDouble()>0.75){
                        k_k = k[0] - 0.012 + random.nextDouble()*0.024;
                    }else{
                        k_k = k[0] - 0.008 + random.nextDouble()*0.016;
                    }

                    ek[i].setText(String.format("%.3f", k_k));

                    //如果不合格置红
                    if(k_k > 0.15 || k_k < 0.11){
                        ek[i].setTextColor(Color.RED);
                    } else {
                        ek[i].setTextColor(Color.parseColor("#000000"));
                    }
                }
                //扭矩系数
                k[1]= Double.parseDouble(ek[1].getText().toString());
                k[2]= Double.parseDouble(ek[2].getText().toString());
                k[3]= Double.parseDouble(ek[3].getText().toString());
                k[4]= Double.parseDouble(ek[4].getText().toString());
                k[5]= Double.parseDouble(ek[5].getText().toString());
                k[6]= Double.parseDouble(ek[6].getText().toString());
                k[7]= Double.parseDouble(ek[7].getText().toString());

                TextView ave= (TextView) findViewById(R.id.textViewave);
                TextView p1= (TextView) findViewById(R.id.textP1);
                TextView p2= (TextView) findViewById(R.id.textP2);
                TextView p3= (TextView) findViewById(R.id.textP3);
                TextView p4= (TextView) findViewById(R.id.textP4);
                TextView p5= (TextView) findViewById(R.id.textP5);
                TextView p6= (TextView) findViewById(R.id.textP6);
                TextView p7= (TextView) findViewById(R.id.textP7);
                TextView p8= (TextView) findViewById(R.id.textP8);
                TextView t1= (TextView) findViewById(R.id.textT1);
                TextView t2= (TextView) findViewById(R.id.textT2);
                TextView t3= (TextView) findViewById(R.id.textT3);
                TextView t4= (TextView) findViewById(R.id.textT4);
                TextView t5= (TextView) findViewById(R.id.textT5);
                TextView t6= (TextView) findViewById(R.id.textT6);
                TextView t7= (TextView) findViewById(R.id.textT7);
                TextView t8= (TextView) findViewById(R.id.textT8);
                TextView d2= (TextView) findViewById(R.id.textView59);
                TextView d3= (TextView) findViewById(R.id.textView60);
                TextView d4= (TextView) findViewById(R.id.textView61);
                TextView d5= (TextView) findViewById(R.id.textView62);
                TextView d6= (TextView) findViewById(R.id.textView63);
                TextView d7= (TextView) findViewById(R.id.textView64);
                TextView d8= (TextView) findViewById(R.id.textView65);
                TextView pc= (TextView) findViewById(R.id.textViewpc);

                //calc
                sum_k =k[0]+k[1]+k[2]+k[3]+k[4]+k[5]+k[6]+k[7];
                ave_k = sum_k/8;
                if(d==30){
                    p_y = 381;
                } else if (d==27) {
                    p_y = 317;
                } else if (d==24) {
                    p_y = 248;
                } else if (d==22) {
                    p_y = 208;
                } else if (d==20) {
                    p_y = 168;
                } else if (d==16) {
                    p_y = 109;
                } else if (d==12) {
                    p_y = 60;
                }
                else p_y=0;

                //计算标准偏差
                pcc = Math.sqrt(
                        (
                                (k[1]-ave_k)*(k[1]-ave_k)+
                                (k[2]-ave_k)*(k[2]-ave_k)+
                                (k[3]-ave_k)*(k[3]-ave_k)+
                                (k[4]-ave_k)*(k[4]-ave_k)+
                                (k[5]-ave_k)*(k[5]-ave_k)+
                                (k[6]-ave_k)*(k[6]-ave_k)+
                                (k[7]-ave_k)*(k[7]-ave_k)+
                                (k[0]-ave_k)*(k[0]-ave_k)
                        )/7
                );

                //扭矩系数平均结果
                ave.setText(String.format("%.3f", ave_k));

                //随机预拉力峰值
                double rp = random.nextDouble()*0.1+p_y+0.6;
                for (int i = 0; i < 8; i++) {
                    p[i] = random.nextDouble()*0.2+rp;
                }


                //如果不合格置红
                if(pcc > 0.01){
                    pc.setTextColor(Color.RED);
                }else {
                    pc.setTextColor(Color.parseColor("#000000"));
                }

                p1.setText(String.format("%.1f", p[0]));
                p2.setText(String.format("%.1f", p[1]));
                p3.setText(String.format("%.1f", p[2]));
                p4.setText(String.format("%.1f", p[3]));
                p5.setText(String.format("%.1f", p[4]));
                p6.setText(String.format("%.1f", p[5]));
                p7.setText(String.format("%.1f", p[6]));
                p8.setText(String.format("%.1f", p[7]));
                //计算施拧扭矩T
                t1.setText(String.format("%.1f", p[0]*d*k[0]));
                t2.setText(String.format("%.1f", p[1]*d*k[1]));
                t3.setText(String.format("%.1f", p[2]*d*k[2]));
                t4.setText(String.format("%.1f", p[3]*d*k[3]));
                t5.setText(String.format("%.1f", p[4]*d*k[4]));
                t6.setText(String.format("%.1f", p[5]*d*k[5]));
                t7.setText(String.format("%.1f", p[6]*d*k[6]));
                t8.setText(String.format("%.1f", p[7]*d*k[7]));
                //螺栓规格
                d2.setText(Integer.toString(d));
                d3.setText(Integer.toString(d));
                d4.setText(Integer.toString(d));
                d5.setText(Integer.toString(d));
                d6.setText(Integer.toString(d));
                d7.setText(Integer.toString(d));
                d8.setText(Integer.toString(d));
                //标准偏差
                pc.setText(String.format("%.4f",pcc));
            }
        });
    }
}