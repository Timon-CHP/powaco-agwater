package com.powaco.agwater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class list_outKH extends Activity{
		
	ListView listtt;	
	Connection connect;
	SimpleAdapter ADT;
	final Context context = this;	
	
	private void declarar()
	{				
		listtt = (ListView) findViewById(R.id.list_outputKH);
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
		String SQL="[dbo].[ListTieuThu] '" + _idkh  + "'";
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
						
			listtt.setAdapter(ADT);		
			
			
		}
		catch(Exception e)
		{
			Log.e("ERRO",e.getMessage());
		}	
	}
	
	private void LayData()
	{
		Intent callerIntent = getIntent();
        Bundle packageFromCaller=
        		 callerIntent.getBundleExtra("DATA");
        String a = packageFromCaller.getString("id");
        listTT(a);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_ttkh1);
        inicializar();
        LayData();
        
    }

}
