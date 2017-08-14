package com.msunsoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.msunsoft.mapper.ChapterSubItemsMapper;
import com.msunsoft.mapper.ItemsMapper;
import com.msunsoft.model.Book;
import com.msunsoft.model.ChapterSubItems;
import com.msunsoft.model.Items;
import com.msunsoft.service.ItemsService;
import com.msunsoft.utils.Tree;
@Service
public class ItemsServiceImpl implements ItemsService{
	@Autowired
	private ItemsMapper itemsMapper;
	@Autowired
	private ChapterSubItemsMapper chapterSubItemsMapper;
	@Override
/*	public List<Tree> findTree(int chapter_id) {
			List<Tree> tree1 = Lists.newArrayList();
			int chapter_sub_item_id=0;
			//查询树根
			ChapterSubItems subItemsFather = chapterSubItemsMapper.findSubEqualsChapter(chapter_id);
            Tree treeOne = new Tree();
            treeOne.setId(subItemsFather.getChapter_sub_item_id());
            treeOne.setText(subItemsFather.getChapter_sub_item_name());
            chapter_sub_item_id=subItemsFather.getChapter_sub_item_id();
            //定义二级树
            List<Tree> tree2 = Lists.newArrayList();
            //查询无小病种的文本块
            List<Items> subItemsSon1 = itemsMapper.findItemBychapterId(chapter_sub_item_id);
			for (Items items : subItemsSon1) {
		             Tree treeOne1 = new Tree();
		             treeOne1.setId(items.getItems_id());
		             treeOne1.setText(items.getItems_name());
		             tree2.add(treeOne1);
			  }
			tree1.add(treeOne);              
			treeOne.setChildren(tree2);
			//查询有小病种情况下的小病种 以及文本块
			List<ChapterSubItems> subItems = chapterSubItemsMapper.findSubByChapterId(chapter_id);
   		    if(subItems.size()!=0){
              for (ChapterSubItems chapterSubItems : subItems) {                       
            	  Tree tree = new Tree();
                   tree.setId(chapterSubItems.getChapter_sub_item_id());
                   tree.setText(chapterSubItems.getChapter_sub_item_name());
                   tree2.add(tree);
                   List<Items> subItemsSon = itemsMapper.findItemBySubId(chapterSubItems.getChapter_sub_item_id());
                   if (subItemsSon.size()!=0) {
                	   //定义三级树 
                	   List<Tree> tree3 = Lists.newArrayList();
                	   for (Items items : subItemsSon) {
	                        Tree treeTwo = new Tree();
	                        treeTwo.setId(items.getItems_id());
	                        treeTwo.setText(items.getItems_name());
	                        tree3.add(treeTwo);
	                    }
                	   tree.setChildren(tree3);
                   } 
                 
              }	
   		}
        return tree1;
	}*/
	public List<Tree> findTree(int chapter_id) {
		List<Tree> tree1 = Lists.newArrayList();
		int chapter_sub_item_id=0;
		//查询树根
		ChapterSubItems subItemsFather = chapterSubItemsMapper.findSubEqualsChapter(chapter_id);
        Tree treeOne = new Tree();
        treeOne.setId(subItemsFather.getChapter_sub_item_id());
        treeOne.setText(subItemsFather.getChapter_sub_item_name());
        chapter_sub_item_id=subItemsFather.getChapter_sub_item_id();
        //定义二级树
        List<Tree> tree2 = Lists.newArrayList();
        //查询无小病种的文本块
        List<Items> subItemsSon1 = itemsMapper.findItemBychapterId(chapter_sub_item_id);
		for (Items items : subItemsSon1) {
	             Tree treeOne1 = new Tree();
	             treeOne1.setId(items.getItems_id());
	             treeOne1.setText(items.getItems_name());
	             tree2.add(treeOne1);
		  }
		tree1.add(treeOne);              
		treeOne.setChildren(tree2);
		//查询有小病种情况下的小病种 以及文本块
		List<ChapterSubItems> subItems = chapterSubItemsMapper.findSubByChapterId(chapter_id);
		    if(subItems.size()!=0){
		          for (ChapterSubItems chapterSubItems : subItems) {                       
		        	  Tree tree = new Tree();
		               tree.setId(chapterSubItems.getChapter_sub_item_id());
		               tree.setText(chapterSubItems.getChapter_sub_item_name());
		               tree2.add(tree);
		               //定义三级级树
		               List<Tree> treeLast = Lists.newArrayList();
		               List<ChapterSubItems> subItemLast = chapterSubItemsMapper.findSecondSubBySubId(chapter_id, chapterSubItems.getChapter_sub_item_id());
			   		    if(subItemLast.size()!=0){
			   		    	for (ChapterSubItems chapterSubItemsLast : subItemLast) {                       
					        	  Tree tree4 = new Tree();
					               tree4.setId(chapterSubItemsLast.getChapter_sub_item_id());
					               tree4.setText(chapterSubItemsLast.getChapter_sub_item_name());
					               treeLast.add(tree4);
					               List<Items> subItemsSonLast = itemsMapper.findItemBySubId(chapterSubItemsLast.getChapter_sub_item_id());
				                   if (subItemsSonLast.size()!=0) {
				                	   //定义三级树 
				                	   List<Tree> tree3 = Lists.newArrayList();
				                	   for (Items items : subItemsSonLast) {
					                        Tree treeTwo = new Tree();
					                        treeTwo.setId(items.getItems_id());
					                        treeTwo.setText(items.getItems_name());
					                        tree3.add(treeTwo);
					                    }
				                 tree4.setChildren(tree3);
				                   }
				                  tree.setChildren(treeLast);
			   		    	}
				                	
			   		    
			   		    }else{
				   		     List<Items> subItemsSon = itemsMapper.findItemBySubId(chapterSubItems.getChapter_sub_item_id());
				               if (subItemsSon.size()!=0) {
				            	   //定义三级树 
				            	   List<Tree> tree3 = Lists.newArrayList();
				            	   for (Items items : subItemsSon) {
				                        Tree treeTwo = new Tree();
				                        treeTwo.setId(items.getItems_id());
				                        treeTwo.setText(items.getItems_name());
				                        tree3.add(treeTwo);
				                    }
				            	   tree.setChildren(tree3);
				               } 
				   		    }   
				                   
		          }
	    	}
     return tree1;
     }
	@Override
	public void deleteSub(int chapter_sub_item_id) {
		// TODO Auto-generated method stub
		chapterSubItemsMapper.deleteSub(chapter_sub_item_id);
	}
	@Override
	public void addSub(ChapterSubItems chapterSubItems) {
		// TODO Auto-generated method stub
		chapterSubItemsMapper.addSub(chapterSubItems);
	}
	@Override
	public void deleteItems(int items_id) {
		// TODO Auto-generated method stub
		itemsMapper.deleteItems(items_id);
	}
	@Override
	public void addItems(Items items) {
		// TODO Auto-generated method stub
		itemsMapper.addItems(items);
	}
	@Override
	public List<ChapterSubItems> findSubByChapterId(int chapter_id) {
		// TODO Auto-generated method stub
		return chapterSubItemsMapper.findSubByChapterId(chapter_id);
	}
	@Override
	public ChapterSubItems findSubIdByChapterId(int chapter_id) {
		// TODO Auto-generated method stub
		return chapterSubItemsMapper.findSubIdByChapterId(chapter_id);
	}
	@Override
	public ChapterSubItems findSubById(int chapter_sub_item_id) {
		// TODO Auto-generated method stub
		return chapterSubItemsMapper.findSubById(chapter_sub_item_id);
	}
	@Override
	public void deleteItemsBySubId(int chapter_sub_item_id) {
		// TODO Auto-generated method stub
		itemsMapper.deleteItemsBySubId(chapter_sub_item_id);
	}
	@Override
	public List<Items> findItemBySubId(int SubId) {
		// TODO Auto-generated method stub
		return itemsMapper.findItemBySubId(SubId);
	}
	@Override
	public Items findItemByItem(Items items) {
		// TODO Auto-generated method stub
		return itemsMapper.findItemByItem(items);
	}
	@Override
	public void addsecondSub(ChapterSubItems chapterSubItems) {
		// TODO Auto-generated method stub
		chapterSubItemsMapper.addsecondSub(chapterSubItems);
	}
	@Override
	public ChapterSubItems findSubBySub(ChapterSubItems chapterSubItems) {
		// TODO Auto-generated method stub
		return chapterSubItemsMapper.findSubBySub(chapterSubItems);
	}

	
	
}
