package cn.pickle.controller;

import cn.pickle.po.Role;
import cn.pickle.po.User;
import cn.pickle.service.RoleService;
import cn.pickle.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/18 14:35
 */
@Controller
public class UserController {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "roleService")
    private RoleService roleService;


    /**
     * 查询所有状态的用户集合
     * @param keywords 关键字
     * @param userListRoleId  角色
     * @param model 返回数据
     * @return 返回视图
     */
    @RequestMapping(value = "/findUserList")
    public String findUserList(String keywords, Integer userListRoleId, Model model){

        final List<Role> roleList = roleService.findRoleList();
        model.addAttribute("roleList",roleList);
        final List<User> userList = userService.findUserList(keywords, userListRoleId);
        model.addAttribute("userList",userList);
        model.addAttribute("keywords",keywords);
        model.addAttribute("userListRoleId",userListRoleId);
        return "user/user_list";
    }


    /**
     * 跳转添加用户页面
     * @return 视图
     */
    @RequestMapping(value = "/toAddUser")
    public String toAddUser(Model model){
        final List<Role> roleList = roleService.findRoleList();
        model.addAttribute("roleList",roleList);
        return "user/add_user";
    }


    /**
     * 添加用户
     * @param user 用户
     * @param model 返回信息
     * @return 视图
     */
    @RequestMapping(value = "/addUser")
    public String addUser(User user,Model model){
        final List<Role> roleList = roleService.findRoleList();
        model.addAttribute("roleList",roleList);
        model.addAttribute("user",user);

        //检查账号是否已经存在
        final User userByLoginName = userService.getUserByLoginName(user.getLoginName());
        if(userByLoginName!=null){
            //登录账号存在，重新转回添加用户页面
            model.addAttribute("checkUserLoginNameMsg","登录账号已经存在");
            return "user/add_user";
        }else{
            //登录账号可用
            Date date = new Date();
            user.setRegisterTime(date);

            //默认设置用户为启用状态”2“
            user.setStatus("2");

            final int row = userService.addUser(user);
            if(row>0){
                //添加成功，转向用户列表页面
                return "redirect:findUserList";
            }else{
                //添加失败，重新转回添加用户页面
                return "user/add_user";
            }
        }
    }

    /**
     * 转向修改用户页面
     */
    @RequestMapping(value = "/toEditUser")
    public String toEditUser(Integer userId,Model  model){

        final User user = userService.getUserByUserId(userId);
        if(user != null){
            model.addAttribute("user",user);
            final List<Role> roleList = roleService.findRoleList();
            model.addAttribute("roleList",roleList);
            return "user/edit_user";
        }else{
            return "redirect:findUserList";
        }
    }


    /**
      * 修改用户
     */
    @RequestMapping(value = "/editUser",method= RequestMethod.POST)
    public String editUser(User user,Model model){
        final Date date = new Date();
        user.setRegisterTime(date);
        //默认设置用户为启用状态”2“
        user.setStatus("2");
        final int row = userService.editUser(user);
        if(row>0){
            //修改成功，转向用户列表页面
            return "redirect:findUserList";
        }else{
            //修改失败
            final List<Role> roleList = roleService.findRoleList();
            model.addAttribute("roleList",roleList);
            model.addAttribute("user",user);
            //转回修改用户页面
            return "user/edit_user";
        }
    }


    /**
     * 删除用户（前台通过Ajax方法调用此方法）
     */
    @ResponseBody
    @RequestMapping(value = "/delUser",method=RequestMethod.POST)
    public User delete(@RequestBody User user,Model model) {
        final int row = userService.delUser(user.getUserId());
        if (row <= 0) {
            //此处设置userId为0，只是作为操作失败的标志
            user.setUserId(0);
        }
        return user;
    }


    /**
     * 禁用用户（更新status字段值为“3”，前台页面中通过jQuery Ajax方式调用此方法）
     * @param user 对象
     * @param model 返回数据
     * @return 返回json
     */
    @ResponseBody
    @RequestMapping(value = "/disableUser")
    public User disableUser(@RequestBody User user,Model model){
        final int rows = userService.setUserStatus(user);
        if(rows<=0){
            //此处设置设置userId为0，只是作为操作失败的标记用
            user.setUserId(0);
        }
        return user;
    }


    /**
     * 禁用
     * @param user
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/enableUser")
    public User enableUser(@RequestBody User user,Model model){
        final int rows = userService.setUserStatus(user);
        if(rows<=0){
            user.setUserId(0);
        }
        return user;
    }

    /**
     * 登录
     */

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String loginName, String password, Model model, HttpSession session){
        //通过用户名和密码查询用户
        final User user = userService.findUser(loginName, password);
        if(user!=null){
            if(user.getStatus().equals("2")){
                //用户被启用，允许登录后台
                session.setAttribute("login_user",user);
                return "../../main";
            }else{
                //账号未启用或被禁用，不允许登录后台
                model.addAttribute("msg","账号未启用或被禁用，请联系管理员！");
                return "../../login";
            }
        }
        //账号或密码错误，不允许登录后台
        model.addAttribute("msg","账号或者密码错误，请重新登录");
        return "../../login";
    }

    /**
     * 退出登录
     * @param session 存储登录用户信息
     * @return 返回登录页面
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        //清空session
        session.invalidate();
        return "../../login";
    }
}
