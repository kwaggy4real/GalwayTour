package com.example.galwaytour;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Display_Attractions extends ListActivity{

    final String TAG_FNAME = "firstName";
    List<String> returned_data = new ArrayList<String>();
    List<retrievedData> retrieved_data = new ArrayList<retrievedData>();
    List<Retrieved_Event> retrieved_events_list = new ArrayList<Retrieved_Event>();
    private ShareActionProvider mShareActionProvider;

    ArrayAdapter<String> adapter;
    
    
    private static String URL = null;
    public static String action_tag = null;
    private final String LOG_TAG = "Display_Hotels Class";
    JSONArray products = null;
    TextView txt;

  //  TextView view1;
    TextView httpStuff;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     setContentView(com.example.galwaytour.R.layout.httpexample);


        URL = getIntent().getStringExtra("URL");
        action_tag = getIntent().getStringExtra("selected");

        android.app.ActionBar actionbar = getActionBar();
        actionbar.setTitle(action_tag);
        
		try {
			Log.i(LOG_TAG,"Inside try");
			 new LoadData().execute();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ListView lv = getListView();

//        if(lv.isShown())
//        {
//            Drawable img = getBaseContext().getResources().getDrawable( R.drawable.ic_event5 );
//            img.setBounds( 0, 0, 100, 100 );
//            TextView v1 = (TextView) lv.findViewById(R.id.list);
//            v1.setCompoundDrawables(img,null,null,null);
//        }
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
                // TODO Auto-generated method stub

                //Opens up the Event Actiivity, to view selected Event

                if (action_tag.equals("Events")) {




                    Log.i("action tag for events", retrieved_events_list.get(position).getLocation());
                    Toast.makeText(getBaseContext(), "Clicked Events", Toast.LENGTH_LONG).show();
                    Class ourClass;
                    try {
                        ourClass = Class.forName("com.example.galwaytour.DisplaySelectedEvents");

                        Intent intent1 = new Intent(Display_Attractions.this, ourClass);
                        intent1.putExtra("name", retrieved_events_list.get(position).getEvent_name());

                        intent1.putExtra("location", retrieved_events_list.get(position).getLocation());
                        intent1.putExtra("start date", retrieved_events_list.get(position).getStart_date());
                        intent1.putExtra("information", retrieved_events_list.get(position).getInformation());
                        intent1.putExtra("image id", retrieved_events_list.get(position).getImage_id());
                        startActivity(intent1);

                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else {
                    Class ourClass;
                    try {

                        ourClass = Class.forName("com.example.galwaytour.DisplaySelected");
                        Intent intent1 = new Intent(Display_Attractions.this, ourClass);
                        //intent1.putExtra("selected", returned_data.get(position).toString());
                        intent1.putExtra("name", retrieved_data.get(position).getName());
                        intent1.putExtra("address", retrieved_data.get(position).getAddress());
                        intent1.putExtra("website", retrieved_data.get(position).getWeb());
                        intent1.putExtra("information", retrieved_data.get(position).getInfo());
                        intent1.putExtra("tel_num", retrieved_data.get(position).getTel());
                        intent1.putExtra("email", retrieved_data.get(position).getEmail());
                        intent1.putExtra("image id", retrieved_data.get(position).getImageID());
                        intent1.putExtra("Latitude", retrieved_data.get(position).getLatitude());
                        intent1.putExtra("Longitude", retrieved_data.get(position).getLongitude());
                        intent1.putExtra("Category", retrieved_data.get(position).getCategory());
                        intent1.putExtra("Ratings_ID", retrieved_data.get(position).getRating());
                     //   intent1.putExtra("Rating", retrieved_data.get(position).getRatings());

                        startActivity(intent1);

                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
		});


	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.menu_item_share);
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
            Log.i("intent",Integer.toString(i));
            startActivity(Intent.createChooser(shareIntent, "Share your thoughts"));
            Toast.makeText(getBaseContext(), "Clicked Share", Toast.LENGTH_LONG).show();
            return true;
        }
        else if ( id == R.id.menu_aroundme)
        {
            //Open around me class
            //Open around me clas
            Intent intent = new Intent(Display_Attractions.this, Around_ME.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "Clicked Around Me", Toast.LENGTH_LONG).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private class LoadData extends AsyncTask<Void,Void, List<String>>{
 	   
    	   AndroidHttpClient httpClient = AndroidHttpClient.newInstance("");

    	   	@Override   	
    	   	protected List<String> doInBackground(Void... arg0) {
    		
    	   		HttpGet request = new HttpGet(URL);
    	   		Log.i("URL",URL);
    	   		GetJsonResponse responseHandler = new GetJsonResponse();
    	   		try{
    	   			return httpClient.execute(request, responseHandler);
    	   		}catch (IOException e) {
    				e.printStackTrace();
    			}
    	   		return null;   		
    	   	} 
    	   	    	   	
    	   	@Override   
    	   	protected void onPostExecute(List<String> result) 
    	   	{
                List<String> r = new ArrayList<String>();
                r.add("Non Available");
                if(result.isEmpty() == false) {

                    setListAdapter(new ArrayAdapter<String>(Display_Attractions.this, com.example.galwaytour.R.layout.list_hotels, result));
                 //   adapter = new ArrayAdapter<String>(Display_Attractions.this, com.example.galwaytour.R.layout.list_hotels, result);
                }
                else
                {
                    setListAdapter(new ArrayAdapter<String>(Display_Attractions.this, com.example.galwaytour.R.layout.list_hotels, r));

                }


             /*   searchbox.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {


                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        Display_Attractions.this.adapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });*/


    	   		  		
    		} 	
    	}
    
    protected class GetJsonResponse implements ResponseHandler<List<String>>
    {
 	 JSONArray Details;
 	List<String> data = new ArrayList<String>();
 	@Override
 	public List<String> handleResponse(HttpResponse arg0)
 			throws ClientProtocolException, IOException {
 		// TODO Auto-generated method stub
 		
 		  
 		String Jsonresponse = new BasicResponseHandler().handleResponse(arg0);
 		 JSONObject jobj = null;
 		
 		if(action_tag.equals("Hotels"))
 		{ 	Log.i("Array",action_tag);
 			try
 	 		{
 			Log.i("Array",Jsonresponse);			
 			jobj = (JSONObject) new JSONTokener(Jsonresponse).nextValue();						
 		
 			
 			Details = jobj.getJSONArray("Hotels");
	 			for( int i = 0; i < Details.length(); i++) 	{
	 				//creates object of retrieved data
	 				
	 				//creates json object of retrieved information
	 				JSONObject info = (JSONObject) Details.get(i);
	 				//data.add(TAG_FNAME + ":"+ keepers.get(TAG_FNAME));/* + ","
	 				data.add(info.get("Comp_Name").toString());
	 				
	 				
	 				//data which adds to the list
	 				returned_data.add(TAG_FNAME + ":"+ info.getString("Comp_Name") + "\n"
	 						+ "Address" + ":" + info.getString("Address") + ","  + "\n"
	 						+ "Website" + ":"+ info.getString("Website") + "\n"
	 						+ "Information" + ":" + info.getString("Information") + "\n"
	 						+ "Telephone Number" + ":" + info.getString("Telephone_Number") + "\n"
	 						+ "Email" + ":" + info.getString("Email"));
	 				
	 				String name = (String)info.get("Comp_Name").toString();
	 				String addr = (String)info.get("Address").toString();
	 				String web = (String)info.get("Website").toString();
	 				String info1 = (String)info.get("Information").toString();
	 				String tel = (String)info.get("Telephone_Number").toString();
	 				String email = (String)info.get("Email").toString();
                    String image_id = (String)info.get("Image_ID").toString();
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
                    String Ratings_1 = "";
                    String Ratings_2 = "";
                    String Ratings_3 = "";
                    String Ratings_4 = "";
                    String Ratings_5 = "";
                    String rating = "";
                    String Ratings_id = (String)info.get("Ratings_ID").toString();


	 				try
	 				{

                       // rating = rData.Calculate_Rating(Ratings_1,Ratings_2,Ratings_3,Ratings_4,Ratings_5);
                        retrievedData rData = new retrievedData(name,addr,web,info1,tel,email,Ratings_id,image_id,latitude,longitude,"",rating);
	 				retrieved_data.add(rData);
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
 		}
 		else if(action_tag.equals("Hostels"))
 		{ 	
			try
	 		{
			Log.i("Array",Jsonresponse);			
			jobj = (JSONObject) new JSONTokener(Jsonresponse).nextValue();						
			
			Details = jobj.getJSONArray("Hostels");
			for( int i = 0; i < Details.length(); i++)			
				{				
					
					JSONObject keepers = (JSONObject) Details.get(i);
					//data.add(TAG_FNAME + ":"+ keepers.get(TAG_FNAME));/* + ","
					data.add(keepers.get("Comp_Name").toString());
					
					
					//data which adds to the list
	 				returned_data.add(TAG_FNAME + ":"+ keepers.get("Comp_Name") + "\n"
	 						+ "Address" + ":" + keepers.getString("Address") + ","  + "\n"
	 						+ "Website" + ":"+ keepers.get("Website") + "\n"
	 						+ "Information" + ":" + keepers.get("Information") + "\n"
	 						+ "Telephone Number" + ":" + keepers.get("Telephone_Number") + "\n"
	 						+ "Email" + ":" + keepers.get("Email"));
					
	 				String name = (String)keepers.get("Comp_Name").toString();
	 				String addr = (String)keepers.get("Address").toString();
	 				String web = (String)keepers.get("Website").toString();
	 				String info = (String)keepers.get("Information").toString();
	 				String tel = (String)keepers.get("Telephone_Number").toString();
	 				String email = (String)keepers.get("Email").toString();
                    String image_id = (String)keepers.get("Image_ID");
                    String latitude;
                    String longitude;
                    if(keepers.get("Latitude") == null || keepers.get("Longitude") == null)
                    {
                         latitude = "";
                         longitude = "";
                    }
                    else
                    {
                         latitude = (String) keepers.get("Latitude");
                         longitude = (String) keepers.get("Longitude");
                    }
                    String Ratings_1 = "";
                    String Ratings_2 = "";
                    String Ratings_3 = "";
                    String Ratings_4 = "";
                    String Ratings_5 = "";
                    String rating = "";
                    String Ratings_id = (String)keepers.get("Ratings_ID").toString();

	 				try
	 				{

                        retrievedData rData = new retrievedData(name,addr,web,info,tel,email,Ratings_id,image_id,latitude,longitude,"",rating);

                     retrieved_data.add(rData);
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
		}
 		else if(action_tag.equals("Attractions"))
 		{ 	
			try
	 		{
			Log.i("Array",Jsonresponse);			
			jobj = (JSONObject) new JSONTokener(Jsonresponse).nextValue();						
			
			Details = jobj.getJSONArray("Attractions");
				for( int i = 0; i < Details.length(); i++)			
				{				
					
					JSONObject keepers = (JSONObject) Details.get(i);
					//data.add(TAG_FNAME + ":"+ keepers.get(TAG_FNAME));/* + ","
					data.add(keepers.get("Comp_Name").toString());
					
					
					//data which adds to the list
	 				returned_data.add(TAG_FNAME + ":"+ keepers.get("Comp_Name") + "\n"
	 						+ "Address" + ":" + keepers.getString("Address") + ","  + "\n"
	 						+ "Website" + ":"+ keepers.get("Website") + "\n"
	 						+ "Information" + ":" + keepers.get("Information") + "\n"
	 						+ "Telephone Number" + ":" + keepers.get("Telephone_Number") + "\n"
	 						+ "Email" + ":" + keepers.get("Email"));
					
	 				String name = (String)keepers.get("Comp_Name").toString();
	 				String addr = (String)keepers.get("Address").toString();
	 				String web = (String)keepers.get("Website").toString();
	 				String info = (String)keepers.get("Information").toString();
	 				String tel = (String)keepers.get("Telephone_Number").toString();
	 				String email = (String)keepers.get("Email").toString();
                    String image_id = (String)keepers.get("Image_ID");
                    String latitude;
                    String longitude;
                    if(keepers.get("Latitude") == null || keepers.get("Longitude") == null)
                    {
                         latitude = "";
                         longitude = "";
                    }
                    else
                    {
                         latitude = (String) keepers.get("Latitude");
                         longitude = (String) keepers.get("Longitude");
                    }
                    String Ratings_1 = "";
                    String Ratings_2 = "";
                    String Ratings_3 = "";
                    String Ratings_4 = "";
                    String Ratings_5 = "";
                    String rating = "";
                    String Ratings_id = (String)keepers.get("Ratings_ID").toString();

                    try
                    {


                        retrievedData  rData = new retrievedData(name,addr,web,info,tel,email,Ratings_id,image_id,latitude,longitude,"Attractions",rating);
                        retrieved_data.add(rData);
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
		}
 		else if(action_tag.equals("Restaurants/Pubs"))
 		{ 	
			try
	 		{
			Log.i("Array",Jsonresponse);			
			jobj = (JSONObject) new JSONTokener(Jsonresponse).nextValue();						
			
			Details = jobj.getJSONArray("Pubs");
			for( int i = 0; i < Details.length(); i++){
					
					JSONObject keepers = (JSONObject) Details.get(i);
					data.add(keepers.get("Comp_Name").toString());
					
					//data which adds to the list
	 				returned_data.add(TAG_FNAME + ":"+ keepers.get("Comp_Name") + "\n"
	 						+ "Address" + ":" + keepers.getString("Address") + ","  + "\n"
	 						+ "Website" + ":"+ keepers.get("Website") + "\n"
	 						+ "Information" + ":" + keepers.get("Information") + "\n"
	 						+ "Telephone Number" + ":" + keepers.get("Telephone_Number") + "\n"
	 						+ "Email" + ":" + keepers.get("Email"));
	 				
	 				String name = (String)keepers.get("Comp_Name").toString();
	 				String addr = (String)keepers.get("Address").toString();
	 				String web = (String)keepers.get("Website").toString();
	 				String info = (String)keepers.get("Information").toString();
	 				String tel = (String)keepers.get("Telephone_Number").toString();
	 				String email = (String)keepers.get("Email").toString();
                    String image_id = (String)keepers.get("Image_ID");
                    String latitude;
                    String longitude;
                    if(keepers.get("Latitude") == null || keepers.get("Longitude") == null)
                    {
                         latitude = "";
                         longitude = "";
                    }
                    else
                    {
                         latitude = (String) keepers.get("Latitude");
                         longitude = (String) keepers.get("Longitude");
                    }
                String Ratings_1 = "";
                String Ratings_2 = "";
                String Ratings_3 = "";
                String Ratings_4 = "";
                String Ratings_5 = "";
                String rating = "";
                String Ratings_id = (String)keepers.get("Ratings_ID").toString();

                    try
                    {


                        retrievedData  rData = new retrievedData(name,addr,web,info,tel,email,Ratings_id,image_id,latitude,longitude,"",rating);
                        retrieved_data.add(rData);
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
		}
 		else if(action_tag.equals("Events"))
 		{ 	
			try
	 		{
			Log.i("Array",Jsonresponse);			
			jobj = (JSONObject) new JSONTokener(Jsonresponse).nextValue();						
			
			Details = jobj.getJSONArray("Events");
				for( int i = 0; i < Details.length(); i++)			
				{								
					JSONObject keepers = (JSONObject) Details.get(i);
					//data.add(TAG_FNAME + ":"+ keepers.get(TAG_FNAME));/* + ","
					data.add(keepers.get("Comp_Name").toString());
					
					
					//data which adds to the list
					returned_data.add(TAG_FNAME + ":"+ keepers.get("Event_Name") + "\n"
							+ "Location" + ":" + keepers.getString("Location") + ","  + "\n"
	 						+ "Start Date" + ":"+ keepers.get("Start_Date") + "\n"
	 						+ "Information" + ":" + keepers.get("Information"));


                    String name = (String)keepers.get("Event_Name").toString();
                    String location = (String) keepers.get("Location").toString();
                    String start_date = (String) keepers.get("Start_Date").toString();
                    String information = (String) keepers.get("Information").toString();
                    String image_id = (String)keepers.get("Image_Id");

                    Log.i("Event Details:", name + " " + location + " " + start_date + " " + information + " ");


                    String latitude = "";
                    String longitude = "";
                  /*  if(keepers.get("Latitude") == null || keepers.get("Longitude") == null)
                    {
                        latitude = "";
                        longitude = "";
                    }
                    else
                    {
                        latitude = (String) keepers.get("Latitude");
                        longitude = (String) keepers.get("Longitude");
                    }*/

                    //   String Ratings_id = (String) keepers.get("Ratings_ID");
                    String Ratings_id = "";
                    String rating = "";

                    try
                    {
                        Retrieved_Event retrieved_event = new Retrieved_Event(name, start_date, "", information, location, image_id);
                        retrieved_events_list.add(retrieved_event);
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
		}
        else if(action_tag.equals("Leisure"))
        {
            try
            {
                Log.i("Array",Jsonresponse);
                jobj = (JSONObject) new JSONTokener(Jsonresponse).nextValue();

                Details = jobj.getJSONArray("Leisure");
                for( int i = 0; i < Details.length(); i++){

                    JSONObject keepers = (JSONObject) Details.get(i);
                    data.add(keepers.get("Comp_Name").toString());

                    //data which adds to the list
                    returned_data.add(TAG_FNAME + ":"+ keepers.get("Comp_Name") + "\n"
                            + "Address" + ":" + keepers.getString("Address") + ","  + "\n"
                            + "Website" + ":"+ keepers.get("Website") + "\n"
                            + "Information" + ":" + keepers.get("Information") + "\n"
                            + "Telephone Number" + ":" + keepers.get("Telephone_Number") + "\n"
                            + "Email" + ":" + keepers.get("Email"));

                    String name = (String)keepers.get("Comp_Name").toString();
                    String addr = (String)keepers.get("Address").toString();
                    String web = (String)keepers.get("Website").toString();
                    String info = (String)keepers.get("Information").toString();
                    String tel = (String)keepers.get("Telephone_Number").toString();
                    String email = (String)keepers.get("Email").toString();
                    String image_id = (String)keepers.get("Image_ID");
                    String latitude;
                    String longitude;
                    if(keepers.get("Latitude") == null || keepers.get("Longitude") == null)
                    {
                        latitude = "";
                        longitude = "";
                    }
                    else
                    {
                        latitude = (String) keepers.get("Latitude");
                        longitude = (String) keepers.get("Longitude");
                    }
                    String Ratings_1 = "";
                    String Ratings_2 = "";
                    String Ratings_3 = "";
                    String Ratings_4 = "";
                    String Ratings_5 = "";
                    String rating = "";
                    String Ratings_id = (String)keepers.get("Ratings_ID").toString();

                    try
                    {

                        retrievedData rData = new retrievedData(name,addr,web,info,tel,email,Ratings_id,image_id,latitude,longitude,"",rating);
                        retrieved_data.add(rData);
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
        }
        else if(action_tag.equals("Shops"))
        {
            try
            {
                Log.i("Array",Jsonresponse);
                jobj = (JSONObject) new JSONTokener(Jsonresponse).nextValue();

                Details = jobj.getJSONArray("Shops");
                for( int i = 0; i < Details.length(); i++){

                    JSONObject keepers = (JSONObject) Details.get(i);
                    data.add(keepers.get("Comp_Name").toString());

                    //data which adds to the list
                    returned_data.add(TAG_FNAME + ":"+ keepers.get("Comp_Name") + "\n"
                            + "Address" + ":" + keepers.getString("Address") + ","  + "\n"
                            + "Website" + ":"+ keepers.get("Website") + "\n"
                            + "Information" + ":" + keepers.get("Information") + "\n"
                            + "Telephone Number" + ":" + keepers.get("Telephone_Number") + "\n"
                            + "Email" + ":" + keepers.get("Email"));

                    String name = (String)keepers.get("Comp_Name").toString();
                    String addr = (String)keepers.get("Address").toString();
                    String web = (String)keepers.get("Website").toString();
                    String info = (String)keepers.get("Information").toString();
                    String tel = (String)keepers.get("Telephone_Number").toString();
                    String email = (String)keepers.get("Email").toString();
                    String image_id = (String)keepers.get("Image_ID");
                    String latitude;
                    String longitude;
                    if(keepers.get("Latitude") == null || keepers.get("Longitude") == null)
                    {
                        latitude = "";
                        longitude = "";
                    }
                    else
                    {
                        latitude = (String) keepers.get("Latitude");
                        longitude = (String) keepers.get("Longitude");
                    }
                    String Ratings_1 = "";
                    String Ratings_2 = "";
                    String Ratings_3 = "";
                    String Ratings_4 = "";
                    String Ratings_5 = "";
                    String rating = "";
                    String Ratings_id = (String)keepers.get("Ratings_ID").toString();

                    try
                    {

                        retrievedData rData = new retrievedData(name,addr,web,info,tel,email,Ratings_id,image_id,latitude,longitude,"", rating);
                        retrieved_data.add(rData);
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
        }
        else if(action_tag.equals("B & B"))
        {
            try
            {
                Log.i("Array",Jsonresponse);
                jobj = (JSONObject) new JSONTokener(Jsonresponse).nextValue();

                Details = jobj.getJSONArray("B_and_B");
                for( int i = 0; i < Details.length(); i++){

                    JSONObject keepers = (JSONObject) Details.get(i);
                    data.add(keepers.get("Comp_Name").toString());

                    //data which adds to the list
                    returned_data.add(TAG_FNAME + ":"+ keepers.get("Comp_Name") + "\n"
                            + "Address" + ":" + keepers.getString("Address") + ","  + "\n"
                            + "Website" + ":"+ keepers.get("Website") + "\n"
                            + "Information" + ":" + keepers.get("Information") + "\n"
                            + "Telephone Number" + ":" + keepers.get("Telephone_Number") + "\n"
                            + "Email" + ":" + keepers.get("Email"));

                    String name = (String)keepers.get("Comp_Name").toString();
                    String addr = (String)keepers.get("Address").toString();
                    String web = (String)keepers.get("Website").toString();
                    String info = (String)keepers.get("Information").toString();
                    String tel = (String)keepers.get("Telephone_Number").toString();
                    String email = (String)keepers.get("Email").toString();
                    String image_id = (String)keepers.get("Image_ID");
                    String latitude;
                    String longitude;
                    if(keepers.get("Latitude") == null || keepers.get("Longitude") == null)
                    {
                        latitude = "";
                        longitude = "";
                    }
                    else
                    {
                        latitude = (String) keepers.get("Latitude");
                        longitude = (String) keepers.get("Longitude");
                    }
                    String Ratings_1 = "";
                    String Ratings_2 = "";
                    String Ratings_3 = "";
                    String Ratings_4 = "";
                    String Ratings_5 = "";
                    String rating = "";
                    String Ratings_id = (String)keepers.get("Ratings_ID").toString();

                    try
                    {

                        retrievedData rData = new retrievedData(name,addr,web,info,tel,email,Ratings_id,image_id,latitude,longitude,"", rating);
                        retrieved_data.add(rData);
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
        }
        else if(action_tag.equals("Car Hires"))
        {
            try
            {
                Log.i("Array",Jsonresponse);
                jobj = (JSONObject) new JSONTokener(Jsonresponse).nextValue();

                Details = jobj.getJSONArray("Car_Hires");
                for( int i = 0; i < Details.length(); i++){

                    JSONObject keepers = (JSONObject) Details.get(i);
                    data.add(keepers.get("Comp_Name").toString());

                    //data which adds to the list
                    returned_data.add(TAG_FNAME + ":"+ keepers.get("Comp_Name") + "\n"
                            + "Address" + ":" + keepers.getString("Address") + ","  + "\n"
                            + "Website" + ":"+ keepers.get("Website") + "\n"
                            + "Information" + ":" + keepers.get("Information") + "\n"
                            + "Telephone Number" + ":" + keepers.get("Telephone_Number") + "\n"
                            + "Email" + ":" + keepers.get("Email"));

                    String name = (String)keepers.get("Comp_Name").toString();
                    String addr = (String)keepers.get("Address").toString();
                    String web = (String)keepers.get("Website").toString();
                    String info = (String)keepers.get("Information").toString();
                    String tel = (String)keepers.get("Telephone_Number").toString();
                    String email = (String)keepers.get("Email").toString();
                    String image_id = (String)keepers.get("Image_ID");
                    String latitude;
                    String longitude;
                    if(keepers.get("Latitude") == null || keepers.get("Longitude") == null)
                    {
                        latitude = "";
                        longitude = "";
                    }
                    else
                    {
                        latitude = (String) keepers.get("Latitude");
                        longitude = (String) keepers.get("Longitude");
                    }
                    String Ratings_1 = "";
                    String Ratings_2 = "";
                    String Ratings_3 = "";
                    String Ratings_4 = "";
                    String Ratings_5 = "";
                    String rating = "";
                    String Ratings_id = (String)keepers.get("Ratings_ID").toString();

                    try
                    {

                        retrievedData rData = new retrievedData(name,addr,web,info,tel,email,Ratings_id,image_id,latitude,longitude,"",rating);
                        retrieved_data.add(rData);
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
        }
        else if(action_tag.equals("Bike Hires"))
        {
            try
            {
                Log.i("Array",Jsonresponse);
                jobj = (JSONObject) new JSONTokener(Jsonresponse).nextValue();

                Details = jobj.getJSONArray("Bike_Hires");
                for( int i = 0; i < Details.length(); i++){

                    JSONObject keepers = (JSONObject) Details.get(i);
                    data.add(keepers.get("Comp_Name").toString());

                    //data which adds to the list
                    returned_data.add(TAG_FNAME + ":"+ keepers.get("Comp_Name") + "\n"
                            + "Address" + ":" + keepers.getString("Address") + ","  + "\n"
                            + "Website" + ":"+ keepers.get("Website") + "\n"
                            + "Information" + ":" + keepers.get("Information") + "\n"
                            + "Telephone Number" + ":" + keepers.get("Telephone_Number") + "\n"
                            + "Email" + ":" + keepers.get("Email"));

                    String name = (String)keepers.get("Comp_Name").toString();
                    String addr = (String)keepers.get("Address").toString();
                    String web = (String)keepers.get("Website").toString();
                    String info = (String)keepers.get("Information").toString();
                    String tel = (String)keepers.get("Telephone_Number").toString();
                    String email = (String)keepers.get("Email").toString();
                    String image_id = (String)keepers.get("Image_ID");
                    String latitude;
                    String longitude;
                    if(keepers.get("Latitude") == null || keepers.get("Longitude") == null)
                    {
                        latitude = "";
                        longitude = "";
                    }
                    else
                    {
                        latitude = (String) keepers.get("Latitude");
                        longitude = (String) keepers.get("Longitude");
                    }
                    String Ratings_1 = "";
                    String Ratings_2 = "";
                    String Ratings_3 = "";
                    String Ratings_4 = "";
                    String Ratings_5 ="";
                    String rating = "";
                    String Ratings_id = (String)keepers.get("Ratings_ID").toString();

                    try
                    {

                        retrievedData rData = new retrievedData(name,addr,web,info,tel,email,Ratings_id,image_id,latitude,longitude,"", rating);
                        retrieved_data.add(rData);
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
        }
        return data;
 	} 	   
    }
    
    public class retrievedData
    {
    	String name;
    	String address;
    	String website;
    	String information;
    	String tel_num;
    	String Email;
    	String rating_ID;
        String rating;
        String image_id;
        String latitude;
        String longitude;
        String category;
    	
    	public retrievedData(String name, String address, String website, String info, String tel_num, String email, String rating_ID,String image_id,String lat, String lng,String category,String rating)
    	{
    		this.name = name;
    		this.address = address;
    		this.website = website;
    		this.information = info;
    		this.tel_num = tel_num;
    		this.Email = email;
    		this.rating_ID = rating_ID;
            this.image_id = image_id;
            this.latitude = lat;
            this.longitude = lng;
            this.category = category;
            this.rating = rating;
    	}
    	public String getName()
    	{
    		return name;
    	}
    	public String getAddress()
    	{
    		return address;
    	}
    	public String getWeb()
    	{
    		return website;
    	}
    	public String getInfo()
    	{
    		return information;
    	}
    	public String getTel()
    	{
    		return tel_num;
    	}
    	public String getRatings()
        {
            return rating;
        }
    	public String getEmail()
    	{
    		return Email;
    	}
    	public String getRating()
    	{
    		return rating_ID;
    	}

        public String getImageID()
        {
            return image_id;
        }

        public String getLatitude()
        {
            return latitude;
        }

        public String getLongitude()
        {
            return longitude;
        }

        public String getCategory() {
            return category;
        }

        public String Calculate_Rating(String r1, String r2, String r3, String r4, String r5)
        {
            Double r_1 = Double.parseDouble(r1);
            Double r_2 = Double.parseDouble(r2);
            Double r_3 = Double.parseDouble(r3);
            Double r_4 = Double.parseDouble(r4);
            Double r_5 = Double.parseDouble(r5);

            Double result =  ((5 * r_5) + (4 * r_4) + (3 * r_3) + (2 * r_2) + (1 * r_1)) / (r_5 + r_4 + r_3 + r_2 + r_1);

            return Double.toString(result);
        }

    }
 }



