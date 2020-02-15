package com.example.madexperiments.ui.experiment_4;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.madexperiments.MainActivity;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Students";
    Context c;
    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null,1);        //3rd argument to be passed is CursorFactory instance
        c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS cse(rollno VARCHAR,name VARCHAR, marks VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS cse");

        // Create tables again
        onCreate(db);
    }

    void addStudent(String rollno,String name,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM cse WHERE rollno='"+rollno+"';",null);
        if(c.getCount()==0){
            ContentValues values = new ContentValues();
            values.put("rollno", rollno); // Student rollno
            values.put("name",name); // Student name
            values.put("marks",marks); // Student name
            db.insert("cse", null, values);// Inserting Row
            db.close();
            ShowMessage("SUCCESS","Record successfully inserted");
        }
        else{
            ShowMessage("ERROR","Student roll number is duplicate");
        }
    }
    void deleteStudent(String rollno){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM cse WHERE rollno='"+rollno+"';",null);
        if(c.moveToFirst()){
            db.execSQL("DELETE FROM cse WHERE rollno='"+rollno+"';");
            ShowMessage("SUCCESS","Record deleted successfully");
        }
        else{
            ShowMessage("ERROR","Student roll number does not exist");
        }
    }
    void modifyStudent(String rollno,String name, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM cse WHERE rollno="+rollno+";",null);
        if(c.moveToFirst()){
            db.execSQL("UPDATE cse SET name='"+name+"', marks='"+marks+"' WHERE rollno='"+rollno+"';");
            ShowMessage("SUCCESS","Record modified successfully");
        }
        else{
            ShowMessage("ERROR","Student roll number does not exist");
        }
    }
    void viewStudent(String rollno){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM cse WHERE rollno="+rollno+";",null);
        if(c.moveToFirst()){
            ShowMessage("SUCCESS","Name :"+c.getString(1)+"\nMarks :"+c.getString(2));
        }
        else{
            ShowMessage("ERROR","Student roll number does not exist");
        }
    }
    void viewAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM cse",null);
        if(c.getCount()!=0){
            c.moveToFirst();
            do{
                createDialog("Roll No:"+c.getString(0)+"\nName :"+c.getString(1)+"\nMarks :"+c.getString(2)).show();
            }while(c.moveToNext());
            ShowMessage("SUCCESS","All data displayed");
        }
        else{
            ShowMessage("ERROR","Empty table");
        }
    }
    private void ShowMessage(String title, String text){
        Toast.makeText(c,""+title+"\n"+text,Toast.LENGTH_SHORT).show();
    }

    public AlertDialog createDialog(String output){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage(output)
                .setPositiveButton("Next",
                        null);
        return builder.create();
    }

}

