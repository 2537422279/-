package cn.pickle.service.impl;

import cn.pickle.dao.UserDao;
import cn.pickle.po.User;
import cn.pickle.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/18 14:30
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public List<User> findUserList(String keywords, Integer userListRoleId) {
        return userDao.selectUserList(keywords, userListRoleId);
    }

    @Override
    public User findUser(String loginName, String password) {
        return userDao.findUserByLoginNameAndPassword(loginName,password);
    }

    @Override
    public User getUserByUserId(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return userDao.getUserByLoginName(loginName);
    }

    @Override
    public int editUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int delUser(Integer userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public int setUserStatus(User user) {
        return userDao.setUserStatus(user);
    }
}
