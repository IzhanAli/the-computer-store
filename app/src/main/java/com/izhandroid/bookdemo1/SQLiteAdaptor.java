package com.izhandroid.bookdemo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLiteAdaptor extends SQLiteOpenHelper {

    public static final int Database_Version = 1;
    public static final String Database_Name = "DB";
    public static final String Table_Name = "Orders";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "ITEM";
    public static final String COL_3 = "PRICE";
    public static final String COL_4 = "QUANTITY";
    public static final String COL_5 = "NAME";
    public static final String COL_6 = "ADDRESS";
    public static final String COL_7 = "EMAIL";
    public static final String COL_8 = "ORDERID";
    public static final String COL_9 = "TIME";

    Context context;

    public SQLiteAdaptor(Context context) {
        super(context, Database_Name, null, Database_Version);


    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + Table_Name + "("
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT,"
                + COL_3 + " TEXT,"
                + COL_4 + " TEXT,"
                + COL_5 + " TEXT,"
                + COL_6 + " TEXT,"
                + COL_7 + " TEXT,"
                + COL_8 + " TEXT,"
                + COL_9 + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    void insertUserDetails( String item, String price, String quantity, String name, String address, String email, String orderid, String time){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(COL_2, item);
        cValues.put(COL_3, price);
        cValues.put(COL_4, quantity);
        cValues.put(COL_5, name);
        cValues.put(COL_6, address);
        cValues.put(COL_7, email);
        cValues.put(COL_8, orderid);
        cValues.put(COL_9, time);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Table_Name,null, cValues);
        db.close();
    }

    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id, item, price, quantity, name, address, email, orderid, time FROM "+ Table_Name;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("id",cursor.getString(cursor.getColumnIndex(COL_1)));
            user.put("item",cursor.getString(cursor.getColumnIndex(COL_2)));
            user.put("price",cursor.getString(cursor.getColumnIndex(COL_3)));
            user.put("quantity",cursor.getString(cursor.getColumnIndex(COL_4)));
            user.put("name",cursor.getString(cursor.getColumnIndex(COL_5)));
            user.put("address",cursor.getString(cursor.getColumnIndex(COL_6)));
            user.put("email",cursor.getString(cursor.getColumnIndex(COL_7)));
            user.put("orderid",cursor.getString(cursor.getColumnIndex(COL_8)));
            user.put("time",cursor.getString(cursor.getColumnIndex(COL_9)));
            userList.add(user);
        }
        return  userList;
    }
    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid ){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String  query = "SELECT item, price, quantity, name, address, email, orderid FROM "+ Table_Name;

        Cursor cursor = db.query(Table_Name, new String[]{COL_2, COL_3, COL_4, COL_5, COL_6, COL_7, COL_8, COL_9}, COL_1+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("item",cursor.getString(cursor.getColumnIndex(COL_2)));
            user.put("price",cursor.getString(cursor.getColumnIndex(COL_3)));
            user.put("quantity",cursor.getString(cursor.getColumnIndex(COL_4)));
            user.put("name",cursor.getString(cursor.getColumnIndex(COL_5)));
            user.put("address",cursor.getString(cursor.getColumnIndex(COL_6)));
            user.put("email",cursor.getString(cursor.getColumnIndex(COL_7)));
            user.put("orderid",cursor.getString(cursor.getColumnIndex(COL_8)));
            user.put("time",cursor.getString(cursor.getColumnIndex(COL_9)));
            userList.add(user);
        }
        return  userList;
    }



   /* public String getEmployeeName(String empNo) {
        Cursor cursor = null;
        String empName = "";
        try {
            cursor = SQLiteDatabaseInstance_.rawQuery("SELECT EmployeeName FROM Employee WHERE EmpNo=?", new String[] {empNo + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                empName = cursor.getString(cursor.getColumnIndex("EmployeeName"));
            }
            return empName;
        }finally {
            cursor.close();
        }
    }*/
    // Delete User Details

    // Update User Details

}
