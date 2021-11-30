package com.fullstackboy.mybatis.designpatterns.composite;

/**
 * 测试
 *
 * @author Liuyongfei
 * @date 2021/3/18 10:02
 */
public class Test {
    public static void main(String[] args) {
        // 根节点
        ConcreteCompany  root = new ConcreteCompany("总公司");
        root.add(new Department1("总公司部门1"));
        root.add(new Department2("总公司部门2"));
//
//        // 子节点
        ConcreteCompany comp = new ConcreteCompany("分公司");
        comp.add(new Department1("分公司部分1"));
        comp.add(new Department2("分公司部分2"));
        root.add(comp);

        // 叶子节点
        ConcreteCompany comp1 = new ConcreteCompany("办事处1");
        comp1.add(new Department1("办事处1部门1"));
        comp1.add(new Department2("办事处1部门2"));
        comp.add(comp1);

        // 叶子节点
        ConcreteCompany comp2 = new ConcreteCompany("办事处2");
        comp2.add(new Department1("办事处2部门1"));
        comp2.add(new Department2("办事处2部门2"));
        comp.add(comp2);

        root.display(1);
//        root.duty();
    }
}
