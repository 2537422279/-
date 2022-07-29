package cn.pickle.dao;

import cn.pickle.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/16 20:18
 */
public interface UserDao {

    /**
     * 查询所有用户
     * @param keywords  可选参数 用户姓名或者登录名关键字
     * @param userListRoleId    可选参数 用户角色
     * @return  用户列表
     */
    @Select({
            "<script>",
            "select u.*,r.roleName from t_user as u,t_role as r",
            "<where>",
            "u.roleId=r.roleId",
            "<if test=\"keywords != null and keywords != ''\">",
            "and (u.userName like CONCAT('%',#{keywords},'%')) or (u.loginName like CONCAT('%',#{keywords},'%'))",
            "</if>",
            "<if test=\"userListRoleId != null and userListRoleId != ''\">",
            "and (u.roleId = #{userListRoleId})",
            "</if>",
            "</where>",
            "order by registerTime desc",
            "</script>"
    })
    List<User> selectUserList(@Param(value = "keywords") String keywords,@Param(value = "userListRoleId") Integer userListRoleId);


    /**
     * 通过账号和密码查询用户
     * @param loginName 账号名
     * @param password  密码
     * @return  用户
     */
    @Select("select * from t_user where loginName=#{loginName} and password=#{password}")
    User findUserByLoginNameAndPassword(@Param(value = "loginName") String loginName,@Param(value = "password") String password);


    /**
     * 通过ID查询用户
     * @param userId 用户ID
     * @return 用户
     */
    @Select("select * from t_user where userId=#{userId}")
    User getUserById(Integer userId);


    /**
     * 通过用户登陆名查询用户
     * @param loginName  用户登陆名
     * @return  用户
     */
    @Select("select * from t_user where loginName=#{loginName}")
    User getUserByLoginName(String loginName);


    /**
     * 添加用户
     * @param user 用户
     * @return 影响行数
     */
    @Insert("insert into t_user(" +
            "userName," +
            "loginName," +
            "password," +
            "tel," +
            "registerTime," +
            "status," +
            "roleId" +
            ")" +
            "values(" +
            "#{userName}," +
            "#{loginName}," +
            "#{password}," +
            "#{tel}," +
            "#{registerTime}," +
            "#{status}," +
            "#{roleId}" +
            ")")
    int addUser(User user);


    /**
     * 更新用户
     * @param user 用户
     * @return 影响行数
     */
    @Update({"<script>" +
            "update t_user" +
            "<set>" +
            "registerTime=#{registerTime}," +
            "status=#{status}," +
            "<if test=\"userName!=null and userName!=''\">" +
            "userName=#{userName}," +
            "</if>" +
            "<if test=\"password!=null and password!=''\">" +
            "password=#{password}," +
            "</if>" +
            "<if test=\"tel!=null and  tel!=''\">" +
            "tel=#{tel}," +
            "</if>" +
            "<if test=\"roleId!=null and roleId!=''\">" +
            "roleId=#{roleId}," +
            "</if>" +
            "</set>" +
            "where userId = #{userId}" +
            "</script>"})
    int updateUser(User user);


    /**
     * 删除User
     * @param userId 用户ID
     * @return 影响行数
     */
    @Delete("delete from t_user where userId=#{userId}")
    int deleteUser(Integer userId);


    /**
     * 设置用户状态
     * @param user 用户
     * @return 影响行数
     */
    @Update("update t_user set status=#{status} where userId=#{userId}")
    int setUserStatus(User user);


}
