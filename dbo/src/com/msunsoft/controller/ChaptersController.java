package com.msunsoft.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msunsoft.model.Chapters;
import com.msunsoft.service.ChaptersService;
import com.msunsoft.utils.Tree;

/**
 * @description：章节管理
 */
@Controller
@RequestMapping("/chapters")
public class ChaptersController {
    private static Logger logger = LoggerFactory.getLogger(ChaptersController.class);

    @Autowired
    private ChaptersService chaptersService;

    /**
     * 菜单树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree(String book_id) {
    	int bookId = Integer.parseInt( book_id.replace(" ", ""));
        List<Tree> tree = chaptersService.findTree(bookId);
        for(Tree tree1:tree){
        	tree1.setState("closed");
        }
        return tree;
    }

    /**
     * 章节管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager(HttpServletRequest request,String book_id) {
    	request.setAttribute("book_id", book_id);
        return "/admin/Chapters";
    }

 

}
