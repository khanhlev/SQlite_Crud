package com.example.sqlite.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlite.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private SQLiteDatabase db;

    public EmployeeDao(Context context){
        DBHelper helper= new DBHelper(context);

        db=helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<Employee> get(String sql, String ...selectArgs){
        List<Employee> list= new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,selectArgs);

        while (cursor.moveToNext()){
            Employee gam=new Employee();
            gam.setId(cursor.getString(cursor.getColumnIndex("id")));
            gam.setName(cursor.getString(cursor.getColumnIndex("name")));
            gam.setSalary(cursor.getFloat(cursor.getColumnIndex("price")));

            list.add(gam);
        }return list;
    }
    public List<Employee> getAll(){
        String sql="SELECT * FROM game";
        return get(sql);
    }
    public Employee getById(String id){
        String sql = "SELECT * FROM game WHERE id = ?";
        List<Employee>list=get(sql,id);
        return list.get(0);
    }
    public long insert(Employee gam){
        ContentValues values=new ContentValues();
        values.put("id",gam.getId());
        values.put("name",gam.getName());
        values.put("price",gam.getSalary());

        return db.insert("game",null,values);
    }
    public long update(Employee gam){
        ContentValues values=new ContentValues();
        values.put("name",gam.getName());
        values.put("price",gam.getSalary());

        return db.update("game",values, "id=?",new  String[]{gam.getId()});
    }
    public int delete(String id){
        return db.delete("game","id=?",new String[]{id});
    }
}
