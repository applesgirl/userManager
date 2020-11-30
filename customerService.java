package cn.cyp.service;

import cn.cyp.bean.Customer;
import cn.cyp.bean.pageBean;

import java.util.List;
import java.util.Map;

/**
 * @Author:cyp
 * @date:2019/8/14 19:12
 * @Desc:
 */
public interface customerService {
    //查询所有用户
    List<Customer> selectAllCustomer();
   // 增加用户
    Boolean addCustomer(Customer customer);
   // 删除用户
   void deleteCustomer(int id);
    //删除选中的用户
    void deleteCheckedCustomer(String[] items);
    //修改用户
    //判断用户id是不是选择的id
    Customer updateCustomer(int id);
    //进行编辑用户的信息
    boolean editCustomer(Customer customer);

    //关于分页的方法
    pageBean findCustomerPage(pageBean pageBean, Map<String, String[]> map);
}
