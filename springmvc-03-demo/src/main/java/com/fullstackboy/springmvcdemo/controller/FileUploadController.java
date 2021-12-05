package com.fullstackboy.springmvcdemo.controller;

import com.fullstackboy.springmvcdemo.pojo.FileDomain;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传控制器
 *
 * @author Liuyongfei
 * @date 2021/12/5 12:44
 */
@Controller
public class FileUploadController {

    private static final Log logger = LogFactory.getLog(FileUploadController.class);

    @RequestMapping("/getFileUpload")
    public String getFileUpload() {
        return "fileUpload";
    }

    @RequestMapping("/fileupload")
    public String oneFileUpload(@ModelAttribute FileDomain fileDomain, HttpServletRequest request) {

        // 设置文件上传到服务器的位置
        String realPath = request.getSession().getServletContext().getRealPath("uploadfiles");

        String fileName = fileDomain.getMyfile().getOriginalFilename();

        File targetFile = new File(realPath, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // 上传
        try {
            fileDomain.getMyfile().transferTo(targetFile);
            logger.info("成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "showFile";
    }
}
