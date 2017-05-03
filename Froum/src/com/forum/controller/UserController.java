package com.forum.controller;

import com.forum.domain.User;
import com.forum.service.UserService;

import java.util.List;

/**
 * 控制层
 * 实现传递数据给Service层
 * Created by Aongi on 2017/5/3.
 * Version 1.0
 */
public class UserController {
    private UserService service = new UserService();
    //查询所有用户
    public List<User> selectAll() {
        return service.selectAll();
    }
    //根据条件查找
    public List<User> select(String flag) {
        return service.select(flag);
    }
    //添加用户
    public boolean addUser(User user){
        return service.addUser(user);
    }
    //修改用户
    public boolean uptateUser(User user){
        return service.updateUser(user);
    }
    //查询uid是否存在
    public Long selectUid(int uid){
        return service.selectUid(uid);
    }
    //修改管理员密码
    public int updateadminpassword(User user) {
        return service.updateadminpassword(user);
    }
    //修改用户
    public boolean updateUser(User user) {
        return service.updateUser(user);
    }
    //删除用户
    public int delUser(int uid) {
        return service.delUser(uid);
    }
}
