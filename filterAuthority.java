package cn.cyp.util;

import cn.cyp.bean.User;
import org.omg.CORBA.Request;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:cyp
 * @date:2019/8/26 16:25
 * @Desc:
 */
 @WebFilter(urlPatterns = "/*")
//管理员能操作任意功能，普通用户只能够进行搜索和添加功能，不能够进行修改和删除功能
public class filterAuthority implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI().substring(request.getContextPath().length());

        if ("/login.jsp".equals(path) || "/userLoginServlet".equals(path)
                || "/userRegisterServlet".equals(path) || "/checkCodeServlet".equals(path)|| "/pageBeanServlet".equals(path) || "/register.jsp".equals(path) || "/list.jsp".equals(path) || "/Checkcode".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }
        //进行判断是管理员还是普通用户
        // User user = (User) request.getSession().getAttribute("reuser");
        User user =(User) request.getSession().getAttribute("reuser");
        // System.out.println("user = " + user);
        if ( "admin".equals( user.getUsername() )) {
            filterChain.doFilter(request, response);
        } else {
            //普通用户，只能进行增加和搜索功能
            if ("abc123".equals(user.getUsername())) {
                if ("/pageBeanServlet".equals(path) || "/list.jsp".equals(path) || "/addCustomerServlet".equals(path) || "/addCustomer.jsp".equals(path)) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    request.getRequestDispatcher("/error/failure.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    public void destroy() {
    }
}
