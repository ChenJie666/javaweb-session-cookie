package com.cj.springweb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author CJ
 * @date 2021/7/10 11:38
 */
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // 通过request中的JSESSIONID获取session对象
        HttpSession session = req.getSession();
        // 在session中设置属性
        session.setAttribute("name","zhangsan");


        // 从session中获取属性
        String name = session.getAttribute("name").toString();

        // 获取sessionId，判断session是不是这次请求时创建的
        PrintWriter out = resp.getWriter();
        out.println("sessionId:" + session.getId() + " --- isNew:" + session.isNew() + " --- name:" + name);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
