package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "book_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "bookid";
    private static final String COL4 = "rank";
    private static final String COL5 = "memo";
    private static final String COL6 = "pages";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT," + COL3 + " Text," + COL4 +" TEXT," + COL5 +" TEXT," + COL6 +" TEXT);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String id, String rank, String memo, String pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, id);
        contentValues.put(COL4, rank);
        contentValues.put(COL5, memo);
        contentValues.put(COL6, pages);

        Log.d(TAG, "addData: Adding " + name + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void updateId(String newId, int id, String oldId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL3 +
                " = '" + newId + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL3 + " = '" + oldId + "'";
        Log.d(TAG, "updateId: query: " + query);
        Log.d(TAG, "updateId: Setting Id to " + newId);
        db.execSQL(query);
    }

    public void updateRank(String newRank, int id, String oldRank) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL4 +
                " = '" + newRank + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL4 + " = '" + oldRank + "'";
        Log.d(TAG, "updateRank: query: " + query);
        Log.d(TAG, "updateRank: Setting rank to " + newRank);
        db.execSQL(query);
    }

    public void updatePages(String newPages, int id, String oldPages) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL6 +
                " = '" + newPages + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL6 + " = '" + oldPages + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting Id to " + newPages);
        db.execSQL(query);
    }

    public void updateMemo(String newMemo, int id, String oldMemo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL5 +
                " = '" + newMemo + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL5 + " = '" + oldMemo + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting Id to " + newMemo);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    public void deleteId(int id, String bookId){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL3 + " = '" + bookId + "'";
        Log.d(TAG, "deleteId: query: " + query);
        Log.d(TAG, "deleteId: Deleting " + bookId + " from database.");
        db.execSQL(query);
    }

    public void deleteRank(int id, String bookRank){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL4 + " = '" + bookRank + "'";
        Log.d(TAG, "deleteRank: query: " + query);
        Log.d(TAG, "deleteRank: Deleting " + bookRank + " from database.");
        db.execSQL(query);
    }

    public void deletePages(int id, String bookPages){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL6 + " = '" + bookPages + "'";
        Log.d(TAG, "deletePages: query: " + query);
        Log.d(TAG, "deletePages: Deleting " + bookPages + " from database.");
        db.execSQL(query);
    }

    public void deleteMemo(int id, String bookMemo){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL5 + " = '" + bookMemo + "'";
        Log.d(TAG, "deleteMemo: query: " + query);
        Log.d(TAG, "deleteMemo: Deleting " + bookMemo + " from database.");
        db.execSQL(query);
    }
}
