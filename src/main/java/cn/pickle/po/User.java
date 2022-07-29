package cn.pickle.po;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/16 20:10
 */

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer userId;     //用户ID
    private String userName;    //用户姓名
    private String loginName;   //用户登录名
    private String password;    //登录密码
    private String tel;         //用户联系电话
    private Date registerTime;  //注册或修改用户时间
    private String status;      //用户状态 （1:未启用 2:已启用 3:被禁用）
    private Integer roleId;     //用户对应的角色ID
    private String roleName;    //角色名称（为了方便列表显示角色名，增加此属性）
}
