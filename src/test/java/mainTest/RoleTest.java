package mainTest;

import cn.pickle.dao.RoleDao;
import cn.pickle.po.Role;
import cn.pickle.service.RoleService;
import cn.pickle.service.impl.RoleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/18 14:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RoleTest {
    @Autowired
    @Qualifier(value = "roleService")
    private RoleServiceImpl roleService;
    @Test
    public void demo(){
        final List<Role> roleList = roleService.findRoleList();

        roleList.forEach(System.out::println);
    }
}
