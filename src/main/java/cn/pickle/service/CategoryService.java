package cn.pickle.service;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/29 12:00
 */

import cn.pickle.po.Category;

import java.util.List;

/**
 * 新闻类别Service层接口
 */
public interface CategoryService {
    List<Category> findCategoryList();
    Category findCategoryById(Integer categoryId);

}
