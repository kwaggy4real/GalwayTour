package com.example.galwaytour;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.view.ViewGroup;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.OnMapReadyCallback;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;


public class Around_ME extends ActionBarActivity implements OnMapReadyCallback {


 //   private Location bestReading;
    private ImageButton toggle_hotel;
    private ImageButton toggle_events;
    private ImageButton toggle_leisure;
    private ImageButton toggle_res_pubs;
    private ImageButton toggle_hostels;
    private Button toggle_b_and_b;
    private Button toggle_attractions;
    private Button toggle_shops;
    private Button toggle_bike_hires;
    String TAG = "Round MMe Activity";

    static Data all_data;
    static List<Data> all_data_list = new ArrayList<Data>();
    Marker marker;
   // String name;
    static final long oneMinute = 1000 * 60;
    static final long twoMinutes = oneMinute *2;
    static final long fiveMinutes = oneMinute * 5;
    static final long measureTime = 1000 * 30;
    static final long pollingFreq = 1000 * 10;
    static final float minAccuracy = 25.0f;
    static final float minLastReadAccuracy = 500.0f;
    static final float minDistance = 10.0f;
    Double my_latitude;
    Double my_longitude;

    private Location bestReading;
    private LocationManager loc_Manager;
    private LocationListener loc_Listener;
    private boolean FirstUpdate = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_around__me);

        try {
            new LoadData().execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

     final  com.google.android.gms.maps.MapFragment fragment = (com.google.android.gms.maps.MapFragment) getFragmentManager().findFragmentById(R.id.around_me_map);
        if(null == (loc_Manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE))) {

            Toast.makeText(this, "Could not Acquire reference to Location Manager", Toast.LENGTH_LONG).show();
            Log.i(TAG, "Location Manager is not null");
              /*ann code to  return to home page */
        }

        toggle_hotel = (ImageButton) findViewById(R.id.map_show_hotels);
        toggle_events = (ImageButton) findViewById(R.id.map_show_events);
        toggle_leisure = (ImageButton) findViewById(R.id.map_show_leisure);
        toggle_res_pubs = (ImageButton) findViewById(R.id.map_show_res_pubs);
        toggle_hostels = (ImageButton) findViewById(R.id.map_show_hostels);
        toggle_b_and_b = (Button) findViewById(R.id.map_show_b_and_b);
        toggle_attractions = (Button) findViewById(R.id.map_show_Attractions);
        toggle_shops = (Button) findViewById(R.id.map_show_Shops);
        toggle_bike_hires = (Button) findViewById(R.id.map_show_BikeHires);

        toggle_bike_hires.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                fragment.getMap().clear();

                String lat = null;
                String lng = null;

                for (int i = 0; i < all_data_list.size(); i++) {
                    lat = all_data_list.get(i).getLatitude();
                    lng = all_data_list.get(i).getLongitude();
                    Double lat1 = 0.0;
                    Double lng2 = 0.0;

                    if (lat.isEmpty() || lng.isEmpty()) {
                        lat = "";
                        lng = "";
                    } else {
                        lat = all_data_list.get(i).getLatitude();
                        lng = all_data_list.get(i).getLongitude();

                        lat1 = (Double) Double.parseDouble(lat);
                        lng2 = (Double) Double.parseDouble(lng);
                    }

                    if (lat1 != 0.0 || lng2 != 0.0) {

                        LatLng location = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                        //  googleMap.setMyLocationEnabled(true);

                        if(all_data_list.get(i).getCategory().equals("Bike Hires")) {
                            fragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(location, 9));
                            marker = fragment.getMap().addMarker(new MarkerOptions().title(all_data_list.get(i).getName()).position(location));
                        }
                    }
                }
            }
        });

        toggle_shops.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                fragment.getMap().clear();

                String lat = null;
                String lng = null;

                for (int i = 0; i < all_data_list.size(); i++) {
                    lat = all_data_list.get(i).getLatitude();
                    lng = all_data_list.get(i).getLongitude();
                    Double lat1 = 0.0;
                    Double lng2 = 0.0;

                    if (lat.isEmpty() || lng.isEmpty()) {
                        lat = "";
                        lng = "";
                    } else {
                        lat = all_data_list.get(i).getLatitude();
                        lng = all_data_list.get(i).getLongitude();

                        lat1 = (Double) Double.parseDouble(lat);
                        lng2 = (Double) Double.parseDouble(lng);
                    }

                    if (lat1 != 0.0 || lng2 != 0.0) {

                        LatLng location = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                        //  googleMap.setMyLocationEnabled(true);

                        if(all_data_list.get(i).getCategory().equals("Shops")) {
                            fragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(location, 9));
                            marker = fragment.getMap().addMarker(new MarkerOptions().title(all_data_list.get(i).getName()).position(location));
                        }
                    }
                }
            }
        });

        toggle_attractions.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                fragment.getMap().clear();

                String lat = null;
                String lng = null;

                for (int i = 0; i < all_data_list.size(); i++) {
                    lat = all_data_list.get(i).getLatitude();
                    lng = all_data_list.get(i).getLongitude();
                    Double lat1 = 0.0;
                    Double lng2 = 0.0;

                    if (lat.isEmpty() || lng.isEmpty()) {
                        lat = "";
                        lng = "";
                    } else {
                        lat = all_data_list.get(i).getLatitude();
                        lng = all_data_list.get(i).getLongitude();

                        lat1 = (Double) Double.parseDouble(lat);
                        lng2 = (Double) Double.parseDouble(lng);
                    }

                    if (lat1 != 0.0 || lng2 != 0.0) {

                        LatLng location = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                        //  googleMap.setMyLocationEnabled(true);

                        if(all_data_list.get(i).getCategory().equals("Attractions")) {
                            fragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(location, 9));
                            marker = fragment.getMap().addMarker(new MarkerOptions().title(all_data_list.get(i).getName()).position(location));
                        }
                    }
                }
            }
        });

        toggle_b_and_b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                fragment.getMap().clear();

                String lat = null;
                String lng = null;

                for (int i = 0; i < all_data_list.size(); i++) {
                    lat = all_data_list.get(i).getLatitude();
                    lng = all_data_list.get(i).getLongitude();
                    Double lat1 = 0.0;
                    Double lng2 = 0.0;

                    if (lat.isEmpty() || lng.isEmpty()) {
                        lat = "";
                        lng = "";
                    } else {
                        lat = all_data_list.get(i).getLatitude();
                        lng = all_data_list.get(i).getLongitude();

                        lat1 = (Double) Double.parseDouble(lat);
                        lng2 = (Double) Double.parseDouble(lng);
                    }

                    if (lat1 != 0.0 || lng2 != 0.0) {

                        LatLng location = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                        //  googleMap.setMyLocationEnabled(true);

                        if(all_data_list.get(i).getCategory().equals("Bed & Breakfast")) {
                            fragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(location, 9));
                            marker = fragment.getMap().addMarker(new MarkerOptions().title(all_data_list.get(i).getName()).position(location));
                        }
                    }
                }
            }
        });


        toggle_res_pubs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                fragment.getMap().clear();

                String lat = null;
                String lng = null;

                for (int i = 0; i < all_data_list.size(); i++) {
                    lat = all_data_list.get(i).getLatitude();
                    lng = all_data_list.get(i).getLongitude();
                    Double lat1 = 0.0;
                    Double lng2 = 0.0;

                    if (lat.isEmpty() || lng.isEmpty()) {
                        lat = "";
                        lng = "";
                    } else {
                        lat = all_data_list.get(i).getLatitude();
                        lng = all_data_list.get(i).getLongitude();

                        lat1 = (Double) Double.parseDouble(lat);
                        lng2 = (Double) Double.parseDouble(lng);
                    }

                    if (lat1 != 0.0 || lng2 != 0.0) {

                        LatLng location = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                        //  googleMap.setMyLocationEnabled(true);

                        if(all_data_list.get(i).getCategory().equals("Restaurants/Pubs")) {
                            fragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(location, 9));
                            marker = fragment.getMap().addMarker(new MarkerOptions().title(all_data_list.get(i).getName()).position(location));
                        }
                    }
                }
            }
        });


        toggle_leisure.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                fragment.getMap().clear();

                String lat = null;
                String lng = null;

                for (int i = 0; i < all_data_list.size(); i++) {
                    lat = all_data_list.get(i).getLatitude();
                    lng = all_data_list.get(i).getLongitude();
                    Double lat1 = 0.0;
                    Double lng2 = 0.0;

                    if (lat.isEmpty() || lng.isEmpty()) {
                        lat = "";
                        lng = "";
                    } else {
                        lat = all_data_list.get(i).getLatitude();
                        lng = all_data_list.get(i).getLongitude();

                        lat1 = (Double) Double.parseDouble(lat);
                        lng2 = (Double) Double.parseDouble(lng);
                    }

                    if (lat1 != 0.0 || lng2 != 0.0) {

                        LatLng location = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                        //  googleMap.setMyLocationEnabled(true);

                        if(all_data_list.get(i).getCategory().equals("Leisure")) {
                            fragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(location, 9));
                            marker = fragment.getMap().addMarker(new MarkerOptions().title(all_data_list.get(i).getName()).position(location));
                        }
                    }
                }
            }
        });



        toggle_hostels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.getMap().clear();

                String lat = null;
                String lng = null;

                for (int i = 0; i < all_data_list.size(); i++) {
                    lat = all_data_list.get(i).getLatitude();
                    lng = all_data_list.get(i).getLongitude();
                    Double lat1 = 0.0;
                    Double lng2 = 0.0;

                    if (lat.isEmpty() || lng.isEmpty()) {
                        lat = "";
                        lng = "";
                    } else {
                        lat = all_data_list.get(i).getLatitude();
                        lng = all_data_list.get(i).getLongitude();

                        lat1 = (Double) Double.parseDouble(lat);
                        lng2 = (Double) Double.parseDouble(lng);
                    }

                    if (lat1 != 0.0 || lng2 != 0.0) {

                        LatLng location = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                        //  googleMap.setMyLocationEnabled(true);

                        if(all_data_list.get(i).getCategory().equals("Hostels")) {
                            fragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(location, 9));
                            marker = fragment.getMap().addMarker(new MarkerOptions().title(all_data_list.get(i).getName()).position(location));
                        }
                    }
                }
            }
        });


        toggle_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.getMap().clear();

                String lat = null;
                String lng = null;

                for (int i = 0; i < all_data_list.size(); i++) {
                    lat = all_data_list.get(i).getLatitude();
                    lng = all_data_list.get(i).getLongitude();
                    Double lat1 = 0.0;
                    Double lng2 = 0.0;

                    if (lat.isEmpty() || lng.isEmpty()) {
                        lat = "";
                        lng = "";
                    } else {
                        lat = all_data_list.get(i).getLatitude();
                        lng = all_data_list.get(i).getLongitude();

                        lat1 = (Double) Double.parseDouble(lat);
                        lng2 = (Double) Double.parseDouble(lng);
                    }

                    if (lat1 != 0.0 || lng2 != 0.0) {

                        LatLng location = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                        //  googleMap.setMyLocationEnabled(true);

                        if(all_data_list.get(i).getCategory().equals("Events")) {
                            fragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(location, 9));
                            marker = fragment.getMap().addMarker(new MarkerOptions().title(all_data_list.get(i).getName()).position(location));
                        }
                    }
                }
            }
        });


        toggle_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment.getMap().clear();

                    String lat = null;
                    String lng = null;
                    Log.i("on map ready", "on map ready is open");

                    for (int i = 0; i < all_data_list.size(); i++) {
                        lat = all_data_list.get(i).getLatitude();
                        lng = all_data_list.get(i).getLongitude();
                        Double lat1 = 0.0;
                        Double lng2 = 0.0;

                        if (lat.isEmpty() || lng.isEmpty()) {
                            lat = "";
                            lng = "";
                        } else {
                            lat = all_data_list.get(i).getLatitude();
                            lng = all_data_list.get(i).getLongitude();

                            lat1 = (Double) Double.parseDouble(lat);
                            lng2 = (Double) Double.parseDouble(lng);
                        }

                        if (lat1 != 0.0 || lng2 != 0.0) {

                            LatLng location = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                            //  googleMap.setMyLocationEnabled(true);

                            if(all_data_list.get(i).getCategory().equals("Hotel")) {
                                fragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13));
                                marker = fragment.getMap().addMarker(new MarkerOptions().title(all_data_list.get(i).getName()).position(location));
                            }
                        }
                    }
                }
        });


        loc_Listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                if(null == bestReading || location.getAccuracy() < bestReading.getAccuracy())
                {

                    bestReading = location;
                    refreshLocation(bestReading);

                    if(bestReading.getAccuracy() < minAccuracy)
                    {
                        loc_Manager.removeUpdates(loc_Listener);
                    }
                    Log.i(TAG, "set location from onlocationchanged method");
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if(null == bestReading || bestReading.getAccuracy() > minLastReadAccuracy || bestReading.getTime() < System.currentTimeMillis() - twoMinutes)
        {
            Log.i(TAG, "called from if statemment");

            if(null != loc_Manager.getProvider(LocationManager.NETWORK_PROVIDER)){
                loc_Manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, pollingFreq, minDistance, loc_Listener);
            }

            if(null != loc_Manager.getProvider(LocationManager.GPS_PROVIDER))
            {
                loc_Manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,pollingFreq,minDistance,loc_Listener);
            }

            if(null != loc_Manager.getProvider(LocationManager.GPS_PROVIDER))
            {
                loc_Manager.requestLocationUpdates(loc_Manager.GPS_PROVIDER, pollingFreq, minDistance, loc_Listener);
            }

            //Thread to unregister locaton listeners
            Executors.newScheduledThreadPool(1).schedule(new Runnable() {
                @Override
                public void run() {
                    loc_Manager.removeUpdates(loc_Listener);
                    Log.i(TAG, "All location updates have been cancelled!!");
                }
            }, measureTime,  TimeUnit.MILLISECONDS);
        }


    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        GoogleMap googleMap1 = null;


        if(bestReading == null)
        {
            Toast.makeText(getBaseContext(), "Cant determine your location", Toast.LENGTH_SHORT).show();
        }
    }

    public void isGPSEnabled()
    {
        if(loc_Manager.isProviderEnabled(LocationManager.GPS_PROVIDER) == false)
        {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Around_ME.this);

            // Setting Dialog Title
            alertDialog.setTitle("GPS not Enabled`");

            // Setting Dialog Message
            alertDialog.setMessage("GPS is not enabled. Please go to setting to Enable GPS");

            // On pressing Settings button
            alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });

            // on pressing cancel button
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            // Showing Alert Message
            alertDialog.show();

        }
    }

    //will be called when refresh location button is pressed.
    public void refreshLocation(Location location1)
    {

        if(null == bestReading || location1.getAccuracy() < bestReading.getAccuracy())
        {
            bestReading = location1;
            my_latitude = location1.getLatitude();
            my_longitude = location1.getLongitude();
            Log.i(TAG, "set location from refresh location method");
        }
    }



    @Override
    protected  void onResume() {
        super.onResume();

        Log.i(TAG, "called on resume method");
        if(null == bestReading || bestReading.getAccuracy() > minLastReadAccuracy || bestReading.getTime() < System.currentTimeMillis() - twoMinutes)
        {
            isGPSEnabled();

            if(null != loc_Manager.getProvider(LocationManager.NETWORK_PROVIDER)){
                loc_Manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, pollingFreq, minDistance, loc_Listener);
            }

            if(null != loc_Manager.getProvider(LocationManager.GPS_PROVIDER))
            {
                loc_Manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,pollingFreq,minDistance,loc_Listener);
            }

            if(null != loc_Manager.getProvider(LocationManager.GPS_PROVIDER))
            {
                loc_Manager.requestLocationUpdates(loc_Manager.GPS_PROVIDER, pollingFreq, minDistance, loc_Listener);
            }

            bestReading =    lastbestLocation( minLastReadAccuracy,  fiveMinutes);
            //    Log.i("best reading" , Double.toString(bestReading.getLongitude()));

            //Thread to unregister locaton listeners
            Executors.newScheduledThreadPool(1).schedule(new Runnable() {
                @Override
                public void run() {
                    loc_Manager.removeUpdates(loc_Listener);
                    Log.i(TAG, "All locationn updates have been cancelled!!");
                }
            }, measureTime, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        loc_Manager.removeUpdates(loc_Listener);

    }

    public Location lastbestLocation(float minAccuracy, long maxAge)
    {
        Location bestRes = null;
        float best_Acc = Float.MAX_VALUE;
        long best_Age = Long.MIN_VALUE;

        List<String> providers = loc_Manager.getAllProviders();

        for(int i = 0; i < providers.size(); i++)
        {
            Location loc = loc_Manager.getLastKnownLocation(providers.get(i));

            if(loc != null)
            {
                float acc = loc.getAccuracy();
                long time = loc.getTime();

                if(acc < best_Acc)
                {
                    bestRes = loc;
                    best_Acc = acc;
                    best_Age = time;
                }
            }
        }


        if(best_Acc > minAccuracy || (System.currentTimeMillis() - best_Age > maxAge))
        {
            Log.i(TAG, "Information returned is not more accurate than current information !!!");
            //    Log.i(TAG, Double.toString(bestRes.getLatitude()));
            //    Log.i(TAG, Double.toString(bestRes.getLongitude()));
            return null;
        }
        else {
            Log.i(TAG, "set location from bestlastlocation method!!!");
            my_latitude = bestRes.getLatitude();
            my_longitude = bestRes.getLongitude();
            return bestRes;
        }
    }




    private void addmarkers(GoogleMap map)
    {
        String lat = null;
        String lng = null;
        Log.i("on map ready", "on map ready is open");

        // bestReading = lastbestLocation(minLastReadAccuracy, fiveMinutes);
        //puts a marker on the map representing location
        for(int i = 0; i < all_data_list.size(); i++)
        {
            lat = all_data_list.get(i).getLatitude();
            lng =  all_data_list.get(i).getLongitude();
            Double lat1 = 0.0;
            Double lng2 = 0.0;

            if(lat.isEmpty()|| lng.isEmpty())
            {
                lat = "";
                lng = "";
            }
            else
            {
                lat = all_data_list.get(i).getLatitude();
                lng = all_data_list.get(i).getLongitude();

                lat1 = (Double) Double.parseDouble(lat);
                lng2 = (Double) Double.parseDouble(lng);
            }

            if(lat1 != 0.0 || lng2 != 0.0) {

                LatLng location = new LatLng(Double.parseDouble(lat),Double.parseDouble(lng));
                //  googleMap.setMyLocationEnabled(true);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13));
              marker =   map.addMarker(new MarkerOptions().title(all_data_list.get(i).getName()).position(location));

            }
        }

        if(bestReading == null)
        {
            Toast.makeText(getBaseContext(), "Cant determine your location", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_around__me, menu);
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


    //Classs to return everything in Details Table
    private class LoadData extends AsyncTask<Void,Void, List<String>> {

        AndroidHttpClient httpClient = AndroidHttpClient.newInstance("");
        String URL = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_All.php";

        @Override
        protected List<String> doInBackground(Void... arg0) {

            HttpGet request = new HttpGet(URL);

            GetJsonResponse responseHandler = new GetJsonResponse();
            try{
                return httpClient.execute(request, responseHandler);
            }catch (IOException e) {
                e.printStackTrace();
            }
            httpClient.close();
            return null;
        }

        @Override
        protected void onPostExecute(List<String> result)
        {
            com.google.android.gms.maps.MapFragment fragment = (com.google.android.gms.maps.MapFragment) getFragmentManager().findFragmentById(R.id.around_me_map);
            fragment.getMapAsync(Around_ME.this);
            fragment.getMap().setMyLocationEnabled(true);
        }
    }

    private class GetJsonResponse implements ResponseHandler<List<String>>
    {
        JSONArray Details;
        List<String> data = new ArrayList<String>();

        @Override
        public List<String> handleResponse(HttpResponse arg0)
                throws ClientProtocolException, IOException {
            // TODO Auto-generated method stub

            String Jsonresponse = new BasicResponseHandler().handleResponse(arg0);
            JSONObject jobj = null;
            try
            {
                jobj = (JSONObject) new JSONTokener(Jsonresponse).nextValue();

                Details = jobj.getJSONArray("Pubs");
                for( int i = 0; i < Details.length(); i++)
                {
                    //creates json object of retrieved information
                    JSONObject info = (JSONObject) Details.get(i);
                    //data.add(TAG_FNAME + ":"+ keepers.get(TAG_FNAME));/* + ","
                    data.add(info.get("Comp_Name").toString());
                    Log.i("Data Retrieved", info.toString());

                    String name = (String)info.get("Comp_Name").toString();
                    String category = (String)info.get("Category").toString();
                    String ratings_id = (String)info.get("Ratings_ID");
                    String latitude;
                    String longitude;
                    if(info.get("Latitude") == "null" || info.get("Longitude") == "null")
                    {
                        latitude = "";
                        longitude = "";
                    }
                    else
                    {
                        latitude = (String) info.get("Latitude");
                        longitude = (String) info.get("Longitude");
                    }

                    //  String Ratings_id = (String) info.get("Ratings_ID");
                    String Ratings_id = "";

                    try
                    {
                        all_data  = new Data(name,latitude,longitude,category,ratings_id);
                        all_data_list.add(all_data);
                        Log.i("Around Me ", "Added to array list");
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            catch(JSONException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }
    }
}
