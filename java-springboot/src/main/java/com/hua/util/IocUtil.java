package com.hua.util;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ioc工具包
 *
 * @author: huabin
 * @date: 2021/9/23 上午10:14
 */
public class IocUtil {

    /**
     * 功能简述：根据配置文件名加载配置文件
     * @param fileName
     * @return
     */
    public static Properties getPropertyByName(String fileName) throws IOException {
        InputStream is = null;
        Properties pro = null;

        try {
            is = IocUtil.class.getClassLoader().getResourceAsStream(fileName);
            pro = new Properties();
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return pro;
        }

    }

    /**
     * 功能简述：首字母转小写
     * @param name
     * @return
     */
    public static String toLowercaseIndex(String name){
        if (!StringUtils.isEmpty(name)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(name.substring(0, 1).toLowerCase());
            stringBuilder.append(name.substring(1, name.length()));
            return stringBuilder.toString();
        }
        return null;
    }
}
