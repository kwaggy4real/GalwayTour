package com.example.galwaytour;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.view.MenuItemCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Bus_Information extends ActionBarActivity {

    TextView citylink;
    TextView gobus;
    TextView buseirrean;
    Button viewgobus;
    Button viewcity;
    Button viewbuseirean;
    TextView sectiontitle;
    Button bus401;
    Button bus402a;
    Button bus402b;
    Button bus403a;
    Button bus403b;
    Button bus404a;
    Button bus404b;
    Button bus405a;
    Button bus405b;
    Button bus407;
    Button bus409;
    Button bus410a;
    Button bus410b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus__information);

        citylink = (TextView) findViewById(R.id.citylinkInfo);
        citylink.setText("City Link Routes: \n" + "Galway-Dublin Airport Express \nGalway-Athlone-Dublin/Airport \nGalway-Limerick-Cork/Cork Airport \nGalway-Clifden" + "\nTouck to Visit Website for More Information");
        citylink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.citylink.ie/"));
                startActivity(openbrowser);
            }
        });

        gobus = (TextView) findViewById(R.id.gobusInfo);
        gobus.setText("Go Bus Routes: \n" + "Galway-Dublin Airport \nGalway--Dublin City \nCork-Dublin City-Dublin Airport \n" + "\nTouck to Visit Website for More Information");
        gobus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gobus.ie/"));
                startActivity(openbrowser);
            }
        });


        buseirrean = (TextView) findViewById(R.id.buseirreanInfo);
        buseirrean.setText("Bus Eirean Routes: \n" + "Galway - Castlebar - Westport - Ballina and return \nGalway - Sligo - Enniskillen - Belfast and return \nDublin-Dublin Airport-Athlone-Ballinasloe-Galway and return \nLimerick - Galway Express" + "\nTouck to Visit Website for More Information");
        buseirrean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/"));
                startActivity(openbrowser);
            }
        });

        viewbuseirean = (Button) findViewById(R.id.viewbuseireanonmap);
        viewbuseirean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bus_Information.this, MapFragment.class);
                intent.putExtra("Latitude", "53.273871855023685");
                intent.putExtra("Longitude", "-9.047144651412964");
                intent.putExtra("Name", "Bus Eirean Coach Station");
                startActivity(intent);
            }
        });

        viewcity = (Button) findViewById(R.id.viewcityonmap);
        viewcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bus_Information.this, MapFragment.class);
                intent.putExtra("Latitude", "53.27452304811188");
                intent.putExtra("Longitude", "-9.045406579971313");
                intent.putExtra("Name", "City Link Coach Station");
                startActivity(intent);
            }
        });

        viewgobus = (Button) findViewById(R.id.viewgobusonmap);
        viewgobus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bus_Information.this, MapFragment.class);
                intent.putExtra("Latitude", "53.27452304811188");
                intent.putExtra("Longitude", "-9.045406579971313");
                intent.putExtra("Name", "Go Bus Coach Station");
                startActivity(intent);
            }
        });

        sectiontitle = (TextView) findViewById(R.id.sectiontitle);

       bus401 = (Button) findViewById(R.id.Bus_401);
        bus401.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360756547-401.pdf"));
                startActivity(openbrowser);
            }
        });

        bus402a = (Button) findViewById(R.id.Bus_402_a);
        bus402a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360756548-402_Merlin-Park-Seacrest.pdf"));
                startActivity(openbrowser);
            }
        });

        bus402b = (Button) findViewById(R.id.Bus_402_b);
        bus402b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360756623-402_Seacrest-Merlin-Park.pdf"));
                startActivity(openbrowser);
            }
        });

        bus403a= (Button) findViewById(R.id.Bus_403_a);
        bus403a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360847232-403_Eyre-Square-Castlepark.pdf"));
                startActivity(openbrowser);
            }
        });

        bus403b= (Button) findViewById(R.id.Bus_403_b);
        bus403b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360847233-403_Castlepark-EyreSquare.pdf"));
                startActivity(openbrowser);
            }
        });

        bus404a= (Button) findViewById(R.id.Bus_404_a);
        bus404a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360756626-404_Eyre-Square-Newcastle.pdf"));
                startActivity(openbrowser);
            }
        });

        bus404b= (Button) findViewById(R.id.Bus_404_b);
        bus404b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360756699-404_Newcastle-Eyre-Square.pdf"));
                startActivity(openbrowser);
            }
        });

        bus405a= (Button) findViewById(R.id.Bus_405_a);
        bus405a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360756700-405_Ballybane-Rahoon.pdf"));
                startActivity(openbrowser);
            }
        });

        bus405b= (Button) findViewById(R.id.Bus_405_b);
        bus405b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360756701-405_Rahoon-Ballybane.pdf"));
                startActivity(openbrowser);
            }
        });

        bus407= (Button) findViewById(R.id.Bus_407);
        bus407.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1361979525-407.pdf"));
                startActivity(openbrowser);
            }
        });

        bus409= (Button) findViewById(R.id.Bus_409);
        bus409.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360756795-409_Parkmore-Ind-Estate-Eyre-Square.pdf"));
                startActivity(openbrowser);
            }
        });

        bus410a= (Button) findViewById(R.id.Bus_410_a);
        bus410a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360756796-410_Eyre-Square-Oranmore.pdf"));
                startActivity(openbrowser);
            }
        });

        bus410b= (Button) findViewById(R.id.Bus_410);
        bus410b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openbrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buseireann.ie/pdf/1360756797-410_Oranmore-Eyre-Square.pdf"));
                startActivity(openbrowser);
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bus__information, menu);
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
