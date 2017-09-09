package com.example.arushi.hackathon;

import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.appindexing.Action;
        import com.google.android.gms.appindexing.AppIndex;
        import com.google.android.gms.common.api.GoogleApiClient;

public class home extends MainActivity implements SensorEventListener {

    public static final String PREFERENCES_RATE = "test";
    public SharedPreferences mSharedPreferences;
    public static final String bleh = "";
    public static final String bleh1 = "";

    //code based on Tihomir RAdeff's video

    TextView tv_steps;
    TextView donationAmount;
    TextView goalAmount;

    int steps;

    SensorManager sensorManager;

    boolean running = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv_steps = (TextView) findViewById(R.id.tv_steps);
        donationAmount= (TextView) findViewById(R.id.donationAmount);
        goalAmount= (TextView ) findViewById(R.id.goalAmount);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        mSharedPreferences = getSharedPreferences(PREFERENCES_RATE, MODE_WORLD_WRITEABLE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        //if you unregister hardware, it will stop detecting steps
        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            tv_steps.setText(String.valueOf((int)event.values[0]));
            float current;
            Float oldR = mSharedPreferences.getFloat(bleh, (float)0.0);
            Float temp=mSharedPreferences.getFloat(bleh1, (float)0.0);
            current = (float)(event.values[0]*oldR);
            steps = (int)event.values[0];
            donationAmount.setText(String.format("$%.2f",current/(100*100)));
            goalAmount.setText(String.format("$%.2f", temp));

        }
    }


    public void hButton(View v){
        Button button = (Button) v;
        Intent intent = new Intent(this, record.class);
        startActivity(intent);
    }

    public void eButton(View v){
        Button button = (Button) v;
        Intent intent = new Intent(this, edit.class);
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.arushi.hackathon/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.arushi.hackathon/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
