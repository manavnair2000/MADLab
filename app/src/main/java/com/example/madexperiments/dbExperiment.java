package com.example.madexperiments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class dbExperiment extends SQLiteOpenHelper {

    static final private String DB_NAME ="StudentDB";
    static final private String DB_TABLE ="student";
    static final private int DB_VER = 1;

    SQLiteDatabase db;
    Context ctx;

    public dbExperiment(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        ctx = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+DB_NAME+" (rollno integer primary key autoincrement, name varchar, marks integer);");
        Log.i("Database","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(sqLiteDatabase);
    }


    public void insertData(String name,int marks){
        db = getWritableDatabase();

        db.execSQL("insert into "+DB_TABLE+" (rollno,name) values('"+name+"',"+marks+");");
        Toast.makeText(ctx,"Data saved successfully!!",Toast.LENGTH_SHORT).show();
    }

    public void getAll(){
        db = getReadableDatabase();
        Cursor c1 = db.rawQuery("select * from "+DB_TABLE,null);
        StringBuilder str = new StringBuilder();

        while(c1.moveToNext()){
            String name = c1.getString(1);
            int marks = c1.getInt(2);
            str.append(name+"\t"+marks+"\n");
        }
        Toast.makeText(ctx,str.toString(),Toast.LENGTH_SHORT);
    }
    public void modify(int id,String name,int marks){
        db = getWritableDatabase();
        //db.execSQL();
    }
}
