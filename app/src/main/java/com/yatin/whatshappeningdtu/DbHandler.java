package com.yatin.whatshappeningdtu;

/**
 * Created by yatin on 31-03-2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DATABASE";
    private static final String TABLE_NAME = "events";
    private static final String TABLE_ITEMS = "items";


    private static final String KEY_NAME = "Name";
    private static final String KEY_TIME = "Time";
    private static final String KEY_DATE = "Date";
    private static final String KEY_SOCIETY = "Society";
    private static final String KEY_VENUE = "Venue";
    private static final String KEY_DESCRIPTION = "Description";
    private static final String KEY_ITEM = "Item";

    public static final String _ID = BaseColumns._ID;


    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "Create Table " + TABLE_NAME + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " Text,"
                + KEY_DATE + " Text,"
                + KEY_TIME + " Text,"
                + KEY_SOCIETY + " Text,"
                + KEY_VENUE + " Text,"
                + KEY_DESCRIPTION + " Text)";

        db.execSQL(CREATE_TABLE);

        String CREATE_ITEM_TABLE = "Create Table " + TABLE_ITEMS + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_ITEM + " Text)";

        db.execSQL(CREATE_ITEM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);

        onCreate(db);
    }

    public void new_event(String Name, String Society, String Date, String Time,String Venue,String Description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, Name);
        values.put(KEY_SOCIETY, Society);
        values.put(KEY_DATE, Date);
        values.put(KEY_TIME, Time);
        values.put(KEY_VENUE, Venue);
        values.put(KEY_DESCRIPTION, Description);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void store_item(String item){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ITEM,item);
        db.insert(TABLE_ITEMS,null,values);
        db.close();

    }

    public void delete_item(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        //String query = "delete from " + TABLE_ITEMS + " where " + KEY_ITEM + " = '" +item+"'";
        //Cursor csr = db.rawQuery(query, null);
        db.delete(TABLE_ITEMS,KEY_ITEM +" = '" + item + "'",null);
       // csr.close();
        db.close();
    }
    public int get_count(){
        SQLiteDatabase db = this.getReadableDatabase();

        String count_query = "Select * from " + TABLE_ITEMS;
        Cursor csr = db.rawQuery(count_query,null);
        int count = csr.getCount();

        db.close();
        csr.close();

        return count;
    }
    public void delete_whole_item_table(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + TABLE_ITEMS;
        Cursor csr = db.rawQuery(query, null);
        csr.close();
        db.close();
    }

    public String[][] get_all_events(){
        SQLiteDatabase db = this.getReadableDatabase();

        String[][] events = new String[6][];
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor csr = db.rawQuery(query, null);
        csr.moveToFirst();
        int count = csr.getCount();
        int i = 0;
        while (count > 0) {
            events[0][i] = csr.getString(0);
            events[1][i] = csr.getString(1);
            events[2][i] = csr.getString(2);
            events[3][i] = csr.getString(3);
            events[4][i] = csr.getString(4);
            events[5][i] = csr.getString(5);

            i++;
            csr.moveToNext();
            count--;
        }
        csr.close();
        db.close();
        return events;
    }


    public String[] get_single_note(int position){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] pos_note = new String[4];
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor csr = db.rawQuery(query, null);
        csr.moveToFirst();
        int count = csr.getCount();
        int i = 0;
        while (count > 0) {
            if(i==position){
                pos_note[0] = csr.getString(0);
                pos_note[1] = csr.getString(1);
                pos_note[2] = csr.getString(2);
                pos_note[3] = csr.getString(3);
                pos_note[4] = csr.getString(4);
                pos_note[5] = csr.getString(5);


            }

            i++;
            csr.moveToNext();
            count--;
        }
        csr.close();
        db.close();
        return pos_note;
    }


    public String[] get_name() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor csr = db.rawQuery(query, null);
        csr.moveToFirst();
        int count = csr.getCount();
        String [] name = new String[count];

        int i = 0;
        while (count > 0) {
            name[i] = csr.getString(0);
            i++;
            csr.moveToNext();
            count--;
        }

        csr.close();
        db.close();
        return name;
    }
    public String[] get_item() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_ITEMS;
        Cursor csr = db.rawQuery(query, null);
        csr.moveToFirst();
        int count = csr.getCount();
        String [] item = new String[count];

        int i = 0;
        while (count > 0) {
            item[i] = csr.getString(1);
            i++;
            csr.moveToNext();
            count--;
        }

        csr.close();
        db.close();
        return item;
    }

}
