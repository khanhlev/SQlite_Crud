package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.sqlite.sqlite.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//       database.close();
        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEdit:
                Intent intent = new Intent(this, AddOrEditEmployeeActivity.class);
                startActivity(intent);
                break;
        }
    }
}