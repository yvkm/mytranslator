package com.github.yvkm.plugin.tencentImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class);

    private static Properties properties;

    static {

        InputStream resourceAsStream = PropertyUtil.class.getClassLoader().getResourceAsStream("translation.properties");
        properties = new Properties();
        try {
            log.trace("正在加载配置文件");
            properties.load(resourceAsStream);

        } catch (IOException e) {
            log.error("无法加载配置文件，原因：{}", e.getMessage());
            throw new RuntimeException("无法加载配置文件！请检查文件是否存在");
        }
    }

    private PropertyUtil() {
    }


    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


    public static void main(String[] args) {
        System.out.println(PropertyUtil.getProperty("tencent.secretId"));
        System.out.println(PropertyUtil.getProperty("tencent.secretKey"));
    }
}
