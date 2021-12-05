package com.fullstackboy.springmvcdemo.controller;

import com.fullstackboy.springmvcdemo.pojo.FileDomain;
import com.fullstackboy.springmvcdemo.pojo.MultiFileDomain;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @RequestMapping("/getmultiFile")
    public String getmultiFile() {
        return "multiFiles";
    }

    /**
     * 多文件上传
     * @param multiFileDomain 上传文件信息
     * @param request
     * @return
     */
    @RequestMapping("/multifileUpload")
    public String multiFileUpload(@ModelAttribute MultiFileDomain multiFileDomain, HttpServletRequest request) {
        // 设置文件上传到服务器的位置
        String realPath = request.getSession().getServletContext().getRealPath("uploadfiles");

        List<MultipartFile> files = multiFileDomain.getMyfile();

        for (int i = 0; i < files.size(); i++) {

            MultipartFile file = files.get(i);
            String fileName = file.getOriginalFilename();

            File targetFile = new File(realPath, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            // 上传
            try {
                file.transferTo(targetFile);
                logger.info("成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "showMulti";
    }
}
