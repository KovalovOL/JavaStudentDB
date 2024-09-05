package com.example.studentdb;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import Data.DatabaseHandler;
import Model.Student;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHandler databaseHandler =new DatabaseHandler(this);

//        databaseHandler.addStudent(new Student("It", "Alex", "Kovalov", 95));
//        databaseHandler.addStudent(new Student("It", "Oleg", "Kukuskin", 82));
//        databaseHandler.addStudent(new Student("FM", "Mish", "Bukov", 73));
//        databaseHandler.addStudent(new Student("FM", "Max", "Musorov", 52));

        List<Student> studentList = databaseHandler.getAllStudent();

        for(Student student : studentList) {
            Log.d("CheckStudents", "Name: " + student.getName()
                    + " Faculty: " + student.getFaculty()
                    + " Id: " + student.getId()
                    );

        }

        Log.d("CheckStudents", "Name: "+ databaseHandler.getStudent(8).getName());
    }
}