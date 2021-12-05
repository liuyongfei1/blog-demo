package com.fullstackboy.springmvcdemo.pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用于多文件上传时封装文件信息
 *
 * @author Liuyongfei
 * @date 2021/12/5 16:20
 */
@Data
public class MultiFileDomain {
    private List<String> description;

    private List<MultipartFile> myfile;

}
