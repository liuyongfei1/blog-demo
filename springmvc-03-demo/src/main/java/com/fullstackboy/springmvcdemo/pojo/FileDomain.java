package com.fullstackboy.springmvcdemo.pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 封装被上传文件的信息
 *
 * @author Liuyongfei
 * @date 2021/12/5 12:43
 */
@Data
public class FileDomain {
    private String description;

    private MultipartFile myfile;
}
