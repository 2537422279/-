package cn.pickle.dao;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/29 10:54
 */

import cn.pickle.po.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 新闻DAO层接口
 */
public interface NewsDao {
    //获取当前类别新闻数量

    @Select({"<script>" +
            "select count(*) from ssm.t_news as n " +
            "<where>" +
            "<if test=\"keywords!=null and keywords!=''\">" +
            "and (n.title like CONCAT('%',#{keywords},'%') or n.keywords like CONCAT('%',#{keywords},'%'))" +
            "</if>" +
            "<if test=\"newsListCategoryId!=null and newsListCategoryId!=''\">" +
            "and (n.categoryId=#{newsListCategoryId))" +
            "</if>" +
            "</where>" +
            "</script>"})
    int getNewsCount(@Param("keywords") String keywords,
                     @Param("newsListCategoryId") Integer newsListCategoryId);


    //获取当前类别新闻列表
    @Select({"<script>" +
            "select n.*,c.categoryName from ssm.t_news as n,ssm.t_category as c" +
            "<where>" +
            "n.categoryId=c.categoryId" +
            "<if test=\"keywords!=null and keywords!=''\">" +
            "and (n.title like CONCAT('%',#{keywords},'%') or n.keywords like CONCAT('%',#{keywords},'%'))" +
            "</if>" +
            "<if test=\"newsListCategoryId!=null and newsListCategoryId!=''\">" +
            "and (n.categoryId=#{newsListCategoryId))" +
            "</if>" +
            "</where>" +
            "order by publishTime desc " +
            "limit #{startRows},#{pageSize}" +
            "</script>"})
    List<News> findNewsList(@Param("keywords") String keywords,
                            @Param("newsListCategoryId") Integer newsListCategoryId,
                            @Param("startRows") Integer startRows,
                            @Param("pageSize") Integer pageSize);


    //根据新闻ID获取新闻
    @Select("select n.*,c.categoryName from ssm.t_news as n,ssm.t_category as c where newId=#{newId} and n.categoryId=c.categoryId ")
    News getNewsByNewsId(Integer newId);

    //添加新闻
    @Insert("insert into ssm.t_news(" +
            "title," +
            "contentTitle," +
            "content," +
            "contentAbstract," +
            "keywords," +
            "author," +
            "publishTime," +
            "publishStatus," +
            "categoryId" +
            ")" +
            "values(" +
            "#{title}," +
            "#{contentTitle}," +
            "#{content}," +
            "#{contentAbstract}," +
            "#{keywords}," +
            "#{author}," +
            "#{publishTime}," +
            "#{publishStatus}," +
            "#{categoryId}" +
            ")")
    int addNews(News news);

    //更新新闻
    @Update({"<script>" +
            "update ssm.t_news" +
            "<set>" +
            "title=#{title}," +
            "contentTitle=#{contentTitle}," +
            "content=#{content}," +
            "contentAbstract=#{contentAbstract}," +
            "keywords=#{keywords}," +
            "author=#{author}," +
            "publishTime=#{publishTime}," +
            "publishStatus=#{publishStatus}," +
            "categoryId=#{categoryId}" +
            "</set>" +
            "where newId=#{newId}" +
            "</script>"})
    int updateNews(News news);

    //设置新闻状态（‘1’：发布；‘2’：撤稿）
    @Update("update ssm.t_news set publishStatus=#{publishStatus} where newId=#{newId}")
    int setNewsPublishStatus(News news);

    //删除新闻
    @Delete("delete from ssm.t_news where newId=#{newId}")
    int delNews(Integer newId);


}
