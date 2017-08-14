package com.msunsoft.utils;


public class PhotoEntity {
	private int image_id;
	private int chapter_sub_item_id;
	private byte[] image;
	private String image_explain;
	private char flag_invalid;
	private String t_name;
	private String sql;
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public int getChapter_sub_item_id() {
		return chapter_sub_item_id;
	}
	public void setChapter_sub_item_id(int chapter_sub_item_id) {
		this.chapter_sub_item_id = chapter_sub_item_id;
	}
	
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getImage_explain() {
		return image_explain;
	}
	public void setImage_explain(String image_explain) {
		this.image_explain = image_explain;
	}
	public char getFlag_invalid() {
		return flag_invalid;
	}
	public void setFlag_invalid(char flag_invalid) {
		this.flag_invalid = flag_invalid;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	
	
	
}
