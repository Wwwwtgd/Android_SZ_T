package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Nj extends AppCompatActivity {
    int d=30;
    double yz=1453.8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nj);
        // 定义下拉列表值
        int list[] = {30,27,24,22,20,16,12};
        Spinner spinnerItems = findViewById(R.id.spinner);
        // 创建一个适配器，将 list 数据传递给 Spinner
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, toArray(list));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // 设置下拉菜单的样式
        spinnerItems.setAdapter(adapter);
        // 设置默认值，默认选中列表中的第一个元素
        spinnerItems.setSelection(0);  // 这里的 0 表示默认选中第一个项
        spinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                d= list[position];  //下拉列表选择值
                for (int i = 0; i < 7; i++) {
                    int resId = getResources().getIdentifier("textView" + (i + 59), "id", getPackageName());
                    TextView dn = findViewById(resId);  // 根据 ID 获取对应的 EditText
                    dn.setText(Integer.toString(d));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //预加载力
        EditText editText11 = findViewById(R.id.editTextText11);
        editText11.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                yz = Double.parseDouble(editText11.getText().toString());
                // 编辑框失去焦点时触发
                for (int i = 1; i < 8; i++) {
                    int resId = getResources().getIdentifier("editTextText" + (i + 11), "id", getPackageName());
                    TextView yzl = findViewById(resId);  // 根据 ID 获取对应的Text
                    yzl.setText(String.format("%.1f",yz));
                }
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double ave_k,sum_k=0,pcc;
                double[] k=new double[8];
                EditText ek[] = new EditText[8];
                yz = Double.parseDouble(editText11.getText().toString());
                //获取扭矩系数控件
                for (int i = 0; i < ek.length; i++) {
                    int resId = getResources().getIdentifier("editTextNumber" + (i + 1), "id", getPackageName());
                    ek[i] = findViewById(resId);  // 根据 ID 获取对应的 EditText
                }
                Log.d("Nj", "获得了扭矩系数");
                //施拧扭矩
                for (int i = 0; i < ek.length; i++) {
                    int resId = getResources().getIdentifier("editTextText" + (i + 21), "id", getPackageName());
                    EditText ep = findViewById(resId);
                    k[i] = Double.parseDouble(ek[i].getText().toString());
                    double pz = yz*d*k[i];
                    Log.d("Nj", String.format("%.0f",pz));
                    ep.setText(String.format("%.1f",pz));  //计算施拧扭矩 p，并设置
                    sum_k += k[i];  // 计算总扭矩系数
                    //扭矩系数如果不合格置红
                    if(k[i] > 0.15 || k[i] < 0.11){
                        ek[i].setTextColor(Color.RED);
                    } else {
                        ek[i].setTextColor(Color.parseColor("#000000"));
                    }
                }
                TextView ave= (TextView) findViewById(R.id.textViewave);  // 平均扭矩系数
                TextView pc= (TextView) findViewById(R.id.textViewpc);
                ave_k = sum_k / 8.0; //calc 平均值
                editText11.setTextColor(Color.parseColor("#000000"));
                //预加载力如果不合格置红
                if(d == 30)
                    if (yz > 429 || yz < 351) {
                        editText11.setTextColor(Color.RED);
                        showinfo("预加载力应为351~429KN");
                    }
                else if(d == 27)
                    if (yz > 352 || yz < 288){
                        editText11.setTextColor(Color.RED);
                        showinfo("预加载力应为288~352KN");
                    }
                else if(d == 24)
                    if (yz > 275 || yz < 225){
                        editText11.setTextColor(Color.RED);
                        showinfo("预加载力应为225~275KN");
                    }
                else if(d == 22)
                    if (yz > 231 || yz < 189){
                        editText11.setTextColor(Color.RED);
                        showinfo("预加载力应为189~231KN");
                    }
                else if(d == 20)
                    if (yz > 187 || yz < 153){
                        editText11.setTextColor(Color.RED);
                        showinfo("预加载力应为153~187KN");
                    }
                else if(d == 16)
                    if (yz > 121 || yz < 99){
                        editText11.setTextColor(Color.RED);
                        showinfo("预加载力应为99~121KN");
                    }
                else if(d == 12)
                    if (yz > 66 || yz < 54){
                        editText11.setTextColor(Color.RED);
                        showinfo("预加载力应为54~66KN");
                    }
                //计算标准偏差
                pcc = Math.sqrt((
                                (k[1]-ave_k)*(k[1]-ave_k)+ (k[2]-ave_k)*(k[2]-ave_k)+ (k[3]-ave_k)*(k[3]-ave_k)+
                                (k[4]-ave_k)*(k[4]-ave_k)+ (k[5]-ave_k)*(k[5]-ave_k)+ (k[6]-ave_k)*(k[6]-ave_k)+
                                (k[7]-ave_k)*(k[7]-ave_k)+ (k[0]-ave_k)*(k[0]-ave_k))/7
                );
                //如果不合格置红
                if(pcc > 0.01){
                    pc.setTextColor(Color.RED);
                }else {
                    pc.setTextColor(Color.parseColor("#000000"));
                }
                //扭矩系数平均结果
                ave.setText(String.format("%.3f", ave_k));
                //标准偏差
                pc.setText(String.format("%.4f",pcc));
            }
        });
    }
    public void showinfo(String info){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Nj.this, info, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 辅助方法：将 int[] 转换为 Integer[]
    private Integer[] toArray(int[] list) {
        Integer[] result = new Integer[list.length];
        for (int i = 0; i < list.length; i++) {
            result[i] = list[i];  // 将 int 转换为 Integer
        }
        return result;
    }
}