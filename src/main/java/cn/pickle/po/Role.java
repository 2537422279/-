package cn.pickle.po;

import lombok.Data;

import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/16 20:01
 */
@Data
public class Role {
    private Integer roleId;     //角色ID
    private String roleName;    //角色名称
    private List<User> userList;//对应角色用户列表
}
