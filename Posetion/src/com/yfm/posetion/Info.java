package com.yfm.posetion;

import java.util.ArrayList;
import java.util.List;


public class Info {

	//γ��
	private Double latitude;
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	//����
	private Double longitude;
	//��ַ
	private String addr;
	private List<CardInfo> lci=new ArrayList<CardInfo>();
	public List<CardInfo> getLci() {
		return lci;
	}
	public void setLci(List<CardInfo> lci) {
		this.lci = lci;
	}
}
