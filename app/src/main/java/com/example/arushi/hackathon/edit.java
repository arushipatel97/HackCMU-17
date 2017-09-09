package com.example.arushi.hackathon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    public void charityButton(View v){
        Button button = (Button) v;
        Intent intent = new Intent(this, PopulateList.class);
        startActivity(intent);
    }
}


