package com.xiao.test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 需求：
 * 在 MySQL 数据库中创建一个 test_uer 数据表，添加 3 个字段：id, user, password. 并录入几条记录
 * 定义一个 login.html, 里边定义两个请求字段： user, password. 并发送到 loginServlet
 * 再创建一个 LoginServlet(需要继承自 HttpServlet, 并重写其doPost 方法)，在其中获取 user, password.
 * 利用 JDBC 从 test_users 中查询有没有和页面输入的 user, password 对应的记录
 * SELECT count(id) FROM test_user WHERE user = ? AND password = ?
 * 若有，响应：Hello：xxx ，若没有，响应 Sorry：xxx   (xxx 为 user).
 */

/**
 * @author Henery
 * @date 2019/9/10 - 11:22
 */
@WebServlet(name = "Servlet" , urlPatterns = "/hello")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("doGet");

    }
}
