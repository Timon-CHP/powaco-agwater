package com.powaco.agwater;

//import com.google.ads.AdRequest;
//import com.google.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ifgioithieu extends Activity {
	
	private void QuangCao()
	{
//		AdRequest adRequest = new AdRequest();
//        adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
//        adRequest.addTestDevice("a15269172bec589"); 
//        
//        AdView adView = (AdView)this.findViewById(R.id.adView);
//        adView.loadAd(new AdRequest());
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.if_gioithieu);  
        
        ImageView imageView = (ImageView) findViewById(R.id.imgGIOITHIEU);
        imageView.setImageResource(R.drawable.i11); 
        
        QuangCao();
        
    }

}
