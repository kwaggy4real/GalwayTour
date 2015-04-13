package com.example.galwaytour;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.LoginButton;
import android.R.layout.*;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.LoginButton;
import android.widget.Toast;


import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.galwaytour.R.drawable.ic_launcher;

public class MainActivity extends ActionBarActivity{

	int i = 0;
	Button hotels,Events;
    ListView menu_list;
	String menu[] = {"Hotels","Hostels","Restaurants/Pubs","Attractions","Events","Leisure","Shops","B & B","Camera","Car Hires","Bike Hires","Bus Timetables","Log Out"};
    private ShareActionProvider mShareActionProvider;
    View view1;
    private TextView userDetails;
    static ImageView userpicture;
    String userID;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);


       menu_list = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_text_design,menu);
        menu_list.setAdapter(adapter);


        menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
     {
         @Override
         public void onItemClick(AdapterView<?> a, View v,int position, long id)
         {
             switch (position)
             {
                 case 0:
                     Drawable img = getBaseContext().getResources().getDrawable( R.drawable.ic_launcher );
                     img.setBounds( 0, 0, 100, 100 );
                     TextView v1 = (TextView) v.findViewById(R.id.list_text_design1);
                     v1.setCompoundDrawables(img,null,null,null);


                     String URL = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_Hotels.php";
                     Class ourClass = null;
                     try {
                         ourClass = Class.forName("com.example.galwaytour.Display_Attractions");

                     } catch (ClassNotFoundException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                     Intent intent = new Intent(MainActivity.this,ourClass);
                     intent.putExtra("selected", menu[position]);
                     intent.putExtra("URL",URL);
                     startActivity(intent);
                     break;

                 case 1:
                     String URL1 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_Hostels.php";
                     //String URL1 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_Attractions.php";
                     Class ourClass1 = null;
                     try {
                         ourClass1 = Class.forName("com.example.galwaytour.Display_Attractions");
                     } catch (ClassNotFoundException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                     Intent intent1 = new Intent(MainActivity.this,ourClass1);
                     intent1.putExtra("selected", menu[position]);
                     intent1.putExtra("URL", URL1);
                     startActivity(intent1);
                     break;
                 case 2:
                     String URL2 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_Restaur_Pubs.php";
                     Class ourClass2 = null;
                     try {
                         ourClass2 = Class.forName("com.example.galwaytour.Display_Attractions");
                     } catch (ClassNotFoundException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                     Intent intent2 = new Intent(MainActivity.this,ourClass2);
                     intent2.putExtra("selected", menu[position]);
                     intent2.putExtra("URL", URL2);
                     startActivity(intent2);
                     break;
                 case 3:
                     //	String URL3 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_Hostels.php";
                     String URL3 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_Attractions.php";
                     Class ourClass3 = null;
                     try {
                         ourClass3 = Class.forName("com.example.galwaytour.Display_Attractions");
                     } catch (ClassNotFoundException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                     Intent intent3 = new Intent(MainActivity.this,ourClass3);
                     intent3.putExtra("selected", menu[position]);
                     intent3.putExtra("URL", URL3);
                     startActivity(intent3);
                     break;
                 case 4:
                     //	String URL3 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_Hostels.php";
                     String URL4 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_Events.php";
                     Class ourClass4 = null;
                     try {
                         ourClass4 = Class.forName("com.example.galwaytour.Display_Attractions");
                     } catch (ClassNotFoundException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                     Intent intent4 = new Intent(MainActivity.this,ourClass4);
                     intent4.putExtra("selected", menu[position]);
                     intent4.putExtra("URL", URL4);
                     startActivity(intent4);
                     break;
                 case 5:
                     String URL5 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_Leisure.php";
                     Class ourClass5 = null;
                     try {
                         ourClass5 = Class.forName("com.example.galwaytour.Display_Attractions");
                     } catch (ClassNotFoundException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                     Intent intent5 = new Intent(MainActivity.this,ourClass5);
                     intent5.putExtra("selected", menu[position]);
                     intent5.putExtra("URL", URL5);
                     startActivity(intent5);
                     break;
                 case 6:
                     String URL6 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_Shops.php";
                     Class ourClass6 = null;
                     try {
                         ourClass6 = Class.forName("com.example.galwaytour.Display_Attractions");
                     } catch (ClassNotFoundException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                     Intent intent6 = new Intent(MainActivity.this,ourClass6);
                     intent6.putExtra("selected", menu[position]);
                     intent6.putExtra("URL", URL6);
                     startActivity(intent6);
                     break;
                 case 7:
                     String URL7 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_B_and_B.php";
                     Class ourClass7 = null;
                     try {
                         ourClass7 = Class.forName("com.example.galwaytour.Display_Attractions");
                     } catch (ClassNotFoundException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                     Intent intent7 = new Intent(MainActivity.this,ourClass7);
                     intent7.putExtra("selected", menu[position]);
                     intent7.putExtra("URL", URL7);
                     startActivity(intent7);

                     break;
                 case 8:
                     Class ourClass9 = null;
                     try
                     {
                         ourClass9 = Class.forName("com.example.galwaytour.Camera");
                         Intent intent9 = new Intent(MainActivity.this,ourClass9);
                         intent9.putExtra("selected", menu[position]);
                         startActivity(intent9);
                     }
                     catch(ClassNotFoundException e)
                     {
                         e.printStackTrace();
                     }
                     break;

                 case 9:
                     String URL8 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_CarHires.php";
                     Class ourClass8 = null;
                     try {
                         ourClass8 = Class.forName("com.example.galwaytour.Display_Attractions");
                     } catch (ClassNotFoundException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                     Intent intent8 = new Intent(MainActivity.this,ourClass8);
                     intent8.putExtra("selected", menu[position]);
                     intent8.putExtra("URL", URL8);
                     startActivity(intent8);

                     break;
                 case 10:
                     String URL911 = "http://danu6.it.nuigalway.ie/dmadugu1/View_Uploads/List_BikeHires.php";
                     Class ourClass911 = null;
                     try {
                         ourClass911 = Class.forName("com.example.galwaytour.Display_Attractions");
                     } catch (ClassNotFoundException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                     Intent intent911 = new Intent(MainActivity.this,ourClass911);
                     intent911.putExtra("selected", menu[position]);
                     intent911.putExtra("URL", URL911);
                     startActivity(intent911);

                     break;
                 case 11:
                     Intent intent122 = new Intent(MainActivity.this, Bus_Information.class);
                     startActivity(intent122);
                     break;
                 case 12:
                    Session session = Session.getActiveSession();
                    Class ourClass12 = null;
                     if(session.isOpened())
                     {
                         session.close();
                         if(session.isClosed()) {
                             Toast.makeText(getBaseContext(), "User is Logged Out. Session has been closed", Toast.LENGTH_LONG).show();
                             try {
                                 ourClass12 = Class.forName("com.example.galwaytour.FB_Login");
                             } catch (ClassNotFoundException e) {
                                 // TODO Auto-generated catch block
                                 e.printStackTrace();
                             }
                             Intent intent12 = new Intent(MainActivity.this,ourClass12);
                             startActivity(intent12);
                         }
                     }
                     else if(session.isOpened())
                     {
                         Toast.makeText(getBaseContext(), "Session is still open, and user is Logged in", Toast.LENGTH_LONG).show();
                     }
                     break;
             }
             Toast.makeText(getBaseContext(), "Clicked:" + menu[position], Toast.LENGTH_LONG).show();
         }
     }
 );
      
    //  setListAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, menu));
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
                                Intent intent = new Intent(MainActivity.this, Around_ME.class);
                                startActivity(intent);

                            }

        return super.onOptionsItemSelected(item);
    }

    private String buildUserInfoDisplay(GraphUser user) {

        StringBuilder userInfo = new StringBuilder("");

        userInfo.append(String.format(user.getName()));
        Log.i("User Data", user.getName());
        userID = user.getId();
        Log.i("User ID", user.getId());

        return userInfo.toString();
    }

}


