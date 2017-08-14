package com.msunsoft.utils;
import org.springframework.web.multipart.MultipartFile;

public class StudentForm {

	private String studentName;
	private int studentSex;
	private String studentBirthday;
	private MultipartFile studentPhoto;
	private String image_explain;
	
	
	public String getImage_explain() {
		return image_explain;
	}
	public void setImage_explain(String image_explain) {
		this.image_explain = image_explain;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(int studentSex) {
		this.studentSex = studentSex;
	}
	public String getStudentBirthday() {
		return studentBirthday;
	}
	public void setStudentBirthday(String studentBirthday) {
		this.studentBirthday = studentBirthday;
	}
	public MultipartFile getStudentPhoto() {
		return studentPhoto;
	}
	public void setStudentPhoto(MultipartFile studentPhoto) {
		this.studentPhoto = studentPhoto;
	}
	
	
}

