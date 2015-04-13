package com.example.galwaytour;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.app.PendingIntent.getActivity;


public class DisplaySelectedEvents extends ActionBarActivity {

    TextView displayEventName;
    TextView displayEventLocation;
    TextView displayEventStartDate;
    TextView displayEventInformation;
    ImageView imgview;
    Button btn1;
    private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_events);

        imgview = (ImageView) findViewById(R.id.displayEventImage);

        displayEventName = (TextView) findViewById(R.id.eventName);
        final String value = getIntent().getStringExtra("name");

        displayEventName.setText("Name: " + value);

        displayEventLocation = (TextView) findViewById(R.id.eventLocation);
        final String value2 = getIntent().getStringExtra("location");
        displayEventLocation.setText("Address: " + value2);

        displayEventStartDate = (TextView) findViewById(R.id.eventStartDate);
        final String value3 = getIntent().getStringExtra("start date");
        displayEventStartDate.setText("Start Date: " + value3);

        displayEventInformation = (TextView) findViewById(R.id.eventInformation);
        final String value4 = getIntent().getStringExtra("information");
        displayEventInformation.setText("Information: " + value4);

        btn1 = (Button) findViewById(R.id.setReminder);
        btn1.setOnClickListener(new View.OnClickListener() {

            AlertDialog.Builder builder = new AlertDialog.Builder(DisplaySelectedEvents.this);


            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                final Intent intent = new Intent(Intent.ACTION_EDIT);

                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("dtstart", value3);
                intent.putExtra("allDay", false);
                intent.putExtra("title", value);
                intent.putExtra("description", value4);

                builder.setTitle("Event Date");
                builder.setMessage("Input Date Manually!!");
                builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.example.galwaytour.R.menu.main, menu);

        MenuItem menuItem = menu.findItem(com.example.galwaytour.R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final  int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(getBaseContext(), "Clicked settings", Toast.LENGTH_LONG).show();
            return true;
        }else if(id == R.id.menu_item_share)
        {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello, from David Madugu");
            int i = Intent.ACTION_SEND.length();
            Log.i("intent", Integer.toString(i));
            startActivity(Intent.createChooser(shareIntent, "Share your thoughts"));
            Toast.makeText(getBaseContext(), "Clicked Share", Toast.LENGTH_LONG).show();
            return true;
        }
        else if ( id == R.id.menu_aroundme)
        {
            //Open around me class
            //Open around me class
            Intent intent = new Intent(DisplaySelectedEvents.this, Around_ME.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "Clicked Around Me", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
