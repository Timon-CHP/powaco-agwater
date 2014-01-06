package com.powaco.agwater;

import com.testflightapp.lib.TestFlight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class Menu extends Activity{

	

	Button btnkhachhang, btnnuoc, btngianuoc, btngiadien,btngioithieu;
	
	final Context context = this;
	
	

	
	private void declarar()
	{
		btnkhachhang = (Button) findViewById(R.id.btnKHACHHANG);
		btnnuoc = (Button) findViewById(R.id.btnNUOC);
		
		btngianuoc = (Button) findViewById(R.id.btnGIANNUOC);
		btngiadien = (Button) findViewById(R.id.btnGIANDIEN);
		btngioithieu = (Button) findViewById(R.id.btnGIOITHIEU);
		
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        TestFlight.takeOff(getApplication(), "711ec4ad-bf03-444b-8212-acb0d8db5c65");
	    setContentView(R.layout.menu);
        
        //connect string sql
        //inicializar();        
        declarar();
        
        btnkhachhang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Intent infokh = new Intent(getApplicationContext(), InfoKhachHang.class);
		    	startActivity(infokh);		
			}
		});
        
        btnnuoc.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Intent kyfonuoc = new Intent(getApplicationContext(), KyChiSoNuoc.class);
		    	startActivity(kyfonuoc);		
			}
        	/*@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Intent infonuoc = new Intent(getApplicationContext(), InfoChiSoNuoc.class);
		    	startActivity(infonuoc);		
			}*/
		});
        
        btngianuoc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Intent ifgianuoc = new Intent(getApplicationContext(), ifgianuoc.class);
		    	startActivity(ifgianuoc);		
			}
		});
        
        btngioithieu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Intent ifgioithieu = new Intent(getApplicationContext(), ifgioithieu.class);
		    	startActivity(ifgioithieu);		
			}
		});
        
        
        
    }
	
}
