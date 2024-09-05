package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Student;
import Util.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_FACULTY + " TEXT,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_SURNAME + " TEXT,"
                +Util.KEY_SCORE + " INTEGER" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_FACULTY, student.getFaculty());
        contentValues.put(Util.KEY_NAME, student.getName());
        contentValues.put(Util.KEY_SURNAME, student.getSurname());
        contentValues.put(Util.KEY_SCORE, student.getScore());

        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    public Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                Util.TABLE_NAME,
                new String[]{Util.KEY_ID,
                        Util.KEY_FACULTY, Util.KEY_NAME, Util.KEY_SURNAME, Util.KEY_SCORE},
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        Student student = new Student();
        if(cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    student.setId(cursor.getInt(0));
                    student.setFaculty(cursor.getString(1));
                    student.setName(cursor.getString(2));
                    student.setSurname(cursor.getString(3));
                    student.setScore(cursor.getInt(4));
                }
            } finally {
                cursor.close();
            }
        }
        return student;
    }

    public List<Student> getAllStudent() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Student> studentList = new ArrayList<>();
        String selectAllStudents = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllStudents, null);

        if(cursor.moveToFirst()) {
            try{
                do {
                    studentList.add(new Student(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(4)
                    ));
                } while (cursor.moveToNext());
            } finally {
                cursor.close();
            }
        }
        return studentList;
    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_FACULTY, student.getFaculty());
        contentValues.put(Util.KEY_NAME, student.getName());
        contentValues.put(Util.KEY_SURNAME, student.getSurname());
        contentValues.put(Util.KEY_SCORE, student.getScore());

        return db.update(
                Util.TABLE_NAME,
                contentValues,
                Util.KEY_ID + "=?",
                new String[] {String.valueOf(student.getId())}
        );
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                Util.TABLE_NAME,
                Util.KEY_ID + "=?",
                new String[] {String.valueOf(student.getId())}
        );
        db.close();
    }

    public int getCountOfStudents() {
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = 0;
        if (cursor != null) {
            try {
                count = cursor.getCount();
            } finally {
                cursor.close();
            }
        }
        return count;
    }

}
