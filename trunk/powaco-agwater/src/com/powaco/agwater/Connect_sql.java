package com.powaco.agwater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

public class Connect_sql {
	static Connection connect;
	//static String username="1", password="2",database="3";
	//static String domain="4";
	static String username="1", password="2",database="3";
	static String domain="4";
	
	@SuppressLint("NewApi")
	static Connection CONN(String _user, String _pass, String _DB, String _server )
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
	}
}
