package com.powaco.agwater.model;

import java.io.Serializable;


public class GhiChiSoNuoc extends Infor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String idkh;
	private String sodb;
	private String tenkh;
	private String thang;
	private String nam;
	private String csdau;
	private String cscuoi;
	
	public GhiChiSoNuoc(String idkh, String thang, String nam, String csdau,String cscuoi, String sodb,String tenkh) {
		this.idkh = idkh;
		this.thang = thang;
		this.nam = nam;
		this.csdau = csdau;
		this.cscuoi = cscuoi;
		this.sodb = sodb;
		this.tenkh = tenkh;
	}
	
	public GhiChiSoNuoc() {
		super();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
