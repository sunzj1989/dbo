package com.msunsoft.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.msunsoft.mapper.ChaptersMapper;
import com.msunsoft.model.Book;
import com.msunsoft.model.Chapters;
import com.msunsoft.service.ChaptersService;
import com.msunsoft.utils.Tree;
@Service	
public class ChaptersServiceImpl implements ChaptersService{
	private static Logger logger = LoggerFactory.getLogger(ChaptersServiceImpl.class);
	@Autowired
	private ChaptersMapper chaptersMapper;
	@Override
	public Chapters findChaptersById(int chapter_id) {
		// TODO Auto-generated method stub
		return chaptersMapper.findChaptersById(chapter_id);
	}

	@Override
	public List<Chapters> findChaptersAllByBookid(int book_id,int chapter_id) {
		// TODO Auto-generated method stub
		return chaptersMapper.findChaptersAllByBookid(book_id,chapter_id);
	}

	@Override
	public List<Chapters> findByBidAndPid(int book_id) {
		// TODO Auto-generated method stub
		return chaptersMapper.findByBidAndPid(book_id);
	}

	@Override
	public List<Tree> findTree(int book_id) {
		 List<Tree> trees = Lists.newArrayList();
	        // 超级管理
		 List<Chapters> chaptersFather = chaptersMapper.findByBidAndPid(book_id);
	            for (Chapters chapters : chaptersFather) {
	                Tree treeOne = new Tree();

	                treeOne.setId(chapters.getChapter_id());
	                treeOne.setText(chapters.getChapter_name());
	                
	                List<Chapters> chaptersSon = chaptersMapper.findChaptersAllByBookid(book_id,chapters.getChapter_id());
	                if (chaptersSon != null) {
	                    List<Tree> tree = Lists.newArrayList();
	                    for (Chapters chaptersTwo : chaptersSon) {
	                        Tree treeTwo = new Tree();
	                        treeTwo.setId(chaptersTwo.getChapter_id());
	                        treeTwo.setText(chaptersTwo.getChapter_name());
	                        tree.add(treeTwo);
	                    }
	                    treeOne.setChildren(tree);
	                } else {
	                    treeOne.setState("closed");
	                }
	                trees.add(treeOne);
	            }
	            return trees;
	}

}
