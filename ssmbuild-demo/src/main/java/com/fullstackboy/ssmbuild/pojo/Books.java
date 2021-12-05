package com.fullstackboy.ssmbuild.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Book实体类
 *
 * @author Liuyongfei
 * @date 2021/12/5 23:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    private int bookID;
    private String bookName;
    private int bookCounts;
    private String detail;
}
