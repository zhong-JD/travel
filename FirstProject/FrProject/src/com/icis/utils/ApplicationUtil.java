package com.icis.utils;

import com.icis.dao.IUserDao;
import com.icis.dao.LoginDao;
import com.icis.dao.UserDao;
import com.icis.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:ReycoLL
 * @Date:2020/11/2
 * @Description: Enjoy yourself
 */
public class ApplicationUtil {
    private static ApplicationContext context;
    static {
            context = new ClassPathXmlApplicationContext("beans.xml");
    }
    public static ApplicationContext getContext(){
        return context;
    }


    @Test
    public void setContext(){
        System.out.println(context.getBean("userDao"));
    }
    public static void main(String[] args) {
        IUserDao userDao = (IUserDao) context.getBean("iuserDao");
        System.out.println(userDao);
    }
}
