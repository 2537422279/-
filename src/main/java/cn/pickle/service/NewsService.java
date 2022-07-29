package cn.pickle.service;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/29 12:08
 */

import cn.pickle.po.News;
import cn.pickle.utils.PageBean;

/**
 * 新闻Service层接口
 */
public interface NewsService {
    PageBean<News> findNewsByPage(String keywords,Integer newsListCategoryId,
                                  Integer currentPage,Integer pageSize);
    News getNewsByNewsId(Integer newsId);

    int setNewsPublishStatus(News news);

    int addNews(News news);

    int editNews(News news);

    int delNews(Integer newsId);
}
