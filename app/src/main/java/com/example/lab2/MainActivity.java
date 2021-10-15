package com.example.lab2;

import static android.Manifest.permission.CALL_PHONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String TAG = "lifecycle";
    EditText enterNumber;
    ImageButton callBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callBtn = findViewById(R.id.button);
        enterNumber = findViewById(R.id.number);
        Log.d(TAG,"Activity created");

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phNum = enterNumber.getText().toString();

                if (!phNum.isEmpty()) {
                    String dial = "tel:" + phNum;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(dial));
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(intent);
                    } else {
                        requestPermissions(new String[]{CALL_PHONE}, 1);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter a phone number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"Activity started");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"Activity resumed");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"Activity paused");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"Activity stopped");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"Activity destroyed");
    }
}