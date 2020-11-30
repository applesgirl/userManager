package cn.cyp.web;

import cn.cyp.bean.Customer;
import cn.cyp.service.customerService;
import cn.cyp.service.impl.customerServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author:cyp
 * @date:2019/8/16 20:38
 * @Desc:添加用户信息的页面
 */
@WebServlet(urlPatterns = "/addCustomerServlet")
public class addCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过map获取页面上输入框的所有内容
        response.setContentType("text/html;charset=utf-8");
        //设置post请求字符集
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Customer customer = new Customer();
        //进行封装实体类
        try {
            BeanUtils.populate(customer, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        customerService addCustomerService = new customerServiceImpl();
        Boolean customer1 = addCustomerService.addCustomer(customer);
        if (customer1 == true) {
            response.getWriter().print("添加成功");
            request.getRequestDispatcher("/pageBeanServlet").forward(request, response);
        } else {
            response.getWriter().print("添加失败");
           request.setAttribute("msg","邮箱已存在");
            //    当邮箱存在的时候，进行回显到添加界面
            request.setAttribute("old", customer);
            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
        }
    }
}
