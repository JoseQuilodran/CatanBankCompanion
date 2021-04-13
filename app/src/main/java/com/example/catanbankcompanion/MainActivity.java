package com.example.catanbankcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton ibAuto=(ImageButton) findViewById(R.id.ibAuto);
        ibAuto.setOnClickListener((View v) -> {
            Intent t= new Intent(MainActivity.this,AutoMode.class);
            startActivity(t);
        });

        ImageButton ibManual=(ImageButton) findViewById(R.id.ibManual);
        ibManual.setOnClickListener((View v) -> {
            Intent t= new Intent(MainActivity.this,ManualMode.class);
            startActivity(t);
        });

        ImageButton ibCamera=(ImageButton) findViewById(R.id.ibCamera);
        ibCamera.setOnClickListener((View v) -> {
            Intent t= new Intent(MainActivity.this,CameraMode.class);
            startActivity(t);
        });
    }
}
