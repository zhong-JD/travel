package com.icis.controller;

import com.icis.pojo.PageBean;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.utils.ApplicationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/13 8:44
 */
@WebServlet("/deletearr")
public class Deletearr extends HttpServlet {
 private UserService userService = (UserService) ApplicationUtil.getContext().getBean("userService");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
          //获得session域中  已经逻辑删除的 id们
            ArrayList<Integer> delIds;
            ArrayList<Integer> yuanlaiDelids = (ArrayList<Integer>) request.getSession().getAttribute("delIdsArr");
            if (yuanlaiDelids == null){
                    delIds = new ArrayList<Integer>();
            }else {
                delIds = yuanlaiDelids;
            }

            String deleteArr = request.getParameter("DeleteArr");
            if (deleteArr.equals("1")){
                //选中删除
                String[] deleteIDS = request.getParameterValues("array[]");
                addToList(delIds,deleteIDS);
                userService.removeDuplicate(delIds);
                request.getSession().setAttribute("delIdsArr",delIds);
                request.getSession().setAttribute("delArr",1); //是否有选中删除的元素
            }else if (deleteArr.equals("0")){
                //全部恢复
                request.getSession().setAttribute("delArr",0);
                request.getSession().removeAttribute("delIdsArr");
            }else if (deleteArr.equals("2")){
                //部分恢复

                String[] restore = request.getParameterValues("array[]");
                ArrayList<Integer> list = new ArrayList<>();
                addToList(list,restore);
                //获得原来已经删除的 id数组
                ArrayList<Integer> AllDelids = (ArrayList<Integer>) request.getSession().getAttribute("delIdsArr");

                //在原来的Id数组中删除要恢复的id  并获得最新的id数组
                clearDumId(list,AllDelids);

                request
                        .getSession().setAttribute("delIdsArr",AllDelids);
                request.getSession().setAttribute("delArr",2);
            }
            request.getRequestDispatcher("/pageServlet").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("delMsg","0");

        }
    }

    private void clearDumId(ArrayList<Integer> delIds, ArrayList<Integer> AllDelids) {
        for (Integer delId : delIds) {
            //遍历恢复数组  并删除所有删除 的id数组中的对应值
                AllDelids.remove(delId);
        }
    }

    private void addToList(ArrayList<Integer> delIds,String[] deleteIDS) {
        //将获得的数组加入到 删除或者恢复   ID数组
        for (String deleteID : deleteIDS) {
            delIds.add(Integer.parseInt(deleteID));
        }
    }
}
