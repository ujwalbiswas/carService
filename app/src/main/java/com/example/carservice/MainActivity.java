package com.example.carservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button ViewBT, BookingBT;
    TextView Service;
    EditText EnterName;
    DatabaseHelper mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewBT = (Button) findViewById(R.id.ViewBT);
        BookingBT = (Button) findViewById(R.id.BookingBT);
        EnterName = (EditText) findViewById(R.id.EnterNameET);
        mDb = new DatabaseHelper(this);

        ViewBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = EnterName.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                else{
                    viewBookingPage(name);
                }

            }
        });

        BookingBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   bookingpage();
            }
        });
    }
    public void bookingpage(){
        Intent intent=new Intent(MainActivity.this, BookingPage.class);
        startActivity(intent);
        finish();
    }

    public void viewBookingPage(String name) {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("Name", name);
            startActivity(intent);
            finish();
    }
}
