package in.hr.android.inventorymain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingAddButton, floatingEditButton, floatingNewButton;

    Button printButton;
    EditText editText;
    DataTable dataTable;
    SQLiteDatabase database;

    Date date = new Date();
    String datePattern = "dd-MM-YYYY";
    SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

    String timePattern = "hh:mm a";
    SimpleDateFormat timeFormat = new SimpleDateFormat(timePattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        printButton = findViewById(R.id.oldPrintBtn);
        editText = findViewById(R.id.oldPrintEditText);
        dataTable = findViewById(R.id.data_table);
        floatingAddButton = findViewById(R.id.floatingAddQuoteButton);
        //floatingEditButton = findViewById(R.id.floatingEditButton);
        floatingNewButton = findViewById(R.id.floatingAddBillButton);

        DataTableHeader header = new DataTableHeader.Builder().item("Invoice No", 5)
                .item("Bill Name", 5)
                .item("Date", 5)
                .item("Time", 5)
                .item("Amount", 5)
                .build();

        ArrayList<DataTableRow> rows = new ArrayList<>();DataTableRow row = new DataTableRow.Builder()
                .value("Name")
                .value(dateFormat.format(date.getTime()))
                .value(timeFormat.format(date.getTime()))
                .value("Amount")
                .build();

        rows.add(row);
        dataTable.setHeader(header);
        dataTable.setRows(rows);
        dataTable.inflate(this);

        floatingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Price is MANDATORY for selected Item!!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Convert_Quotation_To_PDFSQLActivity.class);
                startActivity(intent);
            }
        });

        floatingNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Price is MANDATORY for selected Item!!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Activity_bill_to_PDF.class);
                startActivity(intent);
            }
        });
    }
    public void printSelectedInvoice(View view) {
        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdfDocument pdfDocument = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    pdfDocument = new PdfDocument();

                    Paint paint = new Paint();

                    int invoiceForPDF = Integer.parseInt(editText.toString());

                    Cursor cursor = database.rawQuery("SELECT * FROM PdfTable where invoiceNo = " + invoiceForPDF, null);
                    cursor.moveToNext();


                    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1000, 900, 1).create();
                    PdfDocument.Page page = pdfDocument.startPage(pageInfo);

                    Canvas canvas = page.getCanvas();

                    //first text
                    paint.setTextSize(80);
                    canvas.drawText("Custom Builds", 30, 80, paint);

                    //second text
                    paint.setTextSize(30);
                    canvas.drawText("#21, Rajeev Gandji Nagar, Nandini Layout, bengaluru 96", 30, 120, paint);

                    //
                    paint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText("Invoice Number", canvas.getWidth() - 40, 80, paint);
                    canvas.drawText(String.valueOf(cursor.getInt(0)), canvas.getWidth() - 40, 80, paint);
                    paint.setTextAlign(Paint.Align.LEFT);

                    paint.setColor(Color.rgb(150, 150, 150));
                    canvas.drawRect(30, 150, canvas.getWidth() - 30, 160, paint);

                    paint.setColor(Color.BLACK);
                    canvas.drawText("Date", 50, 200, paint);
                    canvas.drawText(dateFormat.format(cursor.getLong(3)), 250, 200, paint);

                    canvas.drawText("Time", 620, 200, paint);
                    paint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(timeFormat.format(cursor.getLong(3)), canvas.getWidth() - 40, 200, paint);
                    paint.setTextAlign(Paint.Align.LEFT);

                    paint.setColor(Color.rgb(150, 150, 150));
                    canvas.drawRect(30, 250, 250, 300, paint);

                    paint.setColor(Color.WHITE);
                    canvas.drawText("Bill to", 50, 285, paint);

                    paint.setColor(Color.BLACK);
                    canvas.drawText("Customer Name:", 30, 350, paint);
                    canvas.drawText(cursor.getString(1), 280, 350, paint);

                    canvas.drawText("Phone", 620, 350, paint);
                    paint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(cursor.getString(2), canvas.getWidth() - 40, 350, paint);
                    paint.setTextAlign(Paint.Align.LEFT);

                    // create rectangular bar
                    paint.setColor(Color.rgb(150, 150, 150));
                    canvas.drawRect(30, 400, canvas.getWidth() - 40, 450, paint);

                    paint.setColor(Color.WHITE);
                    canvas.drawText("Item", 50, 435, paint);
                    canvas.drawText("Qty", 550, 435, paint);
                    paint.setTextAlign(Paint.Align.RIGHT);

                    canvas.drawText("Amount", canvas.getWidth() - 40, 435, paint);
                    paint.setTextAlign(Paint.Align.LEFT);

                    paint.setColor(Color.BLACK);
                    canvas.drawText(cursor.getString(4), 50, 480, paint);
                    canvas.drawText(String.valueOf(cursor.getInt(5)), 550, 480, paint);
                    paint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText(String.valueOf(cursor.getInt(6)), canvas.getWidth() - 40, 480, paint);

                    paint.setColor(Color.rgb(150, 150, 150));
                    canvas.drawRect(30, 550, canvas.getWidth() - 30, 560, paint);

                    paint.setColor(Color.BLACK);
                    canvas.drawText("Sub Total", 550, 600, paint);
                    canvas.drawText("Tax 4%", 550, 640, paint);
                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

                    canvas.drawText("TOTAL", 550, 680, paint);
                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

                    paint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(String.valueOf(cursor.getInt(6)), 970, 600, paint);
                    canvas.drawText(String.valueOf(cursor.getInt(6) * 4 / 100), 970, 640, paint);
                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                    canvas.drawText(String.valueOf(cursor.getInt(6) + (cursor.getInt(6) * 4 / 100)), 970, 680, paint);

                    paint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText("Make all check payable to \"Custom Builds\"", 30, 800, paint);
                    paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

                    canvas.drawText("Thank you very much", 30, 840, paint);
                    pdfDocument.finishPage(page);

                    File file = new File(MainActivity.this.getExternalFilesDir("/"), cursor.getInt(0) + "_CustomBuilds.pdf");

                    try {
                        pdfDocument.writeTo(new FileOutputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    pdfDocument.close();
                }
            }
        });
    }
}