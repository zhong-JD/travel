package com.icis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.dao.PageDao;
import com.icis.pojo.ElseUser;
import com.icis.pojo.PageBean;
import com.icis.pojo.User;
import com.icis.utils.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/13 10:34
 */
public class PageService {
    Jedis jedis = JedisUtils.getJedis();
    PageDao pageDao;
    ObjectMapper mapper;

    public PageDao getPageDao() {
        return pageDao;
    }

    public void setPageDao(PageDao pageDao) {
        this.pageDao = pageDao;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    //无选中删除的
   public PageBean<User> findUserByPage(int currentPage, int rows, ElseUser elseUser){
       PageBean<User> pageBean = new PageBean<>();
       if(currentPage <= 0 ){
           currentPage =1;
       }
       int totalCount = pageDao.findTotalCount(elseUser);
       int totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount /rows +1  ;
       pageBean.setTotalPage(totalPage);
       if (currentPage > totalPage)
           currentPage = totalPage;
       List<User> list ;
       try
       {
           list = pageDao.findUserByPageC(currentPage,rows,elseUser);
       }catch (Exception e){
           list = new ArrayList<>();
       }

       pageBean.setTotalCount(totalCount);
       pageBean.setCurrentPage(currentPage);
       pageBean.setRows(rows);
       pageBean.setList((ArrayList<User>) list);

       return  pageBean;
   }

   //有选中删除的
    public PageBean<User> findUserByPageD(int currentPage, int rows, ElseUser elseUser,ArrayList<Integer> ids){
        PageBean<User> pageBean = new PageBean<>();
        if(currentPage <= 0 ){
            currentPage =1;
        }
        int totalCount = pageDao.findTotalCountD(ids,elseUser);
        int totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount /rows +1  ;
        pageBean.setTotalPage(totalPage);
        if (currentPage > totalPage)
            currentPage = totalPage;
        List<User> list = pageDao.findUserByPageD(currentPage,rows,elseUser,ids);
        pageBean.setTotalCount(totalCount);
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
       pageBean.setList((ArrayList<User>) list);

        return  pageBean;
    }
   public String addressSet(){
       String provinceList = jedis.get("provinceList");
       try{
           if (provinceList == null){
               List<String> addresses = pageDao.addressSet();
               jedis.set("provinceList",mapper.writeValueAsString(addresses));
           }
       }catch (Exception e){
                e.printStackTrace();
       }finally {
           return  provinceList;
       }
   }

}
