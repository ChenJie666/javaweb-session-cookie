package com.cj.springweb;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author CJ
 * @date 2021/7/10 10:35
 */
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html; charset=UTF-8");

        // 获取cookies
        Cookie[] cookies = req.getCookies();

        // 遍历请求中携带的cookies
        Long lastVisit = null;
        for (Cookie cookie : cookies) {
            if ("lastVisit".equals(cookie.getName())) {
                lastVisit = Long.parseLong(cookie.getValue());
            }
        }

        // 判断Cookie中的属性是否存在，不存在说明第一次登陆
        PrintWriter out = resp.getWriter();
        if (Objects.isNull(lastVisit)) {
            out.println("第一次访问");
        } else {
            Date date = new Date(lastVisit);
            out.println("上次登陆时间为" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        }

        // 将本次登陆时间写入到Cookie中
        long currentTimeMillis = System.currentTimeMillis();
        Cookie cookie = new Cookie("lastVisit", String.valueOf(currentTimeMillis));
        // 过期时间为-1表示cookie永不过期，设置为0表示删除浏览器端的cookie(cookie马上过期)
        cookie.setMaxAge(-1);
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
