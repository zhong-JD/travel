package com.icis.controller;

import com.icis.service.UserService;
import com.icis.utils.ApplicationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/12 22:46
 */
@WebServlet("/deleteU")
public class DeleteU extends HttpServlet {
    private UserService userService = (UserService) ApplicationUtil.getContext().getBean("userService");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
    try {
        String id = request.getParameter("id");
        userService.deleteU(Integer.parseInt(id));
    }catch (Exception e){
        response.getWriter().write("<script>window.alert('删除失败！')</script>");
    }
    finally {
        response.getWriter().write("<script>window.location = '"+request.getContextPath()+"/pageServlet?currentPage="+request.getParameter("currentPage")
                +"&rows="+request.getParameter("rows")+"&name="+request.getParameter("name")+"&address="+request.getParameter("address")+
                "&gender="+request.getParameter("gender")
                +"'</script>");

    }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
