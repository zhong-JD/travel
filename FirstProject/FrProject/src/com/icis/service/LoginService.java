package com.icis.service;

import com.icis.dao.LoginDao;
import com.icis.pojo.DbRigister;
import com.icis.utils.ApplicationUtil;
import org.junit.Test;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/12 18:40
 */
public class LoginService {
    LoginDao loginDao;

    public LoginDao getLoginDao() {
        return loginDao;
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public DbRigister login(String name, String pwd){
        return  loginDao.login(name,pwd);
    }
}
