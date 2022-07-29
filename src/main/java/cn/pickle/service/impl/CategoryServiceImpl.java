package cn.pickle.service.impl;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/29 12:14
 */

import cn.pickle.dao.CategoryDao;
import cn.pickle.po.Category;
import cn.pickle.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 新闻类别Service接口实现类
 */
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    //注入CategoryDao
    @Resource(name = "categoryDao")
    private CategoryDao categoryDao;

    @Override
    public List<Category> findCategoryList() {
        return categoryDao.selectCategoryList();
    }

    @Override
    public Category findCategoryById(Integer categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

}
