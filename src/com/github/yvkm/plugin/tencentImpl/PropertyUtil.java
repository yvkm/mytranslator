package com.github.yvkm.plugin.tencentImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    private static Properties properties;

    static {

        InputStream resourceAsStream = PropertyUtil.class.getClassLoader().getResourceAsStream("translation.properties");
        properties = new Properties();
        try {
            properties.load(resourceAsStream);

        } catch (IOException e) {
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
