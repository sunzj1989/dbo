package com.msunsoft.model;

public class ChapterSubItems {
	private int chapter_sub_item_id;
	private int chapter_id;
	private String chapter_sub_item_name;
	private char falg_chapter;
	private char flag_invalid;
	private int parent_id;
	public int getChapter_sub_item_id() {
		return chapter_sub_item_id;
	}
	public void setChapter_sub_item_id(int chapter_sub_item_id) {
		this.chapter_sub_item_id = chapter_sub_item_id;
	}
	public int getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(int chapter_id) {
		this.chapter_id = chapter_id;
	}
	public String getChapter_sub_item_name() {
		return chapter_sub_item_name;
	}
	public void setChapter_sub_item_name(String chapter_sub_item_name) {
		this.chapter_sub_item_name = chapter_sub_item_name;
	}
	public char getFlag_invalid() {
		return flag_invalid;
	}
	
	public char getFalg_chapter() {
		return falg_chapter;
	}
	public void setFalg_chapter(char falg_chapter) {
		this.falg_chapter = falg_chapter;
	}
	public void setFlag_invalid(char flag_invalid) {
		this.flag_invalid = flag_invalid;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	
}
