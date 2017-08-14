package com.msunsoft.model;


public class Images {
	private int image_id;
	private int chapter_sub_item_id;
	private byte[] image;
	private String image_explain;
	private char flag_invalid;
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
}
