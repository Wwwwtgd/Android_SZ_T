package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class TOFD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tofd);

        Button button = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et= (EditText) findViewById (R.id.t);
                double t = Double.parseDouble(et.getText().toString());
                EditText er= (EditText) findViewById (R.id.r);
                double r = Double.parseDouble(er.getText().toString());
                TextView pcs = (TextView) findViewById(R.id.pcs);
                double radians = Math.toRadians(r); // 转弧度
                double tanValue = Math.tan(radians);
                double result = (4.0 / 3.0) * t * tanValue;
                pcs.setText(String.format("%.2f", result));
            }
        });
    }
}