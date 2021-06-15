package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.User;
import com.icis.service.UserService;
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

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/12 20:25
 */
@WebServlet("/add")
public class AddU extends HttpServlet {
    private static ObjectMapper mapper = (ObjectMapper) ApplicationUtil.getContext().getBean("omapper");
    private static UserService  userService = (UserService) ApplicationUtil.getContext().getBean("userService");

    public AddU() {
    }

    @Test
    public void test(){
        System.out.println(mapper +""+ userService.getUserDao());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            String userMsg = request.getParameter("user");
            User user = mapper.readValue(userMsg, User.class);
        if (user.getBirthday() == null || "".equals(user.getBirthday())  || user.getName() == null || "".equals(user.getName()))
            throw new IOException("error");
            userService.addU(user);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<script>window.location = '"+request.getContextPath()+"/pageServlet?currentPage="+request.getParameter("currentPage")+
                "'</script>");
    }catch (Exception e){
        e.printStackTrace();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<script>window.alert('添加失败！！')</script>");
        response.getWriter().write("<script>window.location = '"+request.getContextPath()+"/pageServlet"+"'</script>");
    }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
