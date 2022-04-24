package com.team1.betterhip.dto;

import java.sql.Blob;

public class CakeListDto {
	int cake_id;
	String cake_name;
	int cake_saleprice;
	String cake_status;
	Blob cake_img;
	String base64Image;
	
	public CakeListDto() {
		super();
	}
	public CakeListDto(int cake_id, String cake_name, int cake_saleprice, String cake_status, Blob cake_img) {
		super();
		this.cake_id = cake_id;
		this.cake_name = cake_name;
		this.cake_saleprice = cake_saleprice;
		this.cake_status = cake_status;
		this.cake_img = cake_img;
	}
	public CakeListDto(int cake_id, String cake_name, int cake_saleprice, String cake_status, String base64Image) {
		super();
		this.cake_id = cake_id;
		this.cake_name = cake_name;
		this.cake_saleprice = cake_saleprice;
		this.cake_status = cake_status;
		this.base64Image = base64Image;
	}
	
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	public int getCake_id() {
		return cake_id;
	}
	public void setCake_id(int cake_id) {
		this.cake_id = cake_id;
	}
	public String getCake_name() {
		return cake_name;
	}
	public void setCake_name(String cake_name) {
		this.cake_name = cake_name;
	}
	public int getCake_saleprice() {
		return cake_saleprice;
	}
	public void setCake_saleprice(int cake_saleprice) {
		this.cake_saleprice = cake_saleprice;
	}
	public String getCake_status() {
		return cake_status;
	}
	public void setCake_status(String cake_status) {
		this.cake_status = cake_status;
	}
	public Blob getCake_img() {
		return cake_img;
	}
	public void setCake_img(Blob cake_img) {
		this.cake_img = cake_img;
	}
	
}
