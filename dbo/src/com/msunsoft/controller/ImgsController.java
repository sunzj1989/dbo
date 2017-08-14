package com.msunsoft.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msunsoft.model.Book;
import com.msunsoft.model.Images;
import com.msunsoft.service.BookService;
import com.msunsoft.service.ImgsService;
import com.msunsoft.utils.PhotoEntity;
import com.msunsoft.utils.Result;
import com.msunsoft.utils.StudentForm;
/**
 * 
 * @author 图片管理类
 *
 */
@Controller
@RequestMapping("/imgs")
public class ImgsController {
	@Autowired
	private ImgsService imgsService;
	@Autowired
	private BookService bookService;
	/**
     * 图片上传
	 * @throws IOException 
     */
	@RequestMapping("/upLoad")  
	@ResponseBody
	public Result upload(ModelMap model, StudentForm form,String t_name,int subId) throws IOException {  
		Result result = new Result();
	    InputStream is = form.getStudentPhoto().getInputStream();  
	    byte[] studentPhotoData = new byte[(int) form.getStudentPhoto().getSize()];  
	    is.read(studentPhotoData);  
	    String fileName = form.getImage_explain();  
	    PhotoEntity photoEntity = new PhotoEntity(); 
	    photoEntity.setChapter_sub_item_id(subId);
	    photoEntity.setImage(studentPhotoData);  
	    photoEntity.setImage_explain(fileName); 
	    photoEntity.setT_name(t_name);
	    List<Images> list = imgsService.findImgsAll(photoEntity);
	    if(list.size()==0){
	    	photoEntity.setSql("1");
    	}else{
    		photoEntity.setSql("(select max(image_id) from "+t_name+" ) + 1");
    	}
	    this.imgsService.addImg(photoEntity); 
	    result.setMsg("上传成功！");
	    result.setSuccess(true);
	    return result;
	}  
	/**
     * 图片下载
	 * @throws IOException 
     */
	@RequestMapping("/downLoad")  
	@ResponseBody
    public void downLoad (HttpServletResponse response,int image_id,String t_name) throws IOException{  
		PhotoEntity photoEntity = new PhotoEntity();
		photoEntity.setImage_id(image_id);
		photoEntity.setT_name(t_name);
        Images images = this.imgsService.findImgsById(photoEntity);
	        byte[] data = images.getImage();  
	        response.setContentType("image/jpeg");  
	        response.setCharacterEncoding("UTF-8"); 
	        OutputStream outputSream = response.getOutputStream();  
	        InputStream in = new ByteArrayInputStream(data);  
	        int len = 0;  
	        byte[] buf = new byte[1024];  
	        while ((len = in.read(buf, 0, 1024)) != -1) {  
	            outputSream.write(buf, 0, len);  
	        }  
	        outputSream.close();  
	}

	/**
     * 图片查询
	 * @throws IOException 
     */
	@RequestMapping("/find")  
	@ResponseBody
	public Result find(HttpSession session,int subId,String book_id){
		Result result = new Result();
		int bookId = Integer.parseInt( book_id.replace(" ", ""));
		Book book = bookService.findBookById(bookId);
		PhotoEntity photoEntity = new PhotoEntity();
		photoEntity.setChapter_sub_item_id(subId);
		String str="image_"+book.getBook_code();
		photoEntity.setT_name(str);
		List<PhotoEntity> imgsList = imgsService.findImgsBySubId(photoEntity);
		for(PhotoEntity photoEntity1:imgsList){
			photoEntity1.setT_name(str);
		}
		session.setAttribute("imgsList", imgsList);
			result.setStr(str);
			result.setSuccess(true);
		return result;
	}
}
