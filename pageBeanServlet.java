package cn.cyp.web;
import cn.cyp.bean.pageBean;
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
 * @date:2019/8/22 9:34
 * @Desc:
 */
@WebServlet(urlPatterns = "/pageBeanServlet")
public class pageBeanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //解决乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;  charset=utf-8");
        //条件查询，获取前台传过来的pageNum，name，sex,age
        Map<String, String[]> map=request.getParameterMap();
        //实例化一个pageBean变量
        pageBean pageBean=new pageBean();
        //    使用BeanUtil封装实体类
        try {
            BeanUtils.populate(pageBean,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //    注入业务层
        customerService customerService=new customerServiceImpl();
        pageBean pageBean1=customerService.findCustomerPage(pageBean,map);
        //将pageBean的值传到浏览器
        request.setAttribute("pageBean",pageBean1);
        //实现输入框的值回显的功能
        request.setAttribute("map",map);
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
