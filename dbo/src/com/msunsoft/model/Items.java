package com.msunsoft.model;

public class Items {
	private int items_id;
	private int chapter_id;
	private int chapter_sub_item_id;
	private String items_name;
	private char flag_invalid;
	public int getItems_id() {
		return items_id;
	}
	public void setItems_id(int items_id) {
		this.items_id = items_id;
	}
	public int getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(int chapter_id) {
		this.chapter_id = chapter_id;
	}
	public int getChapter_sub_item_id() {
		return chapter_sub_item_id;
	}
	public void setChapter_sub_item_id(int chapter_sub_item_id) {
		this.chapter_sub_item_id = chapter_sub_item_id;
	}
	public String getItems_name() {
		return items_name;
	}
	public void setItems_name(String items_name) {
		this.items_name = items_name;
	}
	public char getFlag_invalid() {
		return flag_invalid;
	}
	public void setFlag_invalid(char flag_invalid) {
		this.flag_invalid = flag_invalid;
	}
	
	
}
