package cn.pickle.dao;

import cn.pickle.po.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/16 20:17
 */
public interface RoleDao {
    /**
     * 获取所有角色信息（角色列表）
     * @return 角色列表
     */
    @Select("select roleId,roleName from t_role")
    List<Role> selectRoleList();


}
