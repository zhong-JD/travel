package com.icis.controller;

import com.icis.service.PageService;
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
 * @date: 2020/10/22 16:27
 */
@WebServlet("/getAddress")
public class GetAddressServlcet extends HttpServlet {
    private PageService pageService = (PageService) ApplicationUtil.getContext().getBean("pageService");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(pageService.addressSet());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
