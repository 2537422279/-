package cn.pickle.service;

import cn.pickle.po.Role;

import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/17 17:21
 */
public interface RoleService {

    List<Role> findRoleList();
}
