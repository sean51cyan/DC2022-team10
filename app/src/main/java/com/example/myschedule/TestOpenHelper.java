package com.example.myschedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TestOpenHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 2;

    // データーベース名
    private static final String DATABASE_NAME = "commentDB.db";
    private static final String TABLE_NAME = "comment";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_diff = "diff";
    private static final String COLUMN_NAME_easy = "easy";
    private static final String COLUMN_NAME_get = "get";
    private static final String COLUMN_NAME_pro = "pro";
    private static final String COLUMN_NAME_mood = "mood";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_diff + " INTEGER," +
                    COLUMN_NAME_easy + " INTEGER," +
                    COLUMN_NAME_get + " INTEGER," +
                    COLUMN_NAME_pro + " INTEGER," +
                    COLUMN_NAME_mood + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public TestOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        db.execSQL(
                SQL_CREATE_ENTRIES
        );

        Log.d("debug", "onCreate(SQLiteDatabase db)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}