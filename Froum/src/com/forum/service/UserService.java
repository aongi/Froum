package com.forum.service;

import com.forum.dao.UserDao;
import com.forum.domain.User;

import java.util.List;

/**
 * 业务层
 * 实现传递数据给Dao层
 * Created by Aongi on 2017/5/3.
 * Version 1.0
 */
public class UserService {
    private UserDao dao= new UserDao();
    //查询所有用户
    public List<User> selectAll() {
        return dao.selectAll();
    }
    //条件查询用户
    public List<User> select(String flag) {
        return dao.select(flag);
    }

    //添加用户
    public boolean addUser(User user) {
        //a的值为0或1，0表示用户不存在可以创建，1表示用户已存在
        Long a = dao.addUser(user.getUsername());
        boolean ok=false;
        if (a==0){
            int b = dao.addUser(user);
            if(b==1){
                ok=true;
            }
        }else {
            ok = false;
        }
        return ok;
    }
    //修改用户
    public boolean updateUser(User user){
        Long a = dao.addUser(user.getUsername());
        Object[] username = dao.selectUidOfName(user.getUid());
        boolean ok=false;
        if (a==0 || username[0].equals(user.getUsername())){
            int b = dao.uptateUser(user);
            if(b==1){
                ok=true;
            }
        }else {
            ok = false;
        }
        return ok;
    }
    //查询uid是否存在
    public Long selectUid(int uid) {
        return dao.selectUid(uid);
    }
    //修改管理员密码
    public int updateadminpassword(User user) {
        return dao.updateadminpassword(user);
    }
    //删除用户
    public int delUser(int uid) {
        return dao.delUser(uid);
    }
}
