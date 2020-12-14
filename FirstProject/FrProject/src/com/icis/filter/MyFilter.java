package com.icis.filter;

import com.icis.pojo.DbRigister;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/15 11:23
 */
@WebFilter("/*")
public class MyFilter implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        DbRigister loginUser = (DbRigister) session.getAttribute("loginUser");


        String requestURI = request.getRequestURI();
        if (requestURI.contains("/css/")||requestURI.contains("/js/")
                ||requestURI.contains("/checkCode")||requestURI.contains("/login")||requestURI.contains("/rigister.jsp")||requestURI.contains("/rigister")){
            //如果路径包含这些东西 对资源进行放行
            chain.doFilter(req,resp);
        }else {
            if (loginUser== null){
                request.setAttribute("loginMsg","您尚未登录，请先登录");
                request.getRequestDispatcher("/login.jsp").forward(req,resp);
            }else {
                chain.doFilter(req,resp);
            }
        }
    }

   /* public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
      HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String loginName = (String) session.getAttribute("loginName");
        if (loginName == null){
            request.setAttribute("loginMsg","您尚未登录，请先登录");
            request.getRequestDispatcher("/login.jsp").forward(req,resp);
        }else {
            chain.doFilter(req,resp);
        }

        String requestURI = request.getRequestURI();
        if (requestURI.contains("/css/")||requestURI.contains("/js/")
        ||requestURI.contains("/checkCode")){
            //如果路径包含这些东西 对资源进行放行
            System.out.println(requestURI);
            chain.doFilter(req,resp);
        }
    }*/

    public void init(FilterConfig config) throws ServletException {

    }

}
