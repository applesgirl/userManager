package cn.cyp.dao;

import cn.cyp.bean.Customer;
import cn.cyp.bean.pageBean;

import java.util.List;
import java.util.Map;

/**
 * @Author:cyp
 * @date:2019/8/14 19:54
 * @Desc:
 */
public interface customerDao {
    //查询所有用户
    List<Customer> selectAllCustomer();
    //进行添加判断邮箱
    Customer ckEmailName(String email);
    //增加用户
    Boolean addCustomer(Customer customer);
    // 删除用户
    void deleteCustomer(int id);
    //修改
    //修改数据
    Customer updateCustomer(int id);
    //编辑数据
    Boolean editeCustomer(Customer customer);
    //显示查询的总共有多少条
    int findTotalCounts(Map<String, String[]> map);
    //查询每页显示的数据是什么
    List<Customer> findTotalCustomers(pageBean pageBean, Map<String, String[]> map);

}
