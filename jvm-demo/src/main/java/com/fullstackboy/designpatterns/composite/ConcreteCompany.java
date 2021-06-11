package com.fullstackboy.designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体公司类-树枝节点
 *
 * @author Liuyongfei
 * @date 2021/3/18 09:51
 */
public class ConcreteCompany implements Company{

    private List<Company> companyList = new ArrayList<>();

    private String name;

    public ConcreteCompany(String name) {
        this.name = name;
    }


    @Override
    public void add(Company company) {
        companyList.add(company);
    }

    @Override
    public void remove(Company company) {
        company.remove(company);
    }

    @Override
    public void display(int depth) {
        StringBuffer stringBuffer = new StringBuffer("-");
        for (int i = 0; i< depth; i++) {
            stringBuffer.append("-");
        }

        System.out.println(stringBuffer.append(name));

        // 递归，会直接调用子节点的display方法
        for (Company company : companyList) {
            company.display(depth +2);
        }
    }

    @Override
    public void duty() {
        for (Company company : companyList) {
            company.duty();
        }
    }
}
