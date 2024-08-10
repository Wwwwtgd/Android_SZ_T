package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class LsYd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ls_yd);

        Button b_yd = (Button) findViewById(R.id.button_cxyd);
        b_yd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                double hc,hb;
                double[] hrc = new double[8];
                TextView[] t1= new TextView[8],t2 = new TextView[8];
                t1[0] = (TextView) findViewById(R.id.texthrb1);
                t1[1] = (TextView) findViewById(R.id.texthrb2);
                t1[2] = (TextView) findViewById(R.id.texthrb3);
                t1[3] = (TextView) findViewById(R.id.texthrb4);
                t1[4] = (TextView) findViewById(R.id.texthrb5);
                t1[5] = (TextView) findViewById(R.id.texthrb6);
                t1[6] = (TextView) findViewById(R.id.texthrb7);
                t1[7] = (TextView) findViewById(R.id.texthrb8);

                t2[0] = (TextView) findViewById(R.id.texthrc1);
                t2[1] = (TextView) findViewById(R.id.texthrc2);
                t2[2] = (TextView) findViewById(R.id.texthrc3);
                t2[3] = (TextView) findViewById(R.id.texthrc4);
                t2[4] = (TextView) findViewById(R.id.texthrc5);
                t2[5] = (TextView) findViewById(R.id.texthrc6);
                t2[6] = (TextView) findViewById(R.id.texthrc7);
                t2[7] = (TextView) findViewById(R.id.texthrc8);

                for (int i = 0; i < 8; i++) {
                    hc = 26+random.nextInt(9)*0.5;
                    hb = 36+ random.nextInt(13)*0.5;
                    t1[i].setText(String.format("%.1f",hb));
                    t2[i].setText(String.format("%.1f",hc));
                }
            }
        });
    }
}