package com.fullstackboy.test;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/1/30 21:59
 */
public class WhiteCat extends AbstractCat{

    private String name;

    public WhiteCat(String name) {
        this.name = name;

        sound();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
