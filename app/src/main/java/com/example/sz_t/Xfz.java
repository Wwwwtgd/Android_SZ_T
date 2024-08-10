package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class Xfz extends AppCompatActivity {
    int d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfz);


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


        Button button = (Button) findViewById(R.id.buttonxfz);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                TextView[] t = new TextView[8];
                int res,key=624;
                t[0]= (TextView) findViewById(R.id.textres1);
                t[1]= (TextView) findViewById(R.id.textres2);
                t[2]= (TextView) findViewById(R.id.textres3);
                t[3]= (TextView) findViewById(R.id.textres4);
                t[4]= (TextView) findViewById(R.id.textres5);
                t[5]= (TextView) findViewById(R.id.textres6);
                t[6]= (TextView) findViewById(R.id.textres7);
                t[7]= (TextView) findViewById(R.id.textres8);

                switch (d){
                    case 30: key = 610 + random.nextInt(40);break;
                    case 27: key = 491 + random.nextInt(35);break;
                    case 24: key = 385 + random.nextInt(30);break;
                    case 22: key = 329 + random.nextInt(25);break;
                    case 20: key = 276 + random.nextInt(20);break;
                    case 16: key = 171 + random.nextInt(15);break;
                    case 12: key = 91 + random.nextInt(8);break;
                    default: break;
                }

                for (int i = 0; i < 8; i++) {
                    res = random.nextInt((int)(13)) + key -random.nextInt((int)(13));
                    if(isInside(res)){

                        t[i].setTextColor(Color.parseColor("#000000"));
                    } else {
                        t[i].setTextColor(Color.RED);
                    }
                    t[i].setText(String.format("%d", res));
                }

            }

        });
    }
    public boolean isInside(int res){
        switch (d){
            case 30: if(res<=696&& res>=583) return true;else return false;
            case 27: if(res<=569&& res>=477) return true;else return false;
            case 24: if(res<=438&& res>=367) return true;else return false;
            case 22: if(res<=376&& res>=315) return true;else return false;
            case 20: if(res<=304&& res>=255) return true;else return false;
            case 16: if(res<=195&& res>=163) return true;else return false;
            case 12: if(res<=104.5&& res>=87.7) return true;else return false;
            default: return false;
        }
    }
}