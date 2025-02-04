package com.example.sz_t;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Search_Cx extends AppCompatActivity {
        private Spinner spinnerStandard, spinnerGrade, spinnerThickness, spinnerStatus;
        private Button btnSearch;
        private TextView tvResult;
        private DatabaseHelper dbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_query);

            // 初始化视图
            spinnerStandard = findViewById(R.id.spinner_standard);
            spinnerGrade = findViewById(R.id.spinner_grade);
            spinnerThickness = findViewById(R.id.spinner_thickness);
            spinnerStatus = findViewById(R.id.spinner_status);
            btnSearch = findViewById(R.id.btn_search);
            tvResult = findViewById(R.id.tv_result);

            dbHelper = new DatabaseHelper(this);

            // 初始化标准列表
            loadStandards();

            // 设置监听器
            spinnerStandard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position > 0) {
                        String selectedStandard = parent.getItemAtPosition(position).toString();
                        loadGrades(selectedStandard);
                        spinnerGrade.setEnabled(true);
                    } else {
                        resetSpinners(spinnerGrade, spinnerThickness, spinnerStatus);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });

            // 其他Spinner的监听器类似，需要依次处理级联关系
            spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position > 0) {
                        String selectedStandard = spinnerStandard.getSelectedItem().toString();
                        String selectedGrade = parent.getItemAtPosition(position).toString();
                        loadThicknesses(selectedStandard, selectedGrade);
                        spinnerThickness.setEnabled(true);
                    } else {
                        resetSpinners(spinnerThickness, spinnerStatus);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });

            spinnerThickness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position > 0) {
                        String selectedStandard = spinnerStandard.getSelectedItem().toString();
                        String selectedGrade = spinnerGrade.getSelectedItem().toString();
                        String selectedThickness = parent.getItemAtPosition(position).toString();
                        loadStatuses(selectedStandard, selectedGrade, selectedThickness);
                        spinnerStatus.setEnabled(true);
                    } else {
                        resetSpinners(spinnerStatus);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });
            // 搜索按钮点击事件
            btnSearch.setOnClickListener(v -> performSearch());
        }

        private void loadStandards() {
            new Thread(() -> {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT DISTINCT 标准 FROM material", null);
                List<String> standards = new ArrayList<>();
                standards.add("请选择标准");
                while (cursor.moveToNext()) {
                    standards.add(cursor.getString(0));
                }
                cursor.close();
                runOnUiThread(() -> setupSpinner(spinnerStandard, standards));
            }).start();
        }

        private void loadGrades(String standard) {
            new Thread(() -> {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT DISTINCT 材质 FROM material WHERE 标准=?",
                        new String[]{standard});
                List<String> grades = new ArrayList<>();
                grades.add("请选择材质");
                while (cursor.moveToNext()) {
                    grades.add(cursor.getString(0));
                }
                cursor.close();
                runOnUiThread(() -> {
                    setupSpinner(spinnerGrade, grades);
                    resetSpinners(spinnerThickness, spinnerStatus);
                });
            }).start();
        }

        // 类似方法实现loadThicknesses和loadStatuses
        private void loadThicknesses(String standard, String grade) {
            new Thread(() -> {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery(
                        "SELECT DISTINCT 板厚 FROM material WHERE 标准=? AND 材质=?",
                        new String[]{standard, grade}
                );
                List<String> thicknesses = new ArrayList<>();
                thicknesses.add("请选择板厚");
                while (cursor.moveToNext()) {
                    thicknesses.add(cursor.getString(0));
                }
                cursor.close();
                runOnUiThread(() -> {
                    setupSpinner(spinnerThickness, thicknesses);
                    resetSpinners(spinnerStatus);
                });
            }).start();
        }

    private void loadStatuses(String standard, String grade, String thickness) {
        new Thread(() -> {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(
                    "SELECT DISTINCT 工艺 FROM material WHERE 标准=? AND 材质=? AND 板厚=?",
                    new String[]{standard, grade, thickness}
            );
            List<String> statuses = new ArrayList<>();
            statuses.add("请选择工艺");
            while (cursor.moveToNext()) {
                statuses.add(cursor.getString(0));
            }
            cursor.close();
            runOnUiThread(() -> setupSpinner(spinnerStatus, statuses));
        }).start();
    }

    private void setupSpinner(Spinner spinner, List<String> data) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_item, data);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }

        private void resetSpinners(Spinner... spinners) {
            for (Spinner spinner : spinners) {
                spinner.setAdapter(null);
                spinner.setEnabled(false);
            }
        }

        private void performSearch() {
            String standard = spinnerStandard.getSelectedItem().toString();
            String grade = spinnerGrade.getSelectedItem().toString();
            String thickness = spinnerThickness.getSelectedItem().toString();
            String status = spinnerStatus.getSelectedItem().toString();

            new Thread(() -> {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery(
                        "SELECT * FROM material WHERE 标准=? AND 材质=? AND 板厚=? AND 工艺=?",
                        new String[]{standard, grade, thickness, status});

                StringBuilder result = new StringBuilder();
                if (cursor.moveToFirst()) {
                    int columnCount = cursor.getColumnCount();
                    for (int i = 0; i < columnCount; i++) {
                        result.append(cursor.getColumnName(i))
                                .append(": ")
                                .append(cursor.getString(i))
                                .append("\n");
                    }
                }
                cursor.close();
                runOnUiThread(() -> tvResult.setText(result.toString()));
            }).start();
        }
    }