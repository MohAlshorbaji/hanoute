package com.example.hanoute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button ownersingin;
    private Button custmersingin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     ownersingin = findViewById(R.id.owner);
     custmersingin = findViewById(R.id.custmer);
       ownersingin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,Singinwoner.class);
               startActivity(intent);
           }
       });
     custmersingin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(MainActivity.this,Singincustmer.class);
             startActivity(intent);

         }
     });
    }
}