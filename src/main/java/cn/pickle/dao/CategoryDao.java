package cn.pickle.dao;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/29 10:48
 */

import cn.pickle.po.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 新闻类别DAO接口
 */
public interface CategoryDao {
    //查询所有新闻类别

    @Select("select * from ssm.t_category")
    List<Category> selectCategoryList();

    //根据新闻类别ID查询新闻类别
    @Select("select * from ssm.t_category where categoryId=#{categoryId}")
    Category getCategoryById(Integer categoryId);
}
