package com.powaco.agwater;

import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class KyChiSoNuoc extends Activity{
	
	EditText thang,nam;
	Button btghi;
	
	Calendar now = Calendar.getInstance();	
	
    int thang1,nam1;
    
	private void declarar()
	{		
		thang = (EditText)findViewById(R.id.txtTHANG);
		nam = (EditText)findViewById(R.id.txtNAM);
		btghi = (Button)findViewById(R.id.btGHI);		
		
		thang1 = now.get(Calendar.MONTH)+1;
        thang.setText(Integer.toString(thang1));
        nam1=now.get(Calendar.YEAR);
        nam.setText(Integer.toString(nam1));
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.kychisonuoc);
        declarar(); 
        
        
        
        btghi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent infonuoc = new Intent(getApplicationContext(), InfoChiSoNuoc.class);
		    	
				
				Bundle bundle=new Bundle();
				bundle.putString("thang", thang.getText()+"");
				bundle.putString("nam", nam.getText()+"");
				infonuoc.putExtra("KYGHI", bundle);	
				                   
		        //Toast.makeText(KyChiSoNuoc.this, thang.getText(), Toast.LENGTH_SHORT).show();
		        //Toast.makeText(KyChiSoNuoc.this, Integer.toString(nam1), Toast.LENGTH_SHORT).show();
		        
				//startActivity(intent);
				startActivityForResult(infonuoc,1);
				
			}
		});
        
        
	}
}
