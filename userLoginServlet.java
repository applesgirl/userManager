package cn.cyp.web;

import cn.cyp.bean.User;
import cn.cyp.service.impl.userServiceImpl;
import cn.cyp.service.userService;
import cn.cyp.util.MD5Utils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author:cyp
 * @date:2019/8/15 21:06
 * @Desc: 查询所有的人员的信息的dao层
 */
@WebServlet(urlPatterns = "/userLoginServlet")
public class userLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        //获取用户输入的验证码
        String img = request.getParameter("img");
        //获取服务器验证码的内容，session
        HttpSession session = request.getSession();

        //获取登陆框的内容
        String imgsession =(String) session.getAttribute("CHECKCODE_SERVER");
        //判断不匹配的情况
        if(!img.equalsIgnoreCase(imgsession)){
            request.setAttribute("imgmsg","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //再进行获取从前台输入的内容
        Map<String, String[]> map = request.getParameterMap();
        User user =new User();
        //将从登陆框中获取的内容通过传参传到后面，进行判断
        try {
            //进行MD5解码
            user.setPwd(MD5Utils.getPWD(user.getPwd()));
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userService userLoginService =new userServiceImpl();
        User adminService1 = userLoginService.userLogin(user);
        if(adminService1 != null){
            //表示成功之后将用户名记住
            // Cookie cookie=new Cookie("name",username);
            // //设置生存时间
            // cookie.setMaxAge(60*60);
            // //响应给浏览器
            // response.addCookie(cookie);
            //创建ssession对象
            HttpSession session1 = request.getSession();
            session1.setAttribute("reuser",adminService1);

            System.out.println("adminService1 = " + adminService1);
            response.getWriter().print("登陆成功");
            response.sendRedirect(request.getContextPath()+"/pageBeanServlet");
        }else{
           request.getRequestDispatcher("/login.jsp").forward(request,response);
            response.getWriter().print("登录失败");

        }

    }
}
