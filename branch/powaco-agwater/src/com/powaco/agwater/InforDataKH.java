package com.powaco.agwater;

public class InforDataKH {
	private Object nam;
	private Object thang;
	private Object dau;
	private Object cuoi;
	private Object kl;
	private Object tien;
	
	public Object getNam() {
		return nam;
	}
	public void setNam(Object nam) {
		this.nam = nam;
	}
	
	public Object getThang() {
		return thang;
	}
	public void setThang(Object thang) {
		this.thang = thang;
	}
	
	public Object getDau() {
		return dau;
	}
	public void setDau(Object dau) {
		this.dau = dau;
	}
	
	public Object getCuoi() {
		return cuoi;
	}
	public void setCuoi(Object cuoi) {
		this.cuoi = cuoi;
	}
	
	public Object getKl() {
		return kl;
	}
	public void setKl(Object kl) {
		this.kl = kl;
	}
	
	public Object getTien() {
		return tien;
	}
	public void setTien(Object tien) {
		this.tien = tien;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nam +" - " +this.thang +" - "+this.dau+" - " +this.cuoi+" - " +this.kl+" - " +this.tien;
	}
}

