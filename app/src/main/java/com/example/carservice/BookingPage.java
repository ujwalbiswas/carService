package com.example.carservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingPage extends AppCompatActivity {
  EditText ID, Name, Phone, date, time;

   ImageView I1;
  Button B1, bkBT,bkBT2,btnclr,btnupdate;
  DatabaseHelper mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        ID = findViewById(R.id.IDET);
        Name = findViewById(R.id.NameET);
        Phone = findViewById(R.id.phoneET);
        date = findViewById(R.id.dateET);
        time = findViewById(R.id.TimeET);
        btnclr=findViewById(R.id.backBT4);
        btnupdate=findViewById(R.id.backBT3);

        I1 = findViewById(R.id.I1);
        B1 = findViewById(R.id.B1);
        bkBT = findViewById(R.id.backBT);
        bkBT2 = findViewById(R.id.backBT2);

        mDb = new DatabaseHelper(this);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ID.getText().toString().isEmpty() || Name.getText().toString().isEmpty() || Phone.getText().toString().isEmpty() || date.getText().toString().isEmpty() || time.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the details", Toast.LENGTH_LONG).show();
                } else {

                    boolean update=mDb.updateData(ID.getText().toString(),Name.getText().toString(),Phone.getText().toString(), date.getText().toString(), time.getText().toString());
                    if(update==true)
                        Toast.makeText(BookingPage.this,"Data updated",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(BookingPage.this, "Data not updated",Toast.LENGTH_LONG).show();
                }


                }
        });

        btnclr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID.setText("");
                Name.setText("");
                Phone.setText("");
                date.setText("");
                time.setText("");
            }
        });

        bkBT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ID.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the details", Toast.LENGTH_LONG).show();
                } else {
                    deletedata();
                }
            }
        });
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ID.getText().toString().isEmpty() || Name.getText().toString().isEmpty() || Phone.getText().toString().isEmpty() || date.getText().toString().isEmpty() || time.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the details", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_LONG).show();
                    addData();
               }
            }
        });

        bkBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton();
            }
        });

    }

    public void deletedata() {
        Integer del = mDb.deleteData(ID.getText().toString());
        if (del > 0)
            Toast.makeText(BookingPage.this, "Data deleted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(BookingPage.this, "Dat not deleted", Toast.LENGTH_LONG).show();

    }



    public void addData() {
        String name = Name.getText().toString();
            // Name doesn't exist, insert the data into the database
            boolean insert = mDb.insertData(Integer.parseInt(ID.getText().toString()), name,
                    Integer.parseInt(Phone.getText().toString()), date.getText().toString(), time.getText().toString());

            if (insert) {
                Toast.makeText(BookingPage.this, "Data Inserted", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(BookingPage.this, "Data not inserted", Toast.LENGTH_LONG).show();
            }
        }

public void backButton(){
    Intent intent=new Intent(this, MainActivity.class);

    startActivity(intent);
    finish();
}

}



