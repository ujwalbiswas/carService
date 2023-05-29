package com.example.carservice;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    EditText NameTV,DateTV,TimeTV,PhoneTV;

    Button bakBT;
    DatabaseHelper mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        NameTV = (EditText) findViewById(R.id.editTextText2);
        PhoneTV = (EditText) findViewById(R.id.editTextText3);
        DateTV = (EditText) findViewById(R.id.editTextText4);
        TimeTV = (EditText) findViewById(R.id.editTextText5);

        bakBT = (Button) findViewById(R.id.backBT);
        mDb = new DatabaseHelper(this);

        Intent intent = getIntent();
        Integer IDD = Integer.valueOf(intent.getStringExtra("Name"));

        try {


            Cursor cursor = mDb.getAllData(IDD);

            int NameIndex = cursor.getColumnIndex(DatabaseHelper.COL_2);
            int pn = cursor.getColumnIndex(DatabaseHelper.COL_3);
            int DateIndex = cursor.getColumnIndex(DatabaseHelper.COL_4);
            int TimeIndex = cursor.getColumnIndex(DatabaseHelper.COL_5);


            if (cursor.moveToFirst()) {
                NameTV.setText(cursor.getString(NameIndex));
                PhoneTV.setText(cursor.getString(pn));
                DateTV.setText(cursor.getString(DateIndex));
                TimeTV.setText(cursor.getString(TimeIndex));

            } else {
                Toast.makeText(this, "No name found " + IDD, Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        }catch (Exception e){
            Toast.makeText(this, "An Error Have Occured", Toast.LENGTH_SHORT).show();
        }
        bakBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
                finish();
            }
        });





    }
}