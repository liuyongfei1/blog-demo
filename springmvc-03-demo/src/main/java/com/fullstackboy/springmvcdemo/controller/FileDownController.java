package com.fullstackboy.springmvcdemo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * 文件下载控制器
 *
 * @author Liuyongfei
 * @date 2021/12/5 16:54
 */
@Controller
public class FileDownController {

    private static final Log logger = LogFactory.getLog(FileDownController.class);

    /**
     * 显示要下载的文件
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/showDownFiles")
    public String show(HttpServletRequest request, Model model) {

        // 获取要下载的文件的存储路径
        String realPath = request.getSession().getServletContext().getRealPath("uploadfiles");

        File dir = new File(realPath);
        File files[] = dir.listFiles();

        // 获取该目录下的所有文件名
        ArrayList<String> fileNames = new ArrayList<String>();

        for (int i = 0; i < files.length; i++) {
            fileNames.add(files[i].getName());
        }

        model.addAttribute("files", fileNames);

        return "showDownFiles";
    }

    /**
     * 执行下载
     * @param filename
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/down")
    public String down(@RequestParam String filename, HttpServletRequest request, HttpServletResponse response) {

        // 要下载的文件路径
        String aFilePath = null;

        // 输入流
        FileInputStream in = null;

        // 输出流
        ServletOutputStream out = null;

        try {
            // 获取要下载的文件的存储路径
            aFilePath = request.getSession().getServletContext().getRealPath("uploadfiles");

            // 设置下载文件使用的表头
            response.setHeader("Content-type", "application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + toUTF8String(filename));

            // 读入文件
            in = new FileInputStream(aFilePath + "/" + filename);

            // 获取输出流，用于向客户端输出二进制数据
            out = response.getOutputStream();
            out.flush();

            int aRead = 0;
            byte b[] = new byte[1024];

            while ((aRead = in.read(b))  != -1 && in != null) {
                out.write(b, 0, aRead);
            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("下载成功");
        return null;
    }

    /**
     * 下载保存时中文文件名的字符编码转换方法
     */
    public String toUTF8String(String str) {
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            // 取出字符中的每个字符
            char c = str.charAt(i);
            // Unicode码值为0~255时，不做处理
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else { // 转换 UTF-8 编码
                byte b[];
                try {
                    b = Character.toString(c).getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    b = null;
                }
                // 转换为%HH的字符串形式
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k &= 255;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
