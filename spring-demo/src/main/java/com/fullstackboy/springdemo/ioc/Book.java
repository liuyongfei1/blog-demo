package com.fullstackboy.springdemo.ioc;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/11/1 08:56
 */
public class Book {

    private String bName;

    private String bAuthor;


    public void setbName(String bName) {
        this.bName = bName;
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }

    public void printBook() {
        System.out.println(bName + ": " + bAuthor);
    }
}
