package com.msunsoft.mapper;

import java.util.List;

import com.msunsoft.model.Book;

public interface BookMapper {
    /**
     * 根据用户名查询书籍
     *
     * @return
     */
    List<Book> findBookAll(String book_code);
    /**
     * 查询所有书籍
     *
     * @return
     */
    List<Book> findAll();

    /**
     * 根据id查询资源
     *
     * @param id
     * @return
     */
    Book findBookById(int book_id);
    /**
     * 根据pid查询资源
     *
     * @param id
     * @return
     */
    List<Book> findBookAllByPidNull(int parent_id);
    /**
     * 根据bookcode登录
     */
    Book login(String username);
    
    
}
	
