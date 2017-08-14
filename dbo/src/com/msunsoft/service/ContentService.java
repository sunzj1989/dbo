package com.msunsoft.service;

import java.util.List;

import com.msunsoft.model.Content;
import com.msunsoft.utils.ContentVo;

/**
 * @author 文本块内容
 *
 */
public interface ContentService {
	/*
	 * 根据文本id以及表名查询文本内容
	 */
	Content findContentById(ContentVo contentVo);
	/*
	 * 添加文本内容
	 */
	void addContent(ContentVo contentVo);
	/*
	 * 根据文本标题id删除文本内容
	 */
	void deleteContent(ContentVo contentVo);
	/*
	 * 根据文本标题id更新文本内容
	 */
	void updateContent(ContentVo contentVo);
	/*
	 * 根据表名查询所有内容
	 */
	List<Content> findContentAll(ContentVo contentVo);
}
