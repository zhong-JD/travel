package com.icis.service;

import com.icis.dao.UserDao;
import com.icis.pojo.ElseUser;
import com.icis.pojo.PageBean;
import com.icis.pojo.User;
import com.icis.utils.ApplicationUtil;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/12 19:05
 */
public class UserService {
//    UserDao userDao = UserDao.getInstance();
private   UserDao userDao;

    public UserService() {
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    /*public User selectU(ElseUser user){
            User user1 = userDao.selectU(user);
            return user1;
        }*/
    public int addU(User user){
        return userDao.addU(user);
    }
    public int updateU(int id,User user){
        return userDao.updateU(id,user);
    }
    public int deleteU(int id){
        return userDao.deleteU(id);
    }
//    public int deleteUser(List<Object[]> objects){
//        return userDao.deleteUsers(objects);
//    }
    public User selectById(int id){
        return userDao.selectById(id);
    }
    public PageBean<User> deleteArr(PageBean pageBean,String[] ids){
        ArrayList<User> Ulist = pageBean.getList();
        for (String id : ids) {
            Ulist.remove(getIdIndex(Ulist,id));
        }
        pageBean.setList( Ulist);
        System.out.println(Ulist);
        return  pageBean;
    }
    public User getIdIndex(ArrayList<User> list,String id){
        for (User user : list) {
            if (user.getId() == Integer.parseInt(id))
                return user;
        }
        return  null;
    }
    public ArrayList<Integer>  removeDuplicate(ArrayList<Integer> list) {
        for(int i = 0; i < list.size(); i++) {
            for(int j = i+1; j < list.size(); j++) {
                if(list.get(i).equals(list.get(j))) {
                    list.remove(list.get(j));
                    j--;
                }
            }
        }
        return list;
    }
    public List<User> getDeledUsers(ArrayList<Integer> ids){

        return userDao.getDeledUsers(ids);
    }


 /*   @Test
    public void test(){
        List<ElseUser> users = new ArrayList<>();
        users.add(new ElseUser("网吧","水帘洞","男"));
        users.add(new ElseUser("玩儿","水帘洞","男"));
        users.add(new ElseUser("送是","水帘洞","男"));
        System.out.println(users.get);
    }*/

}
