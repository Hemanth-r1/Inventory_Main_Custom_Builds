package in.hr.android.inventorymain;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConvertToPdfHelperSQL extends SQLiteOpenHelper {

    public ConvertToPdfHelperSQL(@Nullable Context context ) {
        super(context, "QuoteDatabase", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // with open helper step 2
        String createTable = "CREATE TABLE QuoteTABLE(quoteNo INTEGER PRIMARY KEY AUTOINCREMENT, customerName TEXT, mobileNo TEXT, date INTEGER, item TEXT, qty INTEGER, amount INTEGER);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    // step 3
    public void insert(String customerName, String contactNo, Long date, String item, int qty, int amount){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customerName",customerName);
        contentValues.put("contactNo",contactNo);
        contentValues.put("date",date);
        contentValues.put("item",item);
        contentValues.put("qty",qty);
        contentValues.put("amount",amount);

        sqLiteDatabase.insert("PdfTABLE", null, contentValues);
    }
}

