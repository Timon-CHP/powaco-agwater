package com.powaco.agwater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class InfoKhachHang extends Activity {

	//khai bao
		Button btnTIMKH;
		EditText txtTENKH;	
		ListView listkh,listtt;
		
		Connection connect;
		SimpleAdapter AD,ADT;		
		
		final Context context = this;	
		
		List<InforDataKH>list=new ArrayList<InforDataKH>();
		InforDataKH dataClick=null;
		
	private void declarar()
	{
		btnTIMKH = (Button) findViewById(R.id.btnTIMKH);
		txtTENKH = (EditText) findViewById(R.id.txtTENKH); 		
		
		listkh = (ListView) findViewById(R.id.listKH);
	}
	
	private void inicializar ()
	{
		declarar();
		//connect = CONN("sa", "P@sswOrD", "EOSAG", "115.78.230.159:1433");
		connect = Connect_sql.CONN(Connect_sql.username, Connect_sql.password, Connect_sql.database, Connect_sql.domain);
	}
	
	public void listTT(String _idkh)
	{
		ResultSet rs;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		String SQL="[dbo].[ListTieuThu] '" + _idkh  ;
		try
		{
			Statement statement = connect.createStatement();
			rs = statement.executeQuery(SQL);
			
			List<Map<String,String>> data = null;
			data = new ArrayList<Map<String,String>>();
			//ArrayList data = new ArrayList();
			while(rs.next()){           
				Map<String, String> datanum = new HashMap<String, String>();
				datanum.put("A", rs.getString("NAM"));
				datanum.put("B", rs.getString("THANG"));
				datanum.put("C", rs.getString("DAU"));
				datanum.put("D", rs.getString("CUOI"));
				datanum.put("E", rs.getString("KL"));
				datanum.put("F", rs.getString("TIEN"));
				data.add(datanum);
				}
			String[] from = {"A","B","C","D","E","F"};			
			
			int[] views = {R.id.colNAM,R.id.colTHANG,R.id.colDAU,R.id.colCUOI,R.id.colKL,R.id.colTIEN}; 
			ADT = new SimpleAdapter(this, data, R.layout.list_ttkh, from, views);			
						
			//list_outputKH.setAdapter(ADT);		
			
			Intent intent=new Intent(InfoKhachHang.this, list_outKH.class);
			//startActivity(intent);	
			Bundle bundle=new Bundle();
			
			//bundle
			
			//startActivity(intent);
			
			
			
			
		}
		catch(Exception e)
		{
			Log.e("ERRO",e.getMessage());
		}	
	}
	
	public void ListKhachHangs(String _tenkh)
	{
		ResultSet rs;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		String SQL="[dbo].[ListKhachHang] '" + _tenkh + "','O'" ;
		try
		{
			Statement statement = connect.createStatement();
			rs = statement.executeQuery(SQL);
			
			List<Map<String,String>> data = null;
			data = new ArrayList<Map<String,String>>();
			//ArrayList data = new ArrayList();
			while(rs.next()){           
				Map<String, String> datanum = new HashMap<String, String>();
				datanum.put("A", rs.getString("STT"));
				datanum.put("B", rs.getString("TENKH"));
				datanum.put("C", rs.getString("TENKV"));
				datanum.put("D", rs.getString("MADP"));
				datanum.put("E", rs.getString("IDKH"));
				data.add(datanum);
				}
			String[] from = {"A","B","C","D","E"};			
			
			int[] views = {R.id.colSTT,R.id.colTENKH,R.id.colTENKV,R.id.colMADP,R.id.colIDKH}; 
			AD = new SimpleAdapter(this, data, R.layout.list_infokh, from, views);			
			listkh.setAdapter(AD);
						
			listkh.setOnItemClickListener(new OnItemClickListener() {
				@Override				
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					//setContentView(R.layout.menu);					
					
					@SuppressWarnings("unchecked")
			        HashMap<String, String> o = (HashMap<String, String>) listkh.getItemAtPosition(arg2);                   
			        //Toast.makeText(InfoKhachHang.this, o.get("E"), Toast.LENGTH_SHORT).show();
			        
			        //inicializar ();
			        String idkh = o.get("E");
			        //listTT(idkh);
			        //list.add(idkh);
			        
			        Intent intent=new Intent(InfoKhachHang.this, list_outKH.class);						
					Bundle bundle=new Bundle();				
					
					bundle.putString("id", o.get("E").toString());					
					intent.putExtra("DATA", bundle);					
					
					//startActivity(intent);
					startActivityForResult(intent,1);
					
				}
			});
		}
		catch(Exception e)
		{
			Log.e("ERRO",e.getMessage());
		}		
	}
	/*
	private void QuangCao()
	{
		AdRequest adRequest = new AdRequest();
        adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
        adRequest.addTestDevice("a15269172bec589"); 
        
        AdView adView = (AdView)this.findViewById(R.id.adViewkh);
        adView.loadAd(new AdRequest());
	}
	*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infokhachhang);
		
		inicializar();
		
		btnTIMKH.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ListKhachHangs(txtTENKH.getText().toString().trim());

				//QuangCao();
				
			}
		});
		
	}
	
}
