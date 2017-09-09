package com.example.arushi.hackathon;

import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;
import android.widget.TextView;

/**
 * Created by Arushi on 9/9/2017.
 */
public class History extends Activity {

    TextView tv_steps;
    TextView donationAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.history);
    }
}