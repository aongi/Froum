package com.forum.view;

import com.forum.controller.UserController;
import com.forum.domain.User;

import java.util.List;
import java.util.Scanner;

/**
 * 视图层
 * 实现传递数据给Controller层
 * Created by Aongi on 2017/5/3.
 * Version 1.0
 */
public class UserView {
    private UserController controller= new UserController();

    /**
     * 实现界面效果，获取用户输入
     * 根据不同的数据，调用不同的方法
     */
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("------------------管理员操作-------------------");
            System.out.println("1.添加用户 2.删除用户 3.修改用户 4.查询用户 5.退出");
            System.out.println("请输入要操作的功能序号[1-5]:");
            String s = scanner.nextLine();
            switch (s){
                case "1":
                    addUser();
                    break;
                case "2":
                    delUser();
                    break;
                case "3":
                    updateUser();
                    break;
                case "4":
                    selectUser();
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("输入有误，请重新选择！");
                    break;
            }
        }
    }

    /**
     * 删除用户
     */
    public void delUser(){
        selectAll();
        System.out.println("请输入你要删除的用户的ID");
        Scanner scanner = new Scanner(System.in);
        try {
            int uid =Integer.parseInt(scanner.nextLine());
            if(uid==1){
                System.out.println("管理员账户不能被删除！请重新输入！");
                delUser();
                return;
            }
            if(controller.selectUid(uid)==0){
                System.out.println("ID不存在，请重新输入！");
                delUser();
                return;
            }
            int ok = controller.delUser(uid);
            if(ok == 1){
                System.out.println("删除用户成功！");
            }else {
                System.out.println("删除用户失败！");
            }
        }catch (Exception e){
            System.out.println("输入有误，请重新输入！");
            delUser();
            return;
        }
    }
    /**
     * 修改用户
     */
    public void updateUser(){
        selectAll();
        System.out.println("请输入你要修改的用户的ID");
        Scanner scanner = new Scanner(System.in);
        try {
            int uid =Integer.parseInt(scanner.nextLine());
            if(uid==1){
                System.out.println("提示：管理员账号只能修改密码！");
                System.out.println("请输入密码：");
                String adminpassword = scanner.nextLine();
                int passwordOk = controller.updateadminpassword(new User(0,"",adminpassword,true));
                if(passwordOk==1){
                    System.out.println("修改成功！");
                }else {
                    System.out.println("修改失败！");
                }
            }
            if(controller.selectUid(uid)==0){
                System.out.println("ID不存在，请重新输入！");
                updateUser();
                return;
            }
            System.out.println("提示：用户名只能由6-10个大小写英文字母组成");
            System.out.println("请输入用户名：");
            String username=scanner.nextLine();
            if(!username.matches("[a-zA-Z]{6,10}")){
                System.out.println("用户名输入有误，请重新输入！");
                updateUser();
                return;
            }
            System.out.println("请输入密码：");
            String userpassword = scanner.nextLine();
            System.out.println("请输入用户权限（true或false）：");
            boolean flag = scanner.nextBoolean();
            boolean ok= controller.updateUser(new User(uid,username,userpassword,flag));
            if(ok){
                System.out.println("修改成功！");
            }else {
                System.out.println("用户名已存在！");
                updateUser();
                return;
            }
        }catch (Exception e){
            System.out.println("输入有误，请重新输入！");
            updateUser();
            return;
        }
    }

    /**
     * 添加用户方法
     */
    public void addUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("提示：用户名只能由6-10个大小写英文字母组成");
        System.out.println("请输入用户名：");
        String username=scanner.nextLine();
        if(!username.matches("[a-zA-Z]{6,10}")){
            System.out.println("用户名输入有误，请重新输入！");
            addUser();
            return;
        }
        System.out.println("请输入密码：");
        String userpassword = scanner.nextLine();
        boolean ok= controller.addUser(new User(0,username,userpassword,true));
        if(ok){
            System.out.println("添加成功！");
        }else {
            System.out.println("用户名已存在！请重新输入！");
            addUser();
            return;
        }
    }

    /**
     * 查询用户选择方法
     */
    public void selectUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.查询所有 2.条件查询");
        String s = scanner.nextLine();
        switch (s){
            case "1":
                selectAll();
                break;
            case "2":
                select();
                break;
            default:
                System.out.println("输入有误，请重写选择！");
                selectUser();
                return;
        }
    }
    /**
     * 查询所有用户方法
     */
    public void selectAll(){
        List<User> list = controller.selectAll();
        listErgodic(list);
    }
    /**
     * 按登陆权限条件查询
     */
    public void select(){
        System.out.println("目前只支持登陆权限查询 0.禁止登陆 1.允许登陆");
        System.out.println("请输入的查询条件[0-1]:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if(s.equals("0")||s.equals("1")){
            List<User> list = controller.select(s);
            listErgodic(list);
        }else {
            System.out.println("输入有误，请重新输入！");
            select();
            return;
        }
    }

    /**
     * 抽取的遍历集合方法
     * @param list
     */
    private void listErgodic(List<User> list) {
        System.out.println("ID\t \t \t 用户名\t \t \t 密码\t \t \t 登陆权限");
        for (User user : list){
            System.out.println(user.toString());
        }
    }
}
