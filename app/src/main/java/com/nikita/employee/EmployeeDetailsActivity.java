package com.nikita.employee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EmployeeDetailsActivity extends AppCompatActivity {

    private TextView txt_salary, txt_age, txt_imag, txt_name2, txt_id;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        actionBar = getSupportActionBar();


        txt_id = findViewById(R.id.txt_id);
        txt_name2 = findViewById(R.id.txt_name2);
        txt_age = findViewById(R.id.txt_age);
        txt_salary = findViewById(R.id.txt_salary);
        txt_imag = findViewById(R.id.txt_imag);

        Intent intent = getIntent();

        txt_id.setText(": "+intent.getStringExtra("id"));
        txt_name2.setText(": "+intent.getStringExtra("name"));
        txt_age.setText(": "+intent.getStringExtra("age"));
        txt_salary.setText(": "+intent.getStringExtra("salary"));
        txt_imag.setText(": "+intent.getStringExtra("pic"));

        actionBar.setTitle(""+intent.getStringExtra("name"));
    }
}
