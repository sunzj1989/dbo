package com.msunsoft.mapper;

import java.util.List;

import com.msunsoft.model.Items;

/**
 * 二级标题管理
 *
 */
public interface ItemsMapper {
	/*
	 * 查询所有二级标题
	 */
	List<Items> findItemBySubId(int SubId);
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
	 * 若无一级标题，则根据章节id查二级标题
	 */
	List<Items> findItemBychapterId(int chapter_sub_item_id);
	Items findItemByItem(Items items);
}
