package com.icis.dao;


import com.icis.pojo.DbRigister;
import com.icis.pojo.User;
import com.icis.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/12 18:40
 */
public class LoginDao {

        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getSource());


    public DbRigister login(String name,String pwd ){
        System.out.println(name +  " ------- " + pwd);
        String sql = "select * from db_rigister where name = ? and password = ?";
        DbRigister loginUser = template.queryForObject(sql, new BeanPropertyRowMapper<>(DbRigister.class), name, pwd);
        return loginUser;
    }
    @Test
    public void test(){
       login("long","123456");
    }
}
