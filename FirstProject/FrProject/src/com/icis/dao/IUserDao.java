package com.icis.dao;

import com.icis.pojo.DbRigister;

import java.util.List;

public interface IUserDao {

    //注册用户方法
    Integer rigisterUser(DbRigister dbuser);

    //判断数据库中是否存在用户的用户名与注册的用户名一致
    DbRigister JudgeUser(DbRigister dbuser);
}
