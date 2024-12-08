package com.example.war;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/HelloWorld")
public class WarApplication extends HttpServlet {

    static String PAGE_HEADER = "<html><head><title>helloworld</title></head><body>";

    static String PAGE_FOOTER = "</body></html>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String standaloneValue = System.getProperty("STANDALONE_PARAM");
        String webValue = getServletContext().getInitParameter("WEB_PARAM");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(PAGE_HEADER);
        writer.println("<h1>Hello World!</h1>");
        writer.println("<h2>" + standaloneValue + "</h2>");
        writer.println("<h2>" + webValue + "</h2>");
        writer.println(PAGE_FOOTER);
        writer.close();
    }

}