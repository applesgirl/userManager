package cn.cyp.dao;

import cn.cyp.bean.User;

/**
 * @Author:cyp
 * @date:2019/8/16 8:20
 * @Desc:
 */
public interface userDao {

    User userLogin(User user);
    Boolean registerUser(User user);
    User registerOk(String username);
}
