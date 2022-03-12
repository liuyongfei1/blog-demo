package com.fullstackboy.dmoparsexml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用DOM解析XML工具类
 *
 * @author Liuyongfei
 * @date 2022/3/11 16:44
 */
public class DomUtil {

    /**
     * 将指定路径下的XML解析成Document对象
     * 这个Document对象包含了Student的所有信息
     * @param xmlPath xml文件绝对路径
     * @return Document对象
     * @throws Exception
     */
    public static Document getDocument(String xmlPath) throws Exception {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            return documentBuilder.parse(xmlPath);
        } catch (Exception e) {
            throw new RuntimeException("解析XML文件失败!");
        }
    }

    /**
     * 从xml文件里获取到Student信息
     * @param xmlPath xml文件绝对路径
     * @return Student信息 list
     * @throws Exception
     */
    public static List<Student> getStudents(String xmlPath) throws Exception {
        // 1. 获取student.xml对应的Document对象
        Document document = getDocument(xmlPath);

        // 2. 获取XML中所有student的节点
        List<Student> students = new ArrayList<>();
        NodeList studentNodes = document.getElementsByTagName("students");

        // 遍历每个 student 节点
        for (int i = 0; i < studentNodes.getLength(); i++) {
            Element studentEle = (Element) studentNodes.item(i);

            Student student = new Student();
            student.setName(studentEle.getElementsByTagName("name").item(0).getTextContent());
            student.setAge(Integer.parseInt(studentEle.getElementsByTagName("age").item(0).getTextContent()));
            students.add(student);
        }
        return students;
    }
}
