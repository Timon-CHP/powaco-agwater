package com.powaco.agwater;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ifgianuoc extends Activity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.if_gianuoc);  
        
        ImageView imageView = (ImageView) findViewById(R.id.imgGIANNUOC);
        imageView.setImageResource(R.drawable.gn1);
        
    }

}
