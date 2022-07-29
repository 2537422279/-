package mainTest;

import cn.pickle.dao.UserDao;
import cn.pickle.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/18 21:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void demo1(){
        final List<User> users = userDao.selectUserList(null, null);
        users.forEach(System.out::println);
    }
}
