package cn.cyp.web;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author:cyp
 * @date:2019/8/16 15:17
 * @Desc:
 */
@WebServlet(urlPatterns= "/userOutLoginServlet")
public class userOutLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取session对象
        HttpSession session = request.getSession();
        //手动去销毁session对象
        session.invalidate();
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }

}
