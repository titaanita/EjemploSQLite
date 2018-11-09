package com.example.android.ejemplosqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteHelper extends SQLiteOpenHelper {
    public AdminSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //aquí creamos la tabla de usuario (dni, nombre, ciudad, numero)
        db.execSQL("create table usuario(dni integer primary key, nombre text, ciudad text, numero integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuario");
        db.execSQL("create table usuario(dni integer primary key, nombre text, ciudad text, numero integer)");
    }
}
