package com.powaco.agwater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powaco.agwater.model.GhiChiSoNuoc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


public class InfoChiSoNuoc extends Activity{
	
	
	Spinner spmadp;
	TextView tvmadp,tvdp;
	SimpleAdapter AD,ADT;
	
	ListView listmadp,listkhachhang;
		
	Connection connect;		
	final Context context = this;
	public static final int GHICHISONUOC_TC=9;
	
	ArrayAdapter<String> adapter;
	String[] madp = null;
	
	
	List<Map<String,String>> datanv = null;
	
	ArrayList<GhiChiSoNuoc> arrGhiChiSoNuoc=null;
	
	private void declarar()
	{
		spmadp = (Spinner) findViewById(R.id.spMADP);
		tvmadp = (TextView)findViewById(R.id.tvMADP);
		tvdp = (TextView)findViewById(R.id.tvDp);
		listkhachhang = (ListView)findViewById(R.id.lvDSKHACHHANG);
		
		registerForContextMenu(listkhachhang);
		
		spmadp.setOnItemSelectedListener(new OnItemSelectedListener ()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos,
					long id) {
				//Toast.makeText(parent.getContext(), "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),  Toast.LENGTH_SHORT).show();
				
				@SuppressWarnings("unchecked")
		        HashMap<String, String> o = (HashMap<String, String>) parent.getItemAtPosition(pos);
				Toast.makeText(parent.getContext(), o.get("A"),  Toast.LENGTH_SHORT).show();
				
				tvdp.setText(o.get("A"));
				
				Intent callerIntent = getIntent();
		        Bundle packageFromCaller = callerIntent.getBundleExtra("KYGHI");
		        String thang = packageFromCaller.getString("thang");
		        String nam = packageFromCaller.getString("nam");
				
				listKH(o.get("A"),Integer.parseInt(thang),Integer.parseInt(nam));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
			
		});
	}
	
	//bien, connect
	private void inicializar ()
	{
		declarar();
		//connect = CONN("sa", "P@sswOrD", "EOSAG", "115.78.230.159:1433");
		connect = Connect_sql.CONN(Connect_sql.username, Connect_sql.password, Connect_sql.database, Connect_sql.domain);
	}
	
	private void spDuongPho()
	{
		ResultSet rs;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		//String SQL="SELECT MADP FROM DUONGPHO WHERE MAKV='O'";
		String SQL="SELECT MADP,TENDP FROM DUONGPHO WHERE MAKV='O'";
		try
		{
			Statement statement = connect.createStatement();
			rs = statement.executeQuery(SQL);
			
			List<Map<String, String>> data = null;
			data = new ArrayList<Map<String,String>>();
						
			while(rs.next()){           
				Map<String, String> datanum = new HashMap<String, String>();
				datanum.put("A", rs.getString("MADP"));
				datanum.put("B", rs.getString("TENDP"));				
				data.add(datanum);
				}
			String[] from = {"A","B"};
			madp = from;
			int[] views = {R.id.colMADP,R.id.colTENDP};
			AD = new SimpleAdapter(this, data, R.layout.list_tenmadp, madp, views);
			
			//listmadp.setAdapter(AD);
			
			
			adapter=new ArrayAdapter<String>
			(
			 this, android.R.layout.simple_spinner_item, madp
			);
			adapter.setDropDownViewResource
				(android.R.layout.simple_list_item_single_choice);			
			//spmadp.setAdapter(adapter);			
			spmadp.setAdapter(AD);		
			
			
		}
		catch(Exception e)
		{
			Log.e("ERRO",e.getMessage());
		}
	}	
	
	public void listKH(String _madp, int _thang, int _nam)
	{
		ResultSet rs;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		String SQL="SELECT K.IDKH,(K.MADP + K.MADB) AS SODB,K.TENKH,KV.TENKV,T.CHISODAU,T.CHISOCUOI FROM KHACHHANG K " +
				"INNER JOIN KHUVUC KV ON K.MAKV=KV.MAKV INNER JOIN TIEUTHU T ON K.IDKH=T.IDKH " +
				"WHERE K.MADP='"+ _madp +"' AND T.MAKV='O' AND T.NAM="+ _nam +" AND T.THANG="+ _thang +"";
		
		final int t1 = _thang;
		final int n1 = _nam;
		
		try
		{
			Statement statement = connect.createStatement();
			rs = statement.executeQuery(SQL);
			
			//List<Map<String,String>> datanv = null;
			datanv = new ArrayList<Map<String,String>>();
			//ArrayList data = new ArrayList();
			while(rs.next()){           
				Map<String, String> datanum = new HashMap<String, String>();
				datanum.put("A", rs.getString("IDKH"));
				datanum.put("B", rs.getString("SODB"));
				datanum.put("C", rs.getString("TENKH"));
				datanum.put("D", rs.getString("TENKV"));
				datanum.put("E", rs.getString("CHISODAU"));
				datanum.put("F", rs.getString("CHISOCUOI"));
				datanv.add(datanum);
				}
			String[] from = {"A","B","C","D","E","F"};			
			
			int[] views = {R.id.colIDKH,R.id.colSODB,R.id.colTENKH,R.id.colTENKV,R.id.colCHISODAU,R.id.colCHISOCUOI}; 
			//ADT = new SimpleAdapter(this, datanv, R.layout.list_khachhang, from, views);			
			ADT = new SimpleAdapter(this, datanv, R.layout.list_khachhang, from, views);
						
			listkhachhang.setAdapter(ADT);		
			
			listkhachhang.setOnItemClickListener(new OnItemClickListener() {
				@Override				
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					//setContentView(R.layout.menu);					
					
					@SuppressWarnings("unchecked")
			        HashMap<String, String> o = (HashMap<String, String>) listkhachhang.getItemAtPosition(arg2);                   
			        Toast.makeText(InfoChiSoNuoc.this, o.get("A"), Toast.LENGTH_SHORT).show();
			        
			       			        
			        Intent i = new Intent(InfoChiSoNuoc.this, NhapChiSoNuoc.class);						
					Bundle b = new Bundle();								
					
					String t = String.valueOf(t1);
					String n = String.valueOf(n1);;
					
					b.putString("IDKH", o.get("A").toString());	
					b.putString("THANG", t.toString());
					b.putString("NAM", n.toString());
					b.putString("TENKH", o.get("C").toString());
					b.putString("SODB", o.get("B").toString());
					b.putString("DAU", o.get("E").toString());
					b.putString("CUOI", o.get("F").toString());
					i.putExtra("TIEUTHU", b);
					//startActivity(i);
					startActivityForResult(i,1);
										
				}
			});
			
			//Intent intent=new Intent(this, list_outKH.class);
			//startActivity(intent);	
			//Bundle bundle=new Bundle();			
			
			
		}
		catch(Exception e)
		{
			Log.e("ERRO",e.getMessage());
		}	
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//lấy kết quả sửa nhân viên thành công
		if(resultCode == InfoChiSoNuoc.GHICHISONUOC_TC)
		{
			
			//Bundle b = data.getBundleExtra("GHICHISONUOC");
			//String cscuoi = b.getString("CSCUOI");
			
			
			//Toast.makeText(InfoChiSoNuoc.this, cscuoi.toString(), Toast.LENGTH_SHORT).show();
			
			ADT.notifyDataSetChanged();
			
			
			
			
			
		}
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infonuoc);
		
		inicializar();
		
		spDuongPho();
		
	}
	
}
