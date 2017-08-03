package com.maning.mnxutilsdb.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.maning.mnxutilsdb.MyApplication;
import com.maning.mnxutilsdb.R;
import com.maning.mnxutilsdb.bean.Student;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvShow = (TextView) findViewById(R.id.tvShow);
    }

    //保存
    public void btn01(View view) {
        DbManager dbManager = MyApplication.getDbManager();

        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setAge(i);
            if(i<5){
                student.setName("小明");
            }else{
                student.setName("小红");
            }
            students.add(student);
        }
        try {
            dbManager.save(students);
            Log.e(TAG, "保存数据成功");
        } catch (DbException e) {
            e.printStackTrace();
            Log.e(TAG, "保存数据失败:" + e.toString());
        }

    }

    //查询
    public void btn02(View view) {
        DbManager dbManager = MyApplication.getDbManager();
        try {
            List<Student> studentList = dbManager.selector(Student.class).findAll();
            if(studentList != null){
                Log.e(TAG, "查询数据:" + studentList.toString());
                tvShow.setText(studentList.toString());
            }

        } catch (DbException e) {
            e.printStackTrace();
            Log.e(TAG, "查询数据失败:" + e.toString());
        }
    }

    //条件查询
    public void btn05(View view) {
        DbManager dbManager = MyApplication.getDbManager();
        try {
//            List<Student> studentList = dbManager.selector(Student.class).where("name","in",new String[]{"小明"}).findAll();
//            List<Student> studentList = dbManager.selector(Student.class).where("age","between",new int[]{3,8}).findAll();
            List<Student> studentList = dbManager.selector(Student.class).where("age",">",5).findAll();
//            List<Student> studentList = dbManager.selector(Student.class).where("age","in",new int[]{1,4,8}).and("name","in",new String[]{"小明"}).findAll();

            if(studentList != null){
                Log.e(TAG, "查询数据:" + studentList.toString());
                tvShow.setText(studentList.toString());
            }
        } catch (DbException e) {
            e.printStackTrace();
            Log.e(TAG, "查询数据失败:" + e.toString());
        }
    }

    //更新
    public void btn03(View view) {
        DbManager dbManager = MyApplication.getDbManager();

        Student student = new Student();
        student.setAge(10000000);
        student.setName("改名字了1111111");
        student.setID(65);

        try {
//            dbManager.update(student);
//            dbManager.update(student, "age");
            dbManager.update(Student.class, WhereBuilder.b("id","=",6),new KeyValue("name","新名字"));
            Log.e(TAG, "更新数据成功");
        } catch (DbException e) {
            e.printStackTrace();
            Log.e(TAG, "更新数据失败:" + e.toString());
        }

    }

    //删除
    public void btn04(View view) {
        DbManager dbManager = MyApplication.getDbManager();
        try {
            dbManager.delete(Student.class);
            Log.e(TAG, "查询数据成功");
        } catch (DbException e) {
            e.printStackTrace();
            Log.e(TAG, "查询数据失败:" + e.toString());
        }
    }
}
