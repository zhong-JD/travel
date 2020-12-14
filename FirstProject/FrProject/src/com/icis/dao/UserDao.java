package com.icis.dao;

import com.icis.pojo.ElseUser;
import com.icis.pojo.User;
import com.icis.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/12 19:06
 */
public class UserDao {
    public UserDao() {
    }

    JdbcTemplate template = JDBCUtils.getTemplate();
    public List<User>  selectAll(){
         template = JDBCUtils.getTemplate();
        String sql = "select * from user";
       return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getDeledUsers(ArrayList<Integer> delids){
        StringBuilder sb = new StringBuilder();
        sb.append("select * from user where id in (");
        for (int i = 0; i < delids.size(); i++) {
            if (i!=0) sb.append(",");
            sb.append(delids.get(i));
        }
        sb.append(")");
        String sql = sb.toString();
        System.out.println(sql);
        List<User> deledUsers = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return deledUsers;
    }
    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        getDeledUsers(list);
    }
    /*public User selectU(ElseUser elseUser){
        String sql = "select * from user where name = ? and address = ? and email = ? ";
         return template.queryForObject(sql, (rs, i) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            String address = rs.getString("address");
            String email = rs.getString("email");
            Date birthday = rs.getDate("birthday");

            return new User(id, name, gender, age, address, birthday, email, null);
        },elseUser.getName().trim(),elseUser.getAddress().trim(),elseUser.getEmail().trim());
    }*/
    public int addU(User user){
        String sql = "insert into user values(null,?,?,?,?,?,?,?)";
       return template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getBirthday(),
                user.getEmail(),user.getPassword());
    }
    public int updateU(int id,User user){
        String sql = "update user set name=?, gender=?,age=?,address=?,birthday=?,email=?,password=? where  id = ?";
        System.out.println("update"+user);
        return template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getBirthday(),
                user.getEmail(),user.getPassword(),id);
    }
    public int deleteU(int id){
        String sql = "delete  from user where id = ?";
        return template.update(sql,id);
    }
    public int deleteUsers(List<Object[]> batchArgs){
        String sql = "delete from user where id = ?";
        int[] ints = template.batchUpdate(sql,batchArgs);
        return 1;
    }
    public  User selectById(int id ) {
        String sql = "select * from user where id = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        return user;

    }
   /* @Test
    public void test(){
        String name = "hahahaha    ";
        System.out.println(name.trim());
    }*/

   /* @Test
    public void test(){
     List<Object[]> objects = new ArrayList<>();
     objects.add(new Object[]{18});
     objects.add(new Object[]{19});
        System.out.println(deleteUsers(objects));
    }*/
/*@Test
    public void test(){
    ElseUser elseUser = new ElseUser("龙龙","广州","long@qq.com");
    User user = selectU(elseUser);
    user.setName("龙儿龙");
    user.setEmail("ll888lll@qq.com");
    updateU(user.getId(),user);
}*/
    /*@Test
    public void test() throws ParseException {
        String s = "1999-06-02";
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d1 = smf.parse(s);
        java.sql.Date d2 = new java.sql.Date(d1.getTime());
        addU(new User(0,"花花","男",22,"广州",d2,"hua@qq.com","jiagoushi"));
    }*/


 /*   @Test
    public void test(){
        ElseUser elseUser = new ElseUser("龙龙","广州","long@qq.com");
        System.out.println(selectU(elseUser));
    }*/
/*@Test
    public void login(){
        String sql = "select * from user where name = ? and password = ?";
    User user = template.queryForObject(sql, (rs, i) -> {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String gender = rs.getString("gender");
        int age = rs.getInt("age");
        String address = rs.getString("address");
        String email = rs.getString("email");
        Date birthday = rs.getDate("birthday");

        return new User(id, name, gender, age, address, birthday, email, null);
    },"八戒","jiagoushi");
    System.out.println(user);
    System.out.println(user.getBirthday().getYear());
}*/

 /*   @Test
    public void test(){
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
    }*/
}
