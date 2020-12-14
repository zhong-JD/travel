package com.icis.controller;

import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.utils.ApplicationUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/12 20:55
 */
@WebServlet("/updateU")
public class UpdateU extends HttpServlet {
    private UserService userService = (UserService) ApplicationUtil.getContext().getBean("userService");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           request.setCharacterEncoding("utf-8");
           User user = new User();
           BeanUtils.populate(user,request.getParameterMap());
           if (user.getBirthday() == null || "".equals(user.getBirthday()))
               throw new IOException("error");
           User changeUser = (User) request.getSession().getAttribute("changeUser");
           userService.updateU(changeUser.getId(),user);
           response.setCharacterEncoding("utf-8");
       }catch (Exception e){
            e.printStackTrace();
           response.setCharacterEncoding("utf-8");
           response.setContentType("text/html;charset=utf-8");
           response.getWriter().write("<script>window.alert('修改失败！')</script>");
       }finally {
           response.setContentType("text/html;charset=utf-8");
           response.getWriter().write("<script>window.location = '"+request.getContextPath()+"/pageServlet?currentPage="+request.getParameter("currentPage")
                   +"&rows="+request.getParameter("rows")+"&selectname="+request.getParameter("selectname")+"&selectaddress="+request.getParameter("selectaddress")+
                   "&selectgender="+request.getParameter("selectgender")
                   +"'</script>");
       }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}


