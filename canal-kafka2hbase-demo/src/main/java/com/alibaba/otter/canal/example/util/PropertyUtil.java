package com.alibaba.otter.canal.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Desc:    配置文件读取工具类
 */
public final class PropertyUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.alibaba.otter.canal.example.util.PropertyUtil.class);//记载日志
    private static Properties props = new Properties();

    private PropertyUtil() {
    }

    static {
        try {
            //系统级配置
            props.load(com.alibaba.otter.canal.example.util.PropertyUtil.class.getResourceAsStream("/app.properties"));
        } catch (IOException e) {
            LOGGER.error("PropertyUtil" + e.getMessage());
        }
    }

    /**
     * 获取属性，根据传入参数进行格式化
     *
     * @param name
     * @param obj
     * @return
     */
    public static String getProp(String name, Object[] obj) {
        String value = props.getProperty(name);
        return MessageFormat.format(value, obj);
    }

    /**
     * 获取属性
     *
     * @param name
     * @return
     */
    public static String getProp(String name) {
        return props.getProperty(name);
    }
}
