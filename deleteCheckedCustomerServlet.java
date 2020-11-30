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
 * @date:2019/8/20 18:08
 * @Desc:
 */
@WebServlet(urlPatterns = "/deleteCheckedCustomerServlet")
public class deleteCheckedCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] items = request.getParameterValues("items");
        customerService deleteCustomerService = new customerServiceImpl();
        deleteCustomerService.deleteCheckedCustomer(items);
        response.sendRedirect(request.getContextPath()+"/pageBeanServlet");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
