package com.icis.controller;

import com.icis.pojo.DbRigister;
import com.icis.service.LoginService;
import com.icis.utils.ApplicationUtil;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/12 9:28
 */
@WebServlet("/login")
public class ToLoginServlet extends HttpServlet {
    LoginService loginService = (LoginService) ApplicationUtil.getContext().getBean("loginService");

    @Test
    public void test(){
        System.out.println(loginService);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   try {
       request.setCharacterEncoding("utf-8");
       String verifycode = request.getParameter("verifycode");
       String vcode = (String) request.getSession().getAttribute("vcode");
       if (vcode.equalsIgnoreCase(verifycode)){
           //验证码正确s
         try {
             DbRigister loginUser= loginService.login(request.getParameter("user") ,request.getParameter("password"));
             System.out.println("login" + loginUser);
             request.getSession().removeAttribute("vcode");
             request.getSession().setAttribute("loginUser",loginUser);
             request.getRequestDispatcher("index.jsp").forward(request,response);
         }catch (Exception e){
             e.printStackTrace();
             //用户名或者密码错误
             request.setAttribute("loginMsg","账号密码错误");
             request.getRequestDispatcher("login.jsp").forward(request,response);

         }
       }else {
           //验证码错误
           request.setAttribute("loginMsg","验证码错误！");
           request.getRequestDispatcher("login.jsp").forward(request,response);
       }
   }catch (Exception e){
        e.printStackTrace();
   }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
