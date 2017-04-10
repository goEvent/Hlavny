package com.example.lucia.goevent;

/**
 * Created by lucia on 9.4.2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter {
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    // TODO: Create public field for each column in your table.
    // nová databáza
    static final String DATABASE_CREATE = "create table " + "Prihlásenie" +
            "( " + "ID" + " integer primary key autoincrement," + "Používateľ  text,Heslo text); ";
    private final Context context;
    public SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String userName, String password) {
        ContentValues newValues = new ContentValues();

        newValues.put("Používateľ", userName);
        newValues.put("Heslo", password);


        db.insert("Prihlásenie", null, newValues);

    }

    public int deleteEntry(String UserName) {

        String where = "Používateľ=?";
        int numberOFEntriesDeleted = db.delete("Prihlásenie", where, new String[]{UserName});
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query("Prihlásenie", null, " Používateľ=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NEEXISTUJE";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("Heslo"));
        cursor.close();
        return password;
    }

    public void updateEntry(String userName, String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("Používateľ", userName);
        updatedValues.put("Heslo", password);

        String where = "Používateľ = ?";
        db.update("Prihlásenie", updatedValues, where, new String[]{userName});
    }
}
