package com.icis.dao.impl;

import com.icis.dao.IUserDao;
import com.icis.pojo.DbRigister;
import com.icis.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class UserDaoImpl implements IUserDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getSource());

    @Override
    public Integer rigisterUser(DbRigister dbuser) {

        String sql="INSERT INTO db_rigister (NAME,PASSWORD) VALUES (?,?);";

        int update = jdbcTemplate.update(sql, dbuser.getName(), dbuser.getPassword());
        System.out.println(update);
        return update;
    }

    @Override
    public DbRigister JudgeUser(DbRigister dbuser) {
            String sql="SELECT * FROM db_rigister WHERE NAME = ? ;";
            return   jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(DbRigister.class),dbuser.getName());

    }

  /*@Test
    public void test(){
      Date date=new Date();
        User user3 =new User(9,"清狗","女","广东",date);
        UpdateUserById(user3);
      *//*User userByNmaAndPwd = getUserByNmaAndPwd(user3);
      System.out.println(userByNmaAndPwd);*//*

  }*/
}
