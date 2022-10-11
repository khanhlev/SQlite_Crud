package com.example.sqlite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlite.adapter.EmployeeAdapter;
import com.example.sqlite.model.Employee;
import com.example.sqlite.sqlite.DBHelper;
import com.example.sqlite.sqlite.EmployeeDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EmployeeAdapter employeeAdapter;
    private ListView lvEmployee;
    private String Id;
    private List<Employee> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//       database.close();
        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);

        lvEmployee=findViewById(R.id.lvEmployees);
        EmployeeDao dao= new EmployeeDao(this);
        list= dao.getAll();
        employeeAdapter=new EmployeeAdapter(this, list);
        lvEmployee.setAdapter(employeeAdapter);

        lvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee gam = list.get(i);
                Id=gam.getId();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();

        EmployeeDao dao= new EmployeeDao(this);
        List<Employee> updatedList = dao.getAll();

        list.clear();
        updatedList.forEach(item->list.add(item));
        employeeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnInsert:
                Intent intent = null;
                startActivity(intent);
                break;

            case R.id.btnEdit:
                if(Id==null){
                    Toast.makeText(this, "Id must be selected", Toast.LENGTH_SHORT).show();
                    return;
                }

                Bundle bundle=new Bundle();
                bundle.putString("id",Id);
                intent.putExtra("data",bundle);

                startActivity(intent);
                break;

            case R.id.btnDelete:
                if(Id==null){
                    Toast.makeText(this, "ID must be selected", Toast.LENGTH_SHORT).show();
                    return;
                }

                Employee dao= new Employee(this);
                dao.delete(Id);
                Id=null;

                onResume();

                Toast.makeText(this, "Game was deleted", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}