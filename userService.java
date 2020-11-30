package cn.cyp.service;

import cn.cyp.bean.User;

/**
 * @Author:cyp
 * @date:2019/8/15 22:05
 * @Desc:
 */
public interface userService {
    User userLogin(User user);
    Boolean registerUser(User user);
}
