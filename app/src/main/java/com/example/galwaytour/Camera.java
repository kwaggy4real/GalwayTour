package com.example.galwaytour;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.facebook.LoginActivity;
import com.facebook.Session;
import com.facebook.android.Facebook;
import com.facebook.widget.LoginButton;
import android.support.v4.app.Fragment;


import com.example.galwaytour.*;


public class Camera extends Activity implements View.OnClickListener{

	   TextView view1;
	   Button ib;
	   ImageView iv;
	   Intent i;
	   int data;
	   Bitmap bitmap;
        Button share;

	   
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);       
     
        Log.i("CameraClass","Successfully opened Camera Class");
       view1 = (TextView) findViewById(R.id.textview3);       
        String value = getIntent().getStringExtra("selected");
        view1.setText(value);
        ib = (Button) findViewById(R.id.startCamera);
        ib.setOnClickListener(this);
        iv = (ImageView) findViewById(R.id.imageView1);
        share = (Button) findViewById(R.id.shareImage);
        share.setOnClickListener(this);


        
        }
	
      	public void onClick(View v) {
				// TODO Auto-generated method stub
      		
				if(v.getId() == R.id.startCamera)
				{
					i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);					
					startActivityForResult(i, data); 
				}
                if(v.getId() == R.id.shareImage)
                {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.setType("image/png");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bitmap);
                    startActivity(Intent.createChooser(shareIntent, "Share your thoughts"));
                }
			
			}

		@Override
		protected void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if(resultCode == RESULT_OK)
			{
				Bundle extras = data.getExtras();
				bitmap = (Bitmap) extras.get("data");
				iv.setImageBitmap(bitmap);
			}
		}
      	
	}

