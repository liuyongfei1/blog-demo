package com.fullstackboy.designpatterns.composite2;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用Employee类来演示组合模式
 *
 * @author Liuyongfei
 * @date 2021/3/18 17:30
 */
public class Employee {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private String title;

    private List<Employee> subordinates;

    public Employee(String name, String title) {
        this.name = name;
        this.title = title;
        this.subordinates = new ArrayList<>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    /**
     * 递归显示树结构
     * @param depth "-"个数
     * @author Liuyongfei
     * @date 2021/3/18 下午6:02
     */
    public void showTree(int depth) {
        StringBuffer stringBuffer = new StringBuffer("-");
        for (int i = 0; i< depth; i++) {
            stringBuffer.append("-");
        }
        System.out.println(stringBuffer.append(this.getName()));
        if (subordinates.size() > 0) {
            for (Employee child : subordinates) {
                child.showTree(depth + 2);
            }
        }
    }

    /**
     * 递归删除树结构
     * @author Liuyongfei
     * @date 2021/3/18 下午6:02
     */
    public void removeTree() {
        if (subordinates.size() > 0) {
            for (Employee child : subordinates) {
                child.removeTree();
            }
        }
        System.out.println("删除：" + this.getName());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", subordinates=" + subordinates +
                '}';
    }
}
