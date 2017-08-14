package com.msunsoft.service;

import java.util.List;

import com.msunsoft.model.ChapterSubItems;
import com.msunsoft.model.Items;
import com.msunsoft.utils.Tree;

/**
 * 标题管理
 *
 */
public interface ItemsService {

	/*
	 * 根据章节查询树形菜单列表
	 */
    List<Tree> findTree(int chapterId);
    /*
	 * 删除一级标题
	 */
	void deleteSub(int chapter_sub_item_id);
	/*
	 * 添加一级标题
	 */
	void addSub(ChapterSubItems chapterSubItems);
	/*
	 * 添加小病种下小病种
	 */
	void addsecondSub(ChapterSubItems chapterSubItems);
	/*
	 * 删除二级标题
	 */
	void deleteItems(int items_id);
	/*
	 * 根据小病种id删除文本块
	 */
	void deleteItemsBySubId(int chapter_sub_item_id);
	/*
	 * 添加二级标题
	 */
	void addItems(Items items);
	/*
	 * 根据章节id查询所有一级标题
	 */
	List<ChapterSubItems> findSubByChapterId(int chapter_id);
	/*
	 * 根据章节id查询其所在一级标题表中的id
	 */
	ChapterSubItems findSubIdByChapterId(int chapter_id);
	/*
	 * 根据一级标题id查询一级标题 
	 */
	ChapterSubItems findSubById(int chapter_sub_item_id);
	/*
	 * 查询所有二级标题
	 */
	List<Items> findItemBySubId(int SubId);
	Items findItemByItem(Items items);
	/*
	 * 根据id和name查询是否存在
	 */
	ChapterSubItems findSubBySub(ChapterSubItems chapterSubItems);
	
}
