package com.example.arushi.hackathon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit extends home {
    public EditText iRate;
    public EditText dGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    public void charityButton(View v) {
        Button button = (Button) v;
        Intent intent = new Intent(this, PopulateList.class);
        startActivity(intent);
    }

    public void buttonOnClick(View v){
        Button button = (Button) v;
        iRate = (EditText) findViewById(R.id.rate);
        Float x = Float.parseFloat(iRate.getText().toString());
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putFloat(bleh, x);
        if (editor.commit()){
            Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
            float current;
            float oldR = mSharedPreferences.getFloat(bleh, (float)0.0);
            current = (float)(steps*oldR);
            donationAmount.setText(String.format("$%.2f",current/(100*100)));
        }
    }


    public void gButton(View v){
        Button button = (Button) v;
        dGoal = (EditText) findViewById(R.id.goal);
        float temp = Float.parseFloat(dGoal.getText().toString());
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putFloat(bleh1, temp);
        if (editor.commit()) {
            Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
            float oldR = mSharedPreferences.getFloat(bleh1, (float)0.0);
            goalAmount.setText(String.format("$%.2f", oldR));

        }
    }
}
