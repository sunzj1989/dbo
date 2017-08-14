package com.msunsoft.service;

import java.util.List;

import com.msunsoft.model.Chapters;
import com.msunsoft.utils.Tree;

/**
 * 章节目录查询
 *
 */
public interface ChaptersService {
	/*
	 * 按章节id查询
	 */
	Chapters findChaptersById(int chapter_id); 
	/*
	 * 按所属书籍id查询所有章节
	 */
	List<Chapters> findChaptersAllByBookid(int book_id,int chapter_id);
	/*
	 * 按书籍id查询所有父章节
	 */
	List<Chapters> findByBidAndPid(int book_id);
	/*
	 * 根据书籍id查询树形菜单列表
	 */
    List<Tree> findTree(int book_id);
}