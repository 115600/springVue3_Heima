package org.wineky.service;

import org.wineky.pojo.User;

public interface UserService {




    //根据用户名查询用户
    User findByUserName(String username);
    //注册
    void register(String username, String password);
    // 更新用户
    void update(User user);

    //更新用户头像
    void updateAvatar(String avatarUrl);

    //更细用户密码
    void updatePwd(String newPwd);
}
