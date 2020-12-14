package com.icis.controller;

import com.icis.pojo.User;
import com.icis.service.UserService;
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
 * @date: 2020/10/12 21:45
 */


@WebServlet("/changeU")
public class changeU extends HttpServlet {
    private UserService userService = (UserService) ApplicationUtil.getContext().getBean("userService");

    @Test
    public void name() {
        System.out.println(userService.getUserDao()+"");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = userService.selectById(Integer.parseInt(request.getParameter("id")));
        request.getSession().setAttribute("changeUser",user);
        request.setAttribute("selectHelp",request.getAttribute("selectHelp"));
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
