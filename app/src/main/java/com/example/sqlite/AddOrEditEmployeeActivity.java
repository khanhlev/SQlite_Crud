package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sqlite.model.Employee;
import com.example.sqlite.sqlite.EmployeeDao;

public class AddOrEditEmployeeActivity extends AppCompatActivity
                implements View.OnClickListener{

    private EditText edtId,edtName,edtSalary;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_employee);

        edtId=findViewById(R.id.etEmployeeid);
        edtName=findViewById(R.id.etName);
        edtSalary=findViewById(R.id.etSalary);

        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        findViewById(R.id.btnListEmployees).setOnClickListener(this);
        readEmployee();
    }

    private void readEmployee(){
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        if(bundle==null){
            return;
        }

        String id=bundle.getString("id");

        EmployeeDao dao= new EmployeeDao(this);
        Employee gam=dao.getById(id);

        edtId.setText(gam.getId());
        edtName.setText(gam.getName());
        edtSalary.setText(""+ gam.getSalary());

        btnSave.setText("Update");
    }

    @Override
    public void onClick(View view) {

    }
}