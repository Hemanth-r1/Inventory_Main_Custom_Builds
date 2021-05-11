package in.hr.android.inventorymain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingAddButton, floatingEditButton, floatingNewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingAddButton = findViewById(R.id.floatingAddButton);
        floatingEditButton = findViewById(R.id.floatingEditButton);
        floatingNewButton = findViewById(R.id.floatingNewButton);

        floatingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Convert_Quotation_To_PDFSQLActivity.class);
                startActivity(intent);
            }
        });
    }
}