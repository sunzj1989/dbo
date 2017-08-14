package com.msunsoft.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.msunsoft.mapper.BookMapper;
import com.msunsoft.model.Book;
import com.msunsoft.service.BookService;
import com.msunsoft.utils.Config;
import com.msunsoft.utils.Tree;
@Service
public class BookServiceImpl implements BookService {
	private static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookMapper bookMapper;
	@Override
	public List<Tree> findTree(String book_code) {
		 List<Tree> trees = Lists.newArrayList();
	        // 超级管理
		 List<Book> bookFather = bookMapper.findBookAllByPidNull(0);
	            for (Book book : bookFather) {
	                Tree treeOne = new Tree();

	                treeOne.setId(book.getBook_id());
	                treeOne.setText(book.getBook_name());
	                treeOne.setAttributes(book.getUrl());
	                List<Book> BookSon =null;
	                if(book_code.equals("PIS")){
	                	BookSon= bookMapper.findAll();
	                }else{
		                 BookSon = bookMapper.findBookAll(book_code);
		            }
		                if (BookSon != null) {
		                    List<Tree> tree = Lists.newArrayList();
		                    for (Book bookTwo : BookSon) {
		                        Tree treeTwo = new Tree();
		                        treeTwo.setId(bookTwo.getBook_id());
		                        treeTwo.setText(bookTwo.getBook_name());
		                        treeTwo.setAttributes(bookTwo.getUrl());
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
	@Override
	public List<Book> findBookAll(String book_code ) {
		// TODO Auto-generated method stub
		return bookMapper.findBookAll(book_code );
	}
	@Override
	public Book findBookById(int book_id) {
		// TODO Auto-generated method stub
		return bookMapper.findBookById(book_id);
	}
	@Override
	public Book login(String username) {
		// TODO Auto-generated method stub
		return bookMapper.login(username);
	}
	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookMapper.findAll();
	}

}
