package com.msunsoft.mapper;

import java.util.List;

import com.msunsoft.model.ChapterSubItems;

/**
 * 
 * @author 一级标题管理
 *
 */
public interface ChapterSubItemsMapper {
	/*
	 * 根据章节id查询所有一级标题
	 */
	List<ChapterSubItems> findSubByChapterId(int chapter_id);
	/*
	 * 删除一级标题
	 */
	void deleteSub(int chapter_id);
	/*
	 * 添加一级标题
	 */
	void addSub(ChapterSubItems chapterSubItems);
	/*
	 * 添加小病种下小病种
	 */
	void addsecondSub(ChapterSubItems chapterSubItems);
	/*
	 * 根据章节id查询其所在一级标题表中的id
	 */
	ChapterSubItems findSubIdByChapterId(int chapter_id);
	/*
	 * 根据一级标题id查询一级标题 
	 */
	ChapterSubItems findSubById(int chapter_sub_item_id);
	/*
	 * 查询章节id与条目id相同的条目
	 */
	ChapterSubItems findSubEqualsChapter(int chapter_id);
	/*
	 * 根据id和name查询是否存在
	 */
	ChapterSubItems findSubBySub(ChapterSubItems chapterSubItems);
	/*
	 * 根据病种id查询二级小病种
	 */
	List<ChapterSubItems> findSecondSubBySubId(int chapter_id,int parent_id);
}
