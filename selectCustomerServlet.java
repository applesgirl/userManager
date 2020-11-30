package cn.cyp.web;

import cn.cyp.bean.Customer;
import cn.cyp.service.customerService;
import cn.cyp.service.impl.customerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:cyp
 * @date:2019/8/14 19:17
 * @Desc:
 */
@WebServlet(urlPatterns= "/selectCustomerServlet")
public class selectCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      customerService selectCustomerService =new customerServiceImpl();
        //使用list集合来接收从service传过来的值
        List<Customer> list= selectCustomerService.selectAllCustomer();

      request.setAttribute("list",list);
      request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
}
