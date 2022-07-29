package cn.pickle.service;

import cn.pickle.po.User;

import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/17 17:22
 */
public interface UserService {
    List<User> findUserList(String keywords,Integer userListRoleId);
    User findUser(String loginName,String password);
    User getUserByUserId(Integer userId);
    User getUserByLoginName(String loginName);
    int editUser(User user);
    int addUser(User user);
    int delUser(Integer userId);
    int setUserStatus(User user);
}
