package com.forum.tools;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * 链接池工具类
 * Created by Aongi on 2017/5/2.
 * Version 1.0
 */
public class DataSourceUtils {
    private static BasicDataSource dataSource;
    static{
        InputStream in = DataSourceUtils.class.getResourceAsStream("datasource.properties");
        Properties pro = new Properties();
        try {
            pro.load(in);
            in.close();
            dataSource= BasicDataSourceFactory.createDataSource(pro);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("数据库连接失败！");
        }

    }
    public static DataSource getDataSource(){
        return dataSource;
    }
}
