package com.msunsoft.model;

public class Book {
	private int book_id;
	private int parent_id;
	private String book_code;
	private String book_name;
	private String book_version;
	private char flag_invalid;
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	
	
	
	public String getBook_code() {
		return book_code;
	}
	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_version() {
		return book_version;
	}
	public void setBook_version(String book_version) {
		this.book_version = book_version;
	}
	public char getFlag_invalid() {
		return flag_invalid;
	}
	public void setFlag_invalid(char flag_invalid) {
		this.flag_invalid = flag_invalid;
	}
	
}
