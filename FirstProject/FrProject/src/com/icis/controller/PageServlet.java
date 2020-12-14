package com.icis.controller;

import com.icis.pojo.ElseUser;
import com.icis.pojo.PageBean;
import com.icis.pojo.User;
import com.icis.service.PageService;
import com.icis.service.UserService;
import com.icis.utils.ApplicationUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/13 10:31
 */
@WebServlet("/pageServlet")
public class PageServlet extends HttpServlet {
    private PageService pageService = (PageService) ApplicationUtil.getContext().getBean("pageService");
    private UserService userService = (UserService) ApplicationUtil.getContext().getBean("userService");

    @Test
    public  void test(){
        System.out.println(userService+ ""+ pageService);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setCharacterEncoding("utf-8");
            //获取数据
            String currentPage = request.getParameter("currentPage");
            String rows = request.getParameter("rows");
            if (null == currentPage || "".equals(currentPage)){
                currentPage = "1";
            }
            if (null == rows || "".equals(rows)){
                rows = "6";
            }
            Map<String, String[]> parameterMap = request.getParameterMap();
            Set<String> strings = parameterMap.keySet();
            for (String string : strings) {
                System.out.println(string + "=====" + parameterMap.get(string)[0]);
            }
            ElseUser elseUser = new ElseUser("","","");
          if ( parameterMap!=null && parameterMap.get("array[]") == null ){
              BeanUtils.populate(elseUser,parameterMap);
              System.out.println(elseUser);
          }

            Integer delArr = (Integer) request.getSession().getAttribute("delArr") == null?0:(Integer) request.getSession().getAttribute("delArr");//是否存在有选中删除的操作
            System.out.println(delArr);
            if (delArr==1){
                //有删除选中的情况
                ArrayList<Integer> delIdsArr = (ArrayList<Integer>) request.getSession().getAttribute("delIdsArr");
                PageBean<User> bean = pageService.findUserByPageD(Integer.parseInt(currentPage),Integer.parseInt(rows),elseUser,delIdsArr);
                request.getSession().setAttribute("pageBean",bean);
                request.setAttribute("selectHelp",elseUser);
                List<User> deledUsers = userService.getDeledUsers(delIdsArr);
                request.getSession().setAttribute("getDeledUsers",deledUsers);
           }else if (delArr == 2){
                //恢复部分数据
                ArrayList<Integer> restore = (ArrayList<Integer>) request.getSession().getAttribute("delIdsArr");
                PageBean<User> bean = pageService.findUserByPageD(Integer.parseInt(currentPage),Integer.parseInt(rows),elseUser,restore);
                request.getSession().setAttribute("pageBean",bean);
                List<User> deledUsers = userService.getDeledUsers(restore);
                request.getSession().setAttribute("getDeledUsers",deledUsers);
                request.setAttribute("selectHelp",elseUser);
            }
            else {
                //恢复全部数据
                PageBean<User> bean = pageService.findUserByPage(Integer.parseInt(currentPage),Integer.parseInt(rows),elseUser);
                request.getSession().removeAttribute("getDeledUsers");
                request.getSession().setAttribute("pageBean",bean);
                request.setAttribute("selectHelp",elseUser);

            }

            request.getRequestDispatcher("list.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
