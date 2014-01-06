package com.powaco.agwater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import android.app.AlertDialog; 
import android.content.Context; 
import android.content.DialogInterface; 


public class LoginActivity extends Activity {
	//khai bao
	Button btnlogin;
	EditText txtnguoidung, txtmatma;	
	Connection connect;
	SimpleAdapter AD;
	
	ListView Lista;
	final Context context = this;
	
	
	private void declarar()
	{
		btnlogin = (Button) findViewById(R.id.btnLogin);
		txtnguoidung = (EditText) findViewById(R.id.txtNGUOIDUNG); 
		txtmatma = (EditText) findViewById(R.id.txtMATMA);	
				
		Lista = (ListView) findViewById(R.id.list_output);
	}
	//bien, connect
	private void inicializar ()
	{
		declarar();
		//connect = CONN("sa", "P@sswOrD", "EOSAG", "115.78.230.159:1433");
		connect = Connect_sql.CONN(Connect_sql.username, Connect_sql.password, Connect_sql.database, Connect_sql.domain);
	}
	
	/*@SuppressLint("NewApi")
	private Connection CONN(String _user, String _pass, String _DB, String _server )
	{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		Connection conn = null;
		String ConnURL = null;
		try {
		
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			ConnURL = "jdbc:jtds:sqlserver://" + _server + ";" + "databaseName=" + _DB + ";user=" + _user + ";password=" + _pass + ";";
			conn = DriverManager.getConnection(ConnURL);		
		} catch (SQLException se) {
			Log.e("ERRO",se.getMessage());
		} catch (ClassNotFoundException e) {
			Log.e("ERRO",e.getMessage());
		} catch (Exception e) {
		    Log.e("ERRO",e.getMessage());
		}		
		return conn;
	}*/
	
	public void Login(String _nguoidung, String _matma)
	{
		ResultSet rs;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		String SQL="[dbo].[GetUser] '" + _nguoidung + "','" + _matma + "'";
		try
		{
			Statement statement = connect.createStatement();
			rs = statement.executeQuery(SQL);
			
			//List<Map<String, String>> data = null;
			//data = new ArrayList<Map<String,String>>();
			ArrayList data = new ArrayList();
			while(rs.next()){           
				//Map<String, String> datanum = new HashMap<String, String>();
				//datanum.put("A", rs.getString("ID"));
				String id=rs.getString("ID");
				data.add(id);
				}
			String[] from = {"A"};			
			
			if (data.get(0).equals("1")) 
			{							
				Intent ittrangchu = new Intent(getApplicationContext(), Menu.class);
		    	startActivity(ittrangchu);				
			}
			else
			{			
				alertDialogBuilder.setTitle("Thông báo!");
				alertDialogBuilder.setMessage("Nhập sai tên hoặc mật mã !");
				alertDialogBuilder.setCancelable(false);
				alertDialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener() 
								{ 
									public void onClick(DialogInterface dialog,int id) 
										{dialog.cancel();} 
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
		}
		catch(Exception e)
		{
			Log.e("ERRO",e.getMessage());
		}		
	}
	
	
	
	/*public void Login(String _nguoidung, String _matma)
	{
		ResultSet rs;
		String SQL="SELECT Username,Password FROM UserAdmin WHERE Username ='" + _nguoidung + "' AND Password ='" + _matma + "'";
		try
		{
			Statement statement = connect.createStatement();
			rs = statement.executeQuery(SQL);
			
			List<Map<String, String>> data = null;
			data = new ArrayList<Map<String,String>>();
			while(rs.next()){           
				Map<String, String> datanum = new HashMap<String, String>();
				datanum.put("A", rs.getString("Username"));
				datanum.put("B", rs.getString("Password"));
				data.add(datanum);
				}
			String[] from = {"A","B"};
			int[] views = {R.id.txt_titulo,R.id.txt_conteudo}; // vamos criar um modelo para as linhas do Adapter
			AD = new SimpleAdapter(this, data, R.layout.modelo, from, views);
			Lista.setAdapter(AD);			
			//setContentView(R.layout.trangchu);
		}
		catch(Exception e)
		{
			Log.e("ERRO",e.getMessage());
		}		
	}	*/
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //connect string sql
        inicializar();
        
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);        
        
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
			
		public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
				//setContentView(R.layout.infokhachhang);
			}
		});        
       
        btnlogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Login(txtnguoidung.getText().toString().trim(), txtmatma.getText().toString().trim());
				//Login();
				
			}
		});
        
    }
}