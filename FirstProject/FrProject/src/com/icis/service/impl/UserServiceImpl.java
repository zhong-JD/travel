package com.icis.service.impl;



import com.icis.dao.IUserDao;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.DbRigister;
import com.icis.service.IUserService;
import com.icis.utils.ApplicationUtil;
import org.junit.Test;

import java.util.List;

public class UserServiceImpl implements IUserService {
    //创建dao层对象
    private IUserDao userDao=new UserDaoImpl();

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    //        private IUserDao iUserDao = (IUserDao) ApplicationUtil.getContext().getBean("iuserDao");
//        @Test
//        public void test(){
//            System.out.println(iUserDao);
//        }
    @Override
    public Integer rigisterUser(DbRigister dbuser) {
        return userDao.rigisterUser(dbuser);
    }

    @Override
    public DbRigister JudgeUser(DbRigister dbuser) {
        return userDao.JudgeUser(dbuser);
    }


}
