package com.icis.utils;



import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/9/27 19:02
 */
public class JDBCUtils {
    private static DataSource ds;
    static {
        try {
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn() throws SQLException {
        return ds.getConnection();
    }
    public static void close(PreparedStatement psmt,Connection conn) throws SQLException {
        if (psmt!=null) psmt.close();
        if (conn!=null) conn.close();

    }
    public static DataSource getSource(){
        return ds;
    }

    public static JdbcTemplate getTemplate(){
        return  new JdbcTemplate(getSource());
    }

}
