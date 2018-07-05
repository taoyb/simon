package com.taoyb.simon.common.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by taoyb on 2018-01-24.
 * 读取mail配置文件
 */
public class MailConfig {
    private static final String PROPERTIES_DEFAULT = "mail.properties";
    public static String host;
    public static Integer port;
    public static String userName;
    public static String passWord;
    public static String emailForm;
    public static String timeout;
    public static String personal;
    public static Properties properties;
    static{
        init();
    }

    /**
     * @作者 TYB
     * @日期 2018-01-24 下午 9:34
     * @描述 初始化-mail配置文件-
     */
    private static void init(){
        properties = new Properties();
        try{
            InputStream inputStream = MailConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_DEFAULT);
            properties.load(inputStream);
            inputStream.close();
            host = properties.getProperty("mailHost");
            port = Integer.parseInt(properties.getProperty("mailPort"));
            userName = properties.getProperty("mailUsername");
            passWord = properties.getProperty("mailPassword");
            emailForm = properties.getProperty("mailFrom");
            timeout = properties.getProperty("mailTimeout");
            personal = properties.getProperty("personal");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
