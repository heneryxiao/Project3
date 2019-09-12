package com.xiao.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * @author Henery
 * @date 2019/9/11 - 14:18
 */

/**
 * 需求：
 * 在 MySQL 数据库中创建一个 test_uer 数据表，添加 3 个字段：id, user, password. 并录入几条记录
 * 定义一个 login.html, 里边定义两个请求字段： user, password. 并发送到 loginServlet
 * 再创建一个 LoginServlet(需要继承自 HttpServlet, 并重写其doPost 方法)，在其中获取 user, password.
 * 利用 JDBC 从 test_users 中查询有没有和页面输入的 user, password 对应的记录
 * SELECT count(id) FROM test_user WHERE user = ? AND password = ?
 * 若有，响应：Hello：xxx ，若没有，响应 Sorry：xxx   (xxx 为 user).
 */
//（配置和映射这个 Servlet，视频是在Web.xml配置的，这种比较简便）
@WebServlet(name = "loginServlet", urlPatterns = "/loginServlet")

public class loginServlet extends HttpServlet {
    //(重写 doPost 方法)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
        //(获取页面输入的 用户名 和 密码)
        String username = request.getParameter("user");
        String password = request.getParameter("password");

        //（准备数据库连接的所需要的数据（都是之前 JDBC 的内容））
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //(反射，加载数据库驱动程序（不用生成对象，直接实现类中的注册驱动的静态代码块）)
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String password2 = "root";
            String sql = "SELECT count(id) FROM users WHERE username = ? AND password = ?";

            //(连接)
            connection = DriverManager.getConnection(url, user, password2);
            //(处理 SQL 语句)
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println(username + "-----" + username);
            System.out.println(preparedStatement);
            //(获取返回结果)
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);

            //(判读是否有查询结果，并输出)
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    System.out.println("Hello：" + username);
                } else {
                    System.out.println("Sorry：" + username);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //（关闭）
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
