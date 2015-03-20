package com.example.galwaytour;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class DisplaySelectedEvents extends ActionBarActivity {

    TextView displayEventName;
    TextView displayEventLocation;
    TextView displayEventStartDate;
    TextView displayEventInformation;
    ImageView imgview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_events);

        imgview = (ImageView) findViewById(R.id.displayEventImage);

        displayEventName = (TextView) findViewById(R.id.eventName);
        String value = getIntent().getStringExtra("name");

        displayEventName.setText("Name: " + value);

        displayEventLocation = (TextView) findViewById(R.id.eventLocation);
        String value2 = getIntent().getStringExtra("location");
        displayEventLocation.setText("Address: " + value2);

        displayEventStartDate = (TextView) findViewById(R.id.eventStartDate);
        final String value3 = getIntent().getStringExtra("start date");
        displayEventStartDate.setText("Website: " + value3);

        displayEventInformation = (TextView) findViewById(R.id.eventInformation);
        final String value4 = getIntent().getStringExtra("information");
        displayEventInformation.setText("Website: " + value4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_selected_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
