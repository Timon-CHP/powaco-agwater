package com.powaco.agwater;


import com.powaco.agwater.model.GhiChiSoNuoc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class NhapChiSoNuoc extends Activity{
	
	TextView txtdanhso,txtkh,tvcscu;
	EditText txtcsmoi;
	Button btghi;
	
	String idkh,thang,nam,tenkh,sodb,dau,cuoi;
	Connection connect;
	final Context context = this;
	
	GhiChiSoNuoc gcsn = null;
	
	private void declarar()
	{		
		txtdanhso = (TextView)findViewById(R.id.txtDANHSO);
		txtkh = (TextView)findViewById(R.id.txtKH);
		tvcscu = (TextView)findViewById(R.id.tvCSCU);
		txtcsmoi = (EditText)findViewById(R.id.txtCSMOI);
		btghi = (Button)findViewById(R.id.btGHI);
		connect = Connect_sql.CONN(Connect_sql.username, Connect_sql.password, Connect_sql.database, Connect_sql.domain);
	}
	
	private void LoadIntent()
	{
		Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("TIEUTHU");
        idkh = packageFromCaller.getString("IDKH");
        thang = packageFromCaller.getString("THANG");
        nam = packageFromCaller.getString("NAM");
        tenkh = packageFromCaller.getString("TENKH");
        sodb = packageFromCaller.getString("SODB");
        dau = packageFromCaller.getString("DAU");
        cuoi = packageFromCaller.getString("CUOI");
        
        gcsn=(GhiChiSoNuoc) packageFromCaller.getSerializable("TIEUTHU");
	}
	
	private void LoadData()
	{
		LoadIntent();
        
        txtdanhso.setText(sodb);
        txtkh.setText(tenkh);
        tvcscu.setText(dau);
        
	}
	
	private void UpdateCS(String _idkh, int _thang, int _nam, int _csc)
	{
		ResultSet rs;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		//String SQL="SELECT MADP FROM DUONGPHO WHERE MAKV='O'";
		String SQL="UPDATE TIEUTHU SET CHISOCUOI="+ _csc +" FROM TIEUTHU T WHERE T.IDKH='"+ _idkh +
				"' AND T.THANG="+ _thang +" AND T.NAM="+ _nam +"";
		try
		{
			Statement statement = connect.createStatement();
			rs = statement.executeQuery(SQL);			
		}
		catch(Exception e)
		{
			Log.e("ERRO",e.getMessage());
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nhapchisonuoc);		
		declarar();
		
		LoadData();
		
		btghi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
								
				
				//Bundle b=new Bundle();
				//b.putSerializable("CSCUOI", cuoi);
				//i.putExtra("GHICHISONUOC", b);
				
				
				UpdateCS(idkh.toString(),Integer.parseInt(thang),Integer.parseInt(nam),Integer.parseInt(txtcsmoi.getText()+""));
				
				/*Intent i = getIntent();
				String cuoi =txtcsmoi.getText()+"";
				Bundle b=new Bundle();
				b.putString("CSCUOI", cuoi.toString());
				i.putExtra("GHICHISONUOC", b); */
				
				
				Intent i = getIntent();				
				String cuoi =txtcsmoi.getText()+"";								
				gcsn.setIDKH(idkh);
				gcsn.setTHANG(thang);
				gcsn.setNAM(nam);
				gcsn.setSODB(sodb);
				gcsn.setTENKH(tenkh);
				gcsn.setCSDAU(dau);
				gcsn.setCSCUOI(txtcsmoi.getText()+"");		
				Bundle b = new Bundle();				
				b.putSerializable("CSCUOI", gcsn);
				i.putExtra("GHICHISONUOC", b); 
				
				setResult(InfoChiSoNuoc.GHICHISONUOC_TC, i);
				
				finish();
			}
		});
		
	}
}
