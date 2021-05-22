package in.hr.android.inventorymain;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class Convert_Quotation_To_PDF_Helper_SQL extends SQLiteOpenHelper {

    String processorText, motherboardText, ramText, graphicsCardText,
            ssdText, hddText, powerSupplyText, headsetText, keyboardText,
            mouseText, cabinetText, monitorText, caseFansText, cableText, serviceText, coolerText;

    String processorDescription, motherboardDescription, ramDescription, graphics_cardDescription,
            power_supplyDescription, ssdDescription, hddDescription, coolerDescription, cabinetDescription,
            case_fansDescription, keyboardDescription,
            mouseDescription, monitorDescription, serviceDescription, cableDescription, headsetDescription;

    int processorPrice = 0;
    int motherboardPrice = 0;
    int ramPrice = 0;
    int graphicsCardPrice = 0;
    int ssdPrice = 0;
    int hddPrice = 0;
    int powerSupplyPrice = 0;
    int headsetPrice = 0;
    int keyboardPrice = 0;
    int mousePrice = 0;
    int cabinetPrice = 0;
    int monitorPrice = 0;
    int caseFansPrice = 0;
    int cablePrice = 0;
    int servicePrice = 0;
    int coolerPrice = 0;

    public Convert_Quotation_To_PDF_Helper_SQL(@Nullable Context context) {
        super(context, "QuoteDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createBill = "CREATE TABLE QuoteTABLEMain(quoteNo INTEGER PRIMARY KEY AUTOINCREMENT,date INTEGER, mobileNo INTEGER,  processorName TEXT, processorPrice INTEGER, processorDescription TEXT, motherboardName TEXT, motherboardPrice INTEGER, motherboardDescription TEXT, ramName TEXT, ramPrice INTEGER, ramDescription TEXT, graphicsCardName TEXT, graphicsCardPrice INTEGER, graphicsCardDescription TEXT, ssdName TEXT, ssdPrice INTEGER, ssdDescription TEXT, amount INTEGER);";
        db.execSQL(createBill);
   /*
        // with open helper step 2
        String createMainTable = "CREATE TABLE QuoteTABLEMain(quoteNo INTEGER PRIMARY KEY AUTOINCREMENT,date INTEGER, mobileNo INTEGER,  processorName TEXT, processorPrice INTEGER, processorDescription TEXT, motherboardName TEXT, motherboardPrice INTEGER, motherboardDescription TEXT, ramName TEXT, ramPrice INTEGER, ramDescription TEXT, graphicsCardName TEXT, graphicsCardPrice INTEGER, graphicsCardDescription TEXT, ssdName TEXT, ssdPrice INTEGER, ssdDescription TEXT, amount INTEGER);";
        db.execSQL(createMainTable);


        String createPeripheralTable = "CREATE TABLE QuoteTABLEPeripheral(quoteNo INTEGER PRIMARY KEY AUTOINCREMENT, customerName TEXT, mobileNo TEXT, date INTEGER, processorName TEXT, processorPrice INTEGER, processorDescription TEXT, motherboardName TEXT, motherboardPrice INTEGER, motherboardDescription TEXT, motherboardName TEXT, motherboardPrice INTEGER, motherboardDescription TEXT,motherboardName TEXT, motherboardPrice INTEGER, motherboardDescription TEXT, motherboardName TEXT, motherboardPrice INTEGER, motherboardDescription TEXT, amount INTEGER);";
        db.execSQL(createPeripheralTable);

         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void getPart(){

    }
    // step 3
    public void insertMain(String date, int customerMobileNumber,
                           String processorText, int processorPrice, String processorDescription,
                           String motherboardText, int motherboardPrice, String motherboardDescription,
                           String ramText, int ramPrice, String ramDescription,
                           String graphicsCardText, int graphicsCardPrice, String graphics_cardDescription ,
                           String ssdText, int ssdPrice, String ssdDescription, int amount){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("customerMobileNumber",customerMobileNumber);
        contentValues.put("processorText",processorText);
        contentValues.put("processorPrice",processorPrice);
        contentValues.put("processorDescription",processorDescription);
        contentValues.put("motherboardText",motherboardText);
        contentValues.put("motherboardPrice",motherboardPrice);
        contentValues.put("motherboardDescription", motherboardDescription);
        contentValues.put("ramText",ramText);
        contentValues.put("ramPrice",ramPrice);
        contentValues.put("ramDescription",ramDescription);
        contentValues.put("graphicsCardText",graphicsCardText);
        contentValues.put("graphicsCardPrice",graphicsCardPrice);
        contentValues.put("graphics_cardDescription", graphics_cardDescription);
        contentValues.put("ssdText",ssdText);
        contentValues.put("ssdPrice",ssdPrice);
        contentValues.put("ssdDescription",ssdDescription);
        contentValues.put("amount",amount);

        sqLiteDatabase.insert("QuoteTABLEMain", null, contentValues);
    }

    public void insertPeripheral(String customerName, int customerMobileNumber, long date, String processorText,
                           int processorPrice, String processorDescription, String motherboardText, int motherboardPrice,
                           String motherboardDescription, String ramText, int ramPrice, String ramDescription,
                           String graphicsCardText, int graphicsCardPrice, String graphics_cardDescription ,
                           String ssdText, int ssdPrice, String ssdDescription){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customerName",customerName);
        contentValues.put("customerMobileNumber",customerMobileNumber);
        /*contentValues.put("date", date);
        contentValues.put("item",item);
        contentValues.put("qty",qty);
        contentValues.put("amount",amount);
        contentValues.put("customerName",customerName);
        contentValues.put("customerMobileNumber",customerMobileNumber);
        contentValues.put("date", date);
        contentValues.put("item",item);
        contentValues.put("qty",qty);
        contentValues.put("amount",amount);
        contentValues.put("customerName",customerName);
        contentValues.put("customerMobileNumber",customerMobileNumber);
        contentValues.put("date", date);
        contentValues.put("item",item);
        contentValues.put("qty",qty);
        contentValues.put("amount",amount);*/

        sqLiteDatabase.insert("QuoteTABLEMain", null, contentValues);
    }
}

