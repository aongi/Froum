package com.forum.dao;

import com.forum.domain.User;
import com.forum.tools.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 作用层
 * 实现对用户users数据表的增删改查
 * 需要使用DataSourceUtils工具类
 * Created by Aongi on 2017/5/3.
 * Version 1.0
 */
public class UserDao {
    private QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());

    /**
     * 查询所有用户
     * @return 返回所有用户集合
     */
    public List<User> selectAll() {
        String sql="SELECT * FROM users;";
        try {
            return qr.query(sql,new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("查询学生信息失败！");
        }
    }

    /**
     * 按权限查询用户
     * @param flag 用户登陆权限
     * @return 返回满足条件的用户集合
     */
    public List<User> select(String flag) {
        String sql="SELECT * FROM users WHERE flag =?;";
        try {
            return qr.query(sql,new BeanListHandler<User>(User.class),flag);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("查询学生信息失败！");
        }
    }

    /**
     * 查询用户名是否存在
     * @param username
     * @return 返回0或1，1表示已存在，0表示不存在
     */
    public Long addUser(String username) {
        String sql="SELECT COUNT(username) FROM users WHERE username=?;";
        try {
           return qr.query(sql, new ScalarHandler<>(), username);
        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();
        }
    }

    /**
     * 添加用户
     * @param user 接收到的用户对象
     * @return 返回1和0，1表示创建成功，0表示创建不成功
     */
    public int addUser(User user){
        String sql="INSERT INTO users (username,userpassword,flag) VALUES (?,?,?);";
        try {
            return qr.update(sql,user.getUsername(),user.getUserpassword(),user.isFlag());
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("添加用户失败！");
        }
    }

    /**
     * 修改用户数据
     * @param user 接收到的用户对象
     * @return 返回1和0，1表示修改成功，0表示修改不成功
     */
    public int uptateUser(User user){
        String sql="UPDATE users SET username=?,userpassword=?,flag=? WHERE uid=?;";
        try {
           return qr.update(sql,user.getUsername(),user.getUserpassword(),user.isFlag(),user.getUid());
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("修改用户失败！");
        }
    }

    /**
     * 查询uid是否存在
     * @param uid
     * @return
     */
    public Long selectUid(int uid) {
        String sql="SELECT COUNT(uid) FROM users WHERE uid=?;";
        try {
            return qr.query(sql, new ScalarHandler<>(), uid);
        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();
        }
    }

    /**
     * 查询uid所对应的用户名
     * @param uid
     * @return
     */
    public Object[] selectUidOfName(int uid){
        String sql="SELECT username FROM users WHERE uid=?;";
        try {
            return qr.query(sql,new ArrayHandler(),uid);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }
    /**
     * 修改管理员密码
     * @param user
     * @return
     */
    public int updateadminpassword(User user) {
        String sql="UPDATE users SET userpassword=? WHERE uid=1;";
        try {
            return qr.update(sql,user.getUserpassword());
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("修改密码失败！");
        }
    }

    /**
     * 删除用户数据
     * @param uid
     * @return
     */
    public int delUser(int uid) {
        String sql="DELETE FROM users WHERE uid=?;";
        try {
            return qr.update(sql,uid);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("删除用户失败！");
        }
    }
}
