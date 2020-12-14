package com.icis.controller;

import com.icis.pojo.DbRigister;
import com.icis.service.IUserService;
import com.icis.service.impl.UserServiceImpl;
import com.icis.utils.ApplicationUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/rigister")
public class RigisterServlet extends HttpServlet {

//    private IUserService userServiceImpl=new UserServiceImpl();
private IUserService userService = (IUserService) ApplicationUtil.getContext().getBean("userServiceImpl");

    @Test
    public void name() {
        System.out.println(userService);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //设置编码
            request.setCharacterEncoding("utf-8");
            //获得注册页面数据
            Map<String, String[]> RigisterparameterMap = request.getParameterMap();
            //将数据封装到用户实体类
            DbRigister dbuser = new DbRigister();
            BeanUtils.populate(dbuser, RigisterparameterMap);


            if (dbuser.getName() != null && !dbuser.getName().equals("") && dbuser.getPassword() != null && !dbuser.getPassword().equals("")) {
                //判断数据库中是否存在用户的用户名与注册的用户名一致
                try {
                    DbRigister resultUser = userService.JudgeUser(dbuser);
                    //用户名存在
                    //存在用户名一致 设置提醒信息
                    request.setAttribute("loginMsg", "用户名重复! 注册失败!!");
                    //请求转发
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } catch (Exception e) {
                    //可以进行注册
                    //调用service层的注册方法
                    Integer i = userService.rigisterUser(dbuser);
                    if (i > 0) {
                        request.setAttribute("loginMsg", "恭喜您,注册成功!");
                        //请求转发
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    } else {
                        request.setAttribute("loginMsg", "很遗憾,注册失败!");
                        //请求转发
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("loginMsg", "很遗憾,注册失败!");
            //请求转发
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        request.setAttribute("loginMsg", "很遗憾,注册失败!");
        //请求转发
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
