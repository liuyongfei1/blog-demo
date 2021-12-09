package com.fullstackboy.ssmbuild.service;

import com.fullstackboy.ssmbuild.dao.BookMapper;
import com.fullstackboy.ssmbuild.pojo.Books;

import java.util.List;

/**
 * BookService组件实现类
 *
 * @author Liuyongfei
 * @date 2021/12/5 23:24
 */
public class BookServiceImpl implements BookService{


    /**
     * BookMapper组件
     */
    private BookMapper bookMapper;

    /**
     * 调用dao层的操作，这里设置一个set接口，方便spring管理，
     * @param bookMapper BookMapper组件
     */
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }


    @Override
    public int addBook(Books book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBookById(int id) {
        return bookMapper.deleteBookById(id);
    }

    @Override
    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }

    @Override
    public Books queryBookById(int id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public List<Books> queryAllBook() {
        return bookMapper.queryAllBook();
    }
}
