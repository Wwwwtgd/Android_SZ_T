package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PAUT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paut);

        Button button = (Button) findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et2= (EditText) findViewById (R.id.t2);
                double t2 = Double.parseDouble(et2.getText().toString());
                EditText er2= (EditText) findViewById (R.id.r2);
                double r2 = Double.parseDouble(er2.getText().toString());
                TextView qy = (TextView) findViewById(R.id.qy);
                double radians = Math.toRadians(r2); // 转弧度
                double tanValue = Math.tan(radians);
                double result = 2 * t2 * tanValue - 6.8;
                qy.setText(String.format("%.2f", result));
            }
        });
    }
}