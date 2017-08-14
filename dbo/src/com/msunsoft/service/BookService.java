package com.msunsoft.service;

import java.util.List;

import com.msunsoft.model.Book;
import com.msunsoft.utils.Tree;

/**
 * 书籍菜单
 *
 */
public interface BookService {
	/**
     * 根据用户查询树形菜单列表
     *
     * @param currentUser
     * @return
     */
    List<Tree> findTree(String book_code);

    /**
     * 根据用户名所有资源
     *
     * @return
     */
    List<Book> findAll();
    /**
     * 查询所有资源
     *
     * @return
     */
    List<Book> findBookAll(String book_code);


    /**
     * 根据id查询资源
     *
     * @param id
     * @return
     */
    Book findBookById(int book_id);
    /**
     * 根据bookcode登录
     */
    Book login(String username);

}
