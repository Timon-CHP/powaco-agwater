package com.powaco.agwater.model;

import java.io.Serializable;

public class Infor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String idkh,sodb,tenkh;
	private String thang,nam,csdau,cscuoi;
	
	
	public Infor() {
		super();
	}
	
	public String getIDKH() {
		return idkh;
	}
	public void setIDKH(String idkh) {
		this.idkh = idkh;
	}
	
	public String getSODB() {
		return sodb;
	}
	public void setSODB(String sodb) {
		this.sodb = sodb;
	}
	
	public String getTENKH() {
		return tenkh;
	}
	public void setTENKH(String tenkh) {
		this.tenkh = tenkh;
	}
	
	public String getTHANG() {
		return thang;
	}
	public void setTHANG(String thang) {
		this.thang = thang;
	}
	
	public String getNAM() {
		return nam;
	}
	public void setNAM(String nam) {
		this.nam = nam;
	}
	
	public String getCSDAU() {
		return csdau;
	}
	public void setCSDAU(String csd) {
		this.csdau = csd;
	}
	
	public String getCSCUOI() {
		return cscuoi;
	}
	public void setCSCUOI(String csc) {
		this.cscuoi = csc;
	}
	
	
}
