package com.example.galwaytour;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.example.galwaytour.R;

public class introScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_intro_screen);

        Thread timer = new Thread(){
            public void run(){

                try
                {
                   sleep(2500);
                }
                catch(InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                    //start FB_Login Activity
                    Intent intent = new Intent(introScreen.this, FB_Login.class);
                    startActivity(intent);
                }

            }
        };
        timer.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
