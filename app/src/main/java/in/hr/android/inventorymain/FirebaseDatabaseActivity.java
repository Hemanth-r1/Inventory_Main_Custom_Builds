package in.hr.android.inventorymain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Date;

import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class FirebaseDatabaseActivity extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = firebaseDatabase.getReference("/");

    DataObjectModel dataObjectModel = new DataObjectModel();
    Button saveAndPrint, printFireButton;
    EditText editTextName, editTextQty;
    Spinner spinner;
    String[] itemList;
    double[] itemPrice;
    long invoiceNo = 0;
    ArrayAdapter <String> arrayAdapter;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    private void callFindViewById() {
        saveAndPrint = findViewById(R.id.btnSaveAndPrint);
        printFireButton = findViewById(R.id.btnPrint);
        editTextName = findViewById(R.id.editTextName);
        editTextQty = findViewById(R.id.editTextQty);
        spinner = findViewById(R.id.spinner);

        itemList = new String[]{"Petrol" , "Diesel"};
        itemPrice = new double[]{72.56, 52.78 };

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemList);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_database);
        callFindViewById();
        callOnClickListener();
        myRef.setValue("Hello, World!");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                invoiceNo = snapshot.getChildrenCount();

                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    DataTableRow row = new DataTableRow.Builder()
                            .value(String.valueOf(snapshot1.child("invoiceNo").getValue()))
                            .value(String.valueOf(snapshot1.child("customerName").getValue()))
                            .build();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void callOnClickListener() {
        saveAndPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataObjectModel.invoiceNo = invoiceNo + 1;
                dataObjectModel.customerName = editTextName.getText().toString();
                dataObjectModel.date = new Date().getTime();
                dataObjectModel.fuelType = spinner.getSelectedItem().toString();
                dataObjectModel.fuelQty =Double.parseDouble(editTextQty.getText().toString());
                dataObjectModel.amount = Double.parseDouble(decimalFormat.format(dataObjectModel.getFuelQty() * itemPrice[spinner.getSelectedItemPosition()]));

                myRef.child(String.valueOf(invoiceNo)).setValue(dataObjectModel);
                Toast.makeText(FirebaseDatabaseActivity.this, "Completed",Toast.LENGTH_SHORT).show();
                printPDf();
            }
        });
    }

    private void printPDf() {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        Paint linePaint = new Paint();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(250, 350, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        paint.setTextSize(15.5f);
        paint.setColor(Color.rgb(0,50,250));

        canvas.drawText("text sample", 20, 30, paint);
        canvas.drawText("sample2", 20,40,paint);
        canvas.drawText("Karnataka", 50, 55, paint);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setPathEffect(new DashPathEffect(new float[]{5,5}, 0));
        linePaint.setStrokeWidth(2);
        canvas.drawLine(20,65,230,65,linePaint);
        canvas.drawText("customer", 50, 70, paint);
        canvas.drawLine(20,85,230,85,linePaint);
        canvas.drawText(spinner.getSelectedItem().toString(), 20,135,paint);

        //decimalFormat.format(amount);
    }
  public  void  odPDf(){
      FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
      DatabaseReference myRef = firebaseDatabase.getReference("/");
      Button oldBtn;
      DataTable dataTable;
      DataTableHeader dataTableHeader;


      //normal header for data table
      // in my ref add valae event listenr

  }
}