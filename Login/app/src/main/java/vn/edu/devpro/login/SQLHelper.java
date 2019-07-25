package vn.edu.devpro.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "Account.db";
    final static String DB_TABLE = "Account";
    final static int DB_VERSION = 1;

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCreateTable = "CREATE TABLE Account (" +
                "id INTEGER NOT NULL," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "PRIMARY KEY(id)" +
                ");";
        sqLiteDatabase.execSQL(queryCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            sqLiteDatabase.execSQL("drop table if exists " + DB_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    public void insertIntoTable(String name, String pass){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("username", name);
        contentValues.put("password", pass);

        sqLiteDatabase.insert(DB_TABLE, null, contentValues);
        closeDB();
    }

    public ArrayList<Account> getAllAccount(){
        ArrayList<Account> accountArrayList = new ArrayList<>();
        Account account;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false, DB_TABLE, null, null,
                null, null, null, null, null);
        while(cursor.moveToNext()){
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));

            account = new Account(id, username, password);
            accountArrayList.add(account);
        }
        closeDB();
        return accountArrayList;
    }

    public void closeDB(){
        if(sqLiteDatabase != null)
            sqLiteDatabase.close();
        if(contentValues != null)
            contentValues.clear();
        if(cursor != null)
            cursor.close();
    }
}
