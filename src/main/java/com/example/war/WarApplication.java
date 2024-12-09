package com.example.war;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//@WebServlet("/HelloWorld")
public class WarApplication extends HttpServlet {

    static String PAGE_HEADER = "<html><head><title>helloworld</title></head><body>";

    static String PAGE_FOOTER = "</body></html>";

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String standaloneValue = System.getProperty("STANDALONE_PARAM");
//        String webValue = getServletContext().getInitParameter("WEB_PARAM");
//
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        writer.println(PAGE_HEADER);
//        writer.println("<h1>Hello World!</h1>");
//        writer.println("<h2>" + standaloneValue + "</h2>");
//        writer.println("<h2>" + webValue + "</h2>");
//        writer.println(PAGE_FOOTER);
//        writer.close();
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 資料庫連接
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 查找 JNDI 資料來源
            Context initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:jboss/datasources/PostgresDS");
            connection = ds.getConnection();

            // 執行 SQL 查詢
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM test.users");

            // 輸出結果
            PrintWriter out = response.getWriter();
            while (rs.next()) {
                out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 關閉資源
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}