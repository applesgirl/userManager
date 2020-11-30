package cn.cyp.web;

import cn.cyp.bean.User;
import cn.cyp.dao.impl.userDaoImpl;
import cn.cyp.service.impl.userServiceImpl;
import cn.cyp.service.userService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author:cyp
 * @date:2019/8/19 15:12
 * @Desc:
 */
@WebServlet(urlPatterns = "/userRegisterServlet")
public class userRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String img = request.getParameter("img");
        //创建session对象
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute("code");
        //进行判断输入框的内容和验证码是否一致
        if(!code.equalsIgnoreCase(img)) {
            request.setAttribute("imgmsg","验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //进行判断用户名和密码不能为空
        if(user.getUsername()==null||user.getPwd()==null||user.getUsername()==""||user.getPwd()==""){
                 request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
        //进行判断该用户是否存在
        userService userService = new userServiceImpl();
        Boolean flag = userService.registerUser(user);
        if (flag) {
            System.out.println("添加成功");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else {
            System.out.println("添加失败");
        }
    }
}
