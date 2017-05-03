package com.forum.app;

import com.forum.view.UserView;

/**
 * 主程序类，运行程序入口
 * 调用View层run方法
 * Created by Aongi on 2017/5/3.
 * Version 1.0
 */
public class MainApp {
    public static void main(String[] args) {
        new UserView().run();
    }
}
