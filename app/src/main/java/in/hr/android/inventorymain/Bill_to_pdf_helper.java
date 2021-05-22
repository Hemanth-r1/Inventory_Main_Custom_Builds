package in.hr.android.inventorymain;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Bill_to_pdf_helper extends SQLiteOpenHelper {

    public Bill_to_pdf_helper( Context context) {
        super(context, "BillDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // with open helper step 2
        String createBill = "CREATE TABLE BillTable(billNo INTEGER PRIMARY KEY AUTOINCREMENT,billName TEXT ,date TEXT,time TEXT, mobileNo INTEGER,amount INTEGER);";
        db.execSQL(createBill);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // step 3
    public void insertBill(String billName, String date, String time,String mobileNo, String amount){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("billName", billName);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("mobileNo",mobileNo);
        contentValues.put("amount",amount);
        sqLiteDatabase.insert("BillTable", null, contentValues);
    }
}
