package com.fullstackboy.mybatis.designpatterns.composite2;

/**
 * 测试类
 *
 * @author Liuyongfei
 * @date 2021/3/18 17:34
 */
public class Test {
    public static void main(String[] args) {
        Employee boss = new Employee("曹操","CEO");

        Employee headSale = new Employee("张三","Sales");
        Employee headDev = new Employee("李四","CTO");

        Employee sale1 = new Employee("销售1","职工");
        Employee sale2 = new Employee("销售2","职工");

        Employee programmera = new Employee("猿a","工程师");
        Employee programmerb = new Employee("猿b","工程师");

        boss.add(headSale);
        boss.add(headDev);

        headSale.add(sale1);
        headSale.add(sale2);

        headDev.add(programmera);
        headDev.add(programmerb);

//        System.out.println("--" + boss.getName());
//
//        // 递归显示部门树形结构
//        for (Employee head : boss.getSubordinates()) {
//            System.out.println("----" + head.getName());
//            for (Employee employee : head.getSubordinates()) {
//                System.out.println("------" + employee.getName());
//            }
//        }

        boss.showTree(1);
        boss.removeTree();
    }
}
