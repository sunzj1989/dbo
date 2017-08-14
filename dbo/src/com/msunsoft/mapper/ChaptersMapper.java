package com.msunsoft.mapper;

import java.util.List;

import com.msunsoft.model.Chapters;

/**
 * 章节目录查询
 *
 */
public interface ChaptersMapper {
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
}
