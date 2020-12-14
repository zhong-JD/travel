package com.icis.dao;

import com.alibaba.druid.util.JdbcUtils;
import com.icis.pojo.ElseUser;
import com.icis.pojo.User;
import com.icis.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/13 10:34
 */
public class PageDao {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getSource());
    public List<User> findUserByPage(int currentPage, int rows){
        String sql = "select * from user limit ?,?";  //第一个问号起点(0开始)，第二个几条数据
        int page = rows * (currentPage-1);
       return template.query(sql,new BeanPropertyRowMapper<>(User.class),page,rows);

    }

    public List<User> findUserByPageC(int currentPage, int rows,ElseUser elseUser){

        //用拿到的数据 进行模糊查询
      StringBuilder sb= new StringBuilder();
      sb.append("select * from user where 1=1 ");

            if (elseUser.getSelectname() != null  && !"" .equals(elseUser.getSelectname()))
                sb.append(" and name"+" like '"+"%"+elseUser.getSelectname()+"%' ");

             if (elseUser.getSelectaddress() != null  && !"" .equals(elseUser.getSelectaddress()))
                sb.append(" and address"+" like '"+"%"+elseUser.getSelectaddress()+"%' ");

              if (elseUser.getSelectgender() != null  && !"" .equals(elseUser.getSelectgender()))
                sb.append(" and gender"+" like '"+"%"+elseUser.getSelectgender()+"%' ");

        String sql = sb.append("limit ?,?").toString();
        int newPageStart  = (currentPage-1)*rows;
        List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), newPageStart, rows);
        return users;
    }

    public List<User> findUserByPageD(int currentPage, int rows,ElseUser elseUser,ArrayList<Integer> ids){
     //用拿到的数据 进行模糊查询

      StringBuilder sb= new StringBuilder();
      sb.append("select * from user where 1=1 ");

            if (elseUser.getSelectname() != null  && !"" .equals(elseUser.getSelectname()))
                sb.append(" and name"+" like '"+"%"+elseUser.getSelectname()+"%' ");

            if (elseUser.getSelectaddress() != null  && !"" .equals(elseUser.getSelectaddress()))
                sb.append(" and address"+" like '"+"%"+elseUser.getSelectaddress()+"%' ");

            if (elseUser.getSelectgender() != null  && !"" .equals(elseUser.getSelectgender()))
                sb.append(" and gender"+" like '"+"%"+elseUser.getSelectgender()+"%' ");

        sb.append(" and id not in (");
        for (int i = 0; i < ids.size(); i++) {
            if (i != 0){
                sb.append(",");
            }
            sb.append(ids.get(i));
        }
        sb.append(")  ");
        String sql = sb.append("limit ?,?").toString();
        int newPageStart  = (currentPage-1)*rows;
        try{
            List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), newPageStart, rows);
            return users;
        }catch (Exception e){
            return null;
        }

    }

    public int findTotalCount( ElseUser elseUser){

        StringBuilder sb= new StringBuilder();
        sb.append("select count(id) from user where 1 = 1");

            if (elseUser.getSelectname() != null  && !"" .equals(elseUser.getSelectname()))
                sb.append(" and name"+" like '"+"%"+elseUser.getSelectname()+"%' ");

            if (elseUser.getSelectaddress() != null  && !"" .equals(elseUser.getSelectaddress()))
                sb.append(" and address"+" like '"+"%"+elseUser.getSelectaddress()+"%' ");

             if (elseUser.getSelectgender() != null  && !"" .equals(elseUser.getSelectgender()))
                sb.append(" and gender"+" like '"+"%"+elseUser.getSelectgender()+"%' ");


        String sql = sb.toString();
        return template.queryForObject(sql,Integer.class);
    }
    public int findTotalCountD(ArrayList<Integer> ids,ElseUser elseUser){

        StringBuilder sb= new StringBuilder();
        sb.append("select count(id) from user where id not in(");
        for (int i = 0; i < ids.size(); i++) {
            if (i != 0){
                sb.append(",");
            }
            sb.append(ids.get(i));
        }
        sb.append(")  ");

            if (elseUser.getSelectname() != null  && !"" .equals(elseUser.getSelectname()))
                sb.append(" and name"+" like '"+"%"+elseUser.getSelectname()+"%' ");

           if (elseUser.getSelectaddress() != null  && !"" .equals(elseUser.getSelectaddress()))
                sb.append(" and address"+" like '"+"%"+elseUser.getSelectaddress()+"%' ");

              if (elseUser.getSelectgender() != null  && !"" .equals(elseUser.getSelectgender()))
                sb.append(" and gender"+" like '"+"%"+elseUser.getSelectgender()+"%' ");

        String sql = sb.toString();
        return template.queryForObject(sql,Integer.class);

    }
   /* @Test
    public void tets(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        findTotalCountD(list);
    }*/

    public List<String> addressSet(){
        String sql = "SELECT DISTINCT address FROM USER ;";
        return  template.query(sql,(rs,i)->{return rs.getString("address");});
    }
 /*   @Test
    public void test(){
        Map<String,String[]> map = new HashMap<String, String[]>();
        map.put("name",new String[]{""});
        map.put("address",new String[]{"湖南"});
        map.put("gender",new String[]{"男"});
        ArrayList<Integer> list = new ArrayList<>();
        list.add(32);
        list.add(40);
        findUserByPageD(0,3,map,list);
    }*/


}
