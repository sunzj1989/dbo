package com.msunsoft.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msunsoft.model.Book;
import com.msunsoft.model.ChapterSubItems;
import com.msunsoft.model.Items;
import com.msunsoft.service.BookService;
import com.msunsoft.service.ContentService;
import com.msunsoft.service.ItemsService;
import com.msunsoft.utils.ContentVo;
import com.msunsoft.utils.Result;
import com.msunsoft.utils.Tree;

/**
 * @description：标题管理
 */
@Controller
@RequestMapping("/items")
public class ItemsController {
	    private static Logger logger = LoggerFactory.getLogger(ItemsController.class);
	    @Autowired
	    private ItemsService itemsService;
	    @Autowired
	    private ContentService contentService;
	    @Autowired
		private BookService bookService;

	    /**
	     * 菜单树
	     *
	     * @return
	     */
	    @RequestMapping(value = "/tree", method = RequestMethod.POST)
	    @ResponseBody
	    public List<Tree> tree(HttpSession session,int chapterId) {
	    	
	        List<Tree> tree = itemsService.findTree(chapterId);
	        return tree;
	    }
	    /**
	     * 添加一级标题页
	     *
	     * @return
	     */
	    @RequestMapping("/addFirstPage")
	    public String addPage(HttpServletRequest request,int chapter_id,int subId) {
	    	ChapterSubItems chapterSubItems = itemsService.findSubById(subId);
	    	int parent_id=subId;
	    	request.setAttribute("chapterSubItems", chapterSubItems);
	    	request.setAttribute("parent_id", parent_id);
	    	request.setAttribute("chapter_id", chapter_id);
	        return "/admin/addFirstPage";
	    }
	    
	    /**
	     * 查询当前节点是否为病种
	     *
	     * @return
	     */
	    @RequestMapping("/findFirst")
	    @ResponseBody
	    public Result findFirst(int subId,String subName) {
	        Result result = new Result();
	        ChapterSubItems chapterSubItems = new ChapterSubItems();
	        chapterSubItems.setChapter_sub_item_id(subId);
	        chapterSubItems.setChapter_sub_item_name(subName);
	        try {
	        	if(itemsService.findSubBySub(chapterSubItems)!=null){
		            result.setSuccess(true);
	        	}
	        	return result;
	        } catch (RuntimeException e) {
	        	logger.info("删除失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    } 
	    /**
	     * 添加一级标题
	     *
	     * @param 
	     */
	    @RequestMapping("/addFirst")
	    @ResponseBody
	    public Result add(ChapterSubItems chapterSubItems) {
	        Result result = new Result();
	        try {
	        	if(chapterSubItems.getParent_id()==0){
	        	   itemsService.addSub(chapterSubItems);
	        	}
	        	itemsService.addsecondSub(chapterSubItems);
	            result.setSuccess(true);
	            result.setMsg("添加成功！");
	            return result;
	        } catch (RuntimeException e) {
	        	logger.info("添加失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    }
	    
	    /**
	     * 删除一级标题
	     *
	     * @param id
	     * @return
	     */
	    @RequestMapping("/deleteFirst")
	    @ResponseBody
	    public Result delete(int id,String book_id) {
	        Result result = new Result();
	        int bookId = Integer.parseInt( book_id.replace(" ", ""));
			Book book = bookService.findBookById(bookId);
			ContentVo contentVo = new ContentVo();
			String str="content_"+book.getBook_code();
	        try {
	        	List<Items> list = itemsService.findItemBySubId(id);
	        	for(Items items:list){
	        		contentVo.setItem_id(items.getItems_id());
	        		contentVo.setT_name(str);
	        		contentService.deleteContent(contentVo);
	        	}
	        	itemsService.deleteItemsBySubId(id);
	        	itemsService.deleteSub(id);
	            result.setMsg("删除成功！");
	            result.setSuccess(true);
	            return result;
	        } catch (RuntimeException e) {
	        	logger.info("删除部门失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    }
	    
	    /**
	     * 添加二级标题页
	     *
	     * @return
	     */
	    @RequestMapping("/addSecondPage")
	    public String addSecondPage(HttpServletRequest request,String chapter_id,String chapter_sub_item_id) {
	    	int subId=Integer.parseInt(chapter_sub_item_id);
	    	int chapterId=Integer.parseInt(chapter_id);
	    	ChapterSubItems chapterSubItems = itemsService.findSubById(subId);
	    	request.setAttribute("chapterSubItems", chapterSubItems);
	    	request.setAttribute("chapterId", chapterId);
	        return "/admin/addSecondPage";
	    }

	    /**
	     * 添加二级标题
	     *
	     * @param 
	     */
	    @RequestMapping("/addSecond")
	    @ResponseBody
	    public Result addSecond(Items items) {
	        Result result = new Result();
	        if(items.getChapter_sub_item_id()==0){
	        	int chapter_sub_item_id=items.getChapter_id();
	        	items.setChapter_sub_item_id(chapter_sub_item_id);
	        }
	        try {
	        	itemsService.addItems(items);
	            result.setSuccess(true);
	            result.setMsg("添加成功！");
	            return result;
	        } catch (RuntimeException e) {
	        	logger.info("添加部门失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    }
	    /**
	     * 删除二级标题
	     *
	     * @param id
	     * @return
	     */
	    @RequestMapping("/deleteSecond")
	    @ResponseBody
	    public Result deleteSecond(int id,String t_name) {
	        Result result = new Result();
	        ContentVo contentVo = new ContentVo();
	        contentVo.setItem_id(id);
	        contentVo.setT_name(t_name);
	        try {
	        	itemsService.deleteItems(id);
	        	contentService.deleteContent(contentVo);
	            result.setMsg("删除成功！");
	            result.setSuccess(true);
	            return result;
	        } catch (RuntimeException e) {
	        	logger.info("删除失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    } 
	    /**
	     * 查找标题
	     *
	     * @param id
	     * @return
	     */
	    @RequestMapping("/find")
	    @ResponseBody
	    public Result find(int items_id,String items_name) {
	        Result result = new Result();
	        Items items = new Items();
	        items.setItems_id(items_id);
	        items.setItems_name(items_name);
	        try {
	        	if(itemsService.findItemByItem(items)!=null){
		            result.setSuccess(true);
	        	}
	        	return result;
	        } catch (RuntimeException e) {
	        	logger.info("删除失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    } 
}
