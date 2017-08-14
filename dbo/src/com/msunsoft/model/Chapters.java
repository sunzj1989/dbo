package com.msunsoft.model;

public class Chapters {
	private int chapter_id;
	private int book_id;
	private String chapter_name;
	private char flag_invalid;
	private char flag_parent;
	private int parent_id;
	public int getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(int chapter_id) {
		this.chapter_id = chapter_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getChapter_name() {
		return chapter_name;
	}
	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}
	public char getFlag_invalid() {
		return flag_invalid;
	}
	public void setFlag_invalid(char flag_invalid) {
		this.flag_invalid = flag_invalid;
	}
	public char getFlag_parent() {
		return flag_parent;
	}
	public void setFlag_parent(char flag_parent) {
		this.flag_parent = flag_parent;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	
	
}
