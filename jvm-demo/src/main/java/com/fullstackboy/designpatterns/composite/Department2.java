package com.fullstackboy.designpatterns.composite;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/3/18 10:01
 */
public class Department2 implements Company{
    private String name;

    public Department2(String name) {
        this.name = name;
    }

    @Override
    public void add(Company company) {

    }

    @Override
    public void remove(Company company) {

    }

    @Override
    public void display(int depth) {
        StringBuffer stringBuffer = new StringBuffer("-");
        for (int i = 0; i < depth ; i++) {
            stringBuffer.append("-");
        }

        System.out.println(stringBuffer.append(name));
    }

    @Override
    public void duty() {
        System.out.println(name + "负责销售");
    }
}
