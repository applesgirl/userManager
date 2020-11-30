package cn.cyp.web;

import cn.cyp.service.customerService;
import cn.cyp.service.impl.customerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:cyp
 * @date:2019/8/17 14:47
 * @Desc:
 */
@WebServlet(urlPatterns = "/deleteCustomerServlet")
public class deleteCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前台传来的id值
        String idstr = request.getParameter("id");
        int id=Integer.parseInt(idstr);

        customerService deleteCustomerService = new customerServiceImpl();
        deleteCustomerService.deleteCustomer(id);
        //删除成功
        response.sendRedirect(request.getContextPath()+"/pageBeanServlet");
            // request.getRequestDispatcher("/selectCustomerServlet").forward(request,response);
            System.out.println("删除成功");

        }
    }
