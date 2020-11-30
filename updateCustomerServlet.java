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
 * @date:2019/8/16 19:45
 * @Desc:修改操作
 */
@WebServlet(urlPatterns= "/updateCustomerServlet")
public class updateCustomerServlet extends HttpServlet {
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
        customerService updateCustomerService = new customerServiceImpl();
        boolean customer1 = updateCustomerService.editCustomer(customer);
        if (customer1 == true) {
            response.getWriter().print("添加成功");
            request.getRequestDispatcher("/pageBeanServlet").forward(request, response);
        } else {
            response.getWriter().print("添加失败");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String idstr = request.getParameter("id");
        int id=Integer.parseInt(idstr);

        customerService updateCustomerService = new customerServiceImpl();
        Customer customer1 = updateCustomerService.updateCustomer(id);
        //判断是否是需要的id
        if (customer1!=null) {
            request.setAttribute("customer",customer1);
            request.getRequestDispatcher("/updateCustomer.jsp").forward(request,response);
        }
        System.out.println("查询不到");
    }

}
