package com.msunsoft.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msunsoft.model.Book;
import com.msunsoft.model.User;
import com.msunsoft.service.BookService;
import com.msunsoft.utils.Result;

/**
 * 登录模块，控制类
 *   
 * 
 */
@Controller
@RequestMapping("/user")
public class LoginController {
	// 注入
	// 注入Service

	@Autowired
	private BookService bookService;

	/**
	 * 登录
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
    @RequestMapping("/login")
    @ResponseBody
	    public Result login(HttpSession session,User user) {
	        Result result = new Result();
	        try {
	        	if((user.getUsername().equals("PIS"))&&(user.getPassword().equals("PIS"))){
	        		 result.setSuccess(true);
		        	 result.setStr(user.getUsername());
		        	 session.setAttribute("user", user.getUsername());
				     result.setMsg("登陆成功！");
	        	}else{
			        	Book book =bookService.login(user.getUsername());
			        	if(book==null){
				            result.setSuccess(false);
				            result.setMsg("用户名或密码不正确！");
			        	}else{
			        		if(book.getBook_code().equals(user.getPassword())!=true){
			        			result.setSuccess(false);
			    	            result.setMsg("用户名或密码不正确！");
			        		}
			        	 result.setSuccess(true);
			        	 result.setStr(user.getUsername());
			        	 session.setAttribute("user", user.getUsername());
					     result.setMsg("登陆成功！");
			        	}
	        	}
			        return result;
	        } catch (RuntimeException e) {
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    }
    /**
     * 退出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
  
        Result result = new Result();

        result.setSuccess(true);
        return result;
    }
}
