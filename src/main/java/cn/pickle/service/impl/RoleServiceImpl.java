package cn.pickle.service.impl;

import cn.pickle.dao.RoleDao;
import cn.pickle.po.Role;
import cn.pickle.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/18 11:29
 */
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public List<Role> findRoleList() {
        return roleDao.selectRoleList();
    }
}
