package com.fullstackboy.designpatterns.composite;

/**
 * 组合模式demo
 * 公司接口
 * @author Liuyongfei
 * @date 2021/3/18 上午9:47
 */
public interface Company {
    void add (Company company);

    void remove (Company company);

    /**
     *  展示树形结构
     */
    void display (int depth);

    /**
     *  不同部门所具有的各自职责
     */
    void duty ();
}
