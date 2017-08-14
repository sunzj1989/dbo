package com.msunsoft.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msunsoft.model.Book;
import com.msunsoft.model.Content;
import com.msunsoft.service.BookService;
import com.msunsoft.service.ContentService;
import com.msunsoft.utils.ContentVo;
import com.msunsoft.utils.Result;
/**
 * 
 * @author 文本内容管理
 *
 */
@Controller
@RequestMapping("/content")
public class ContentCotroller {
    private static Logger logger = LoggerFactory.getLogger(ContentCotroller.class);
	@Autowired
	private ContentService contentService;
	@Autowired
	private BookService bookService;
	@RequestMapping("/show")
	@ResponseBody
	 public Result show(String item_id, String book_id) {
    	   Result result = new Result();
    	   ContentVo contentVo = new ContentVo();
    	   int itemId = Integer.parseInt(item_id);
    	   int bookId = Integer.parseInt( book_id.replace(" ", ""));
    	    try {
    	    Book book = bookService.findBookById(bookId);
	    	contentVo.setItem_id(itemId);
	    	contentVo.setT_name("content_"+book.getBook_code());
	    	Content content =  contentService.findContentById(contentVo);
	        result.setObj(content);
	        result.setStr("content_"+book.getBook_code());
	        result.setSuccess(true);
	        return result;
	    } catch (RuntimeException e) {
	        result.setMsg(e.getMessage());
	        return result;
	    }
    }
	@RequestMapping("/add")
	@ResponseBody
	 public Result add(int item_id,String detail_content,String t_name) {
    	   Result result = new Result();
    	   ContentVo contentVo = new ContentVo();
    	    	contentVo.setItem_id(item_id);
    	    	contentVo.setT_name(t_name);
    	    	List<Content> list = contentService.findContentAll(contentVo);
    	    	if(list.size()==0){
    	    		contentVo.setSql("1");
    	    	}else{
    	    		contentVo.setSql("(select max(content_id) from "+t_name+" ) + 1");
    	    	}
	    	try {
    	    	Content content=contentService.findContentById(contentVo);
    	    	contentVo.setDetail_content(detail_content);
    	    	if(content==null){
	    	    	contentService.addContent(contentVo);
    	    	}else{
    	    		contentService.updateContent(contentVo);
    	    	}
	    	    	Content contentResult=contentService.findContentById(contentVo);
	    	    	result.setObj(contentResult);
			        result.setSuccess(true);
			        result.setMsg("保存成功！");
			        return result;
			    } catch (RuntimeException e) {
			        result.setMsg(e.getMessage());
			        return result;
			    }
    }
	
}
