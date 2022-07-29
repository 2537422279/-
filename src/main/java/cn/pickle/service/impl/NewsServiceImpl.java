package cn.pickle.service.impl;

import cn.pickle.dao.NewsDao;
import cn.pickle.po.News;
import cn.pickle.service.NewsService;
import cn.pickle.utils.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/29 14:47
 */

/*
 * 新闻Service接口实现类
 */
@Service(value = "newsService")
public class NewsServiceImpl implements NewsService {

    @Resource(name = "newsDao")
    private NewsDao newsDao;


    @Override
    public PageBean<News> findNewsByPage(String keywords, Integer newsListCategoryId, Integer currentPage, Integer pageSize) {
        //获取当前类别信息数量
        final int count = newsDao.getNewsCount(keywords, newsListCategoryId);
        //求总页数
        int totalPage=(int)Math.ceil(count*0.1/pageSize);
        //获取新闻列表
        final List<News> newsList = newsDao.findNewsList(keywords, newsListCategoryId, (currentPage - 1) * pageSize, pageSize);
        //创建PageBean实例pb
        final PageBean<News> pb = new PageBean<>();
        pb.setCount(count);
        if(currentPage==0)
            currentPage=1;
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        pb.setTotalPage(totalPage);
        pb.setList(newsList);
        return pb;
    }

    @Override
    public News getNewsByNewsId(Integer newsId) {
        return newsDao.getNewsByNewsId(newsId);
    }

    @Override
    public int setNewsPublishStatus(News news) {
        return newsDao.setNewsPublishStatus(news);
    }

    @Override
    public int addNews(News news) {
        return newsDao.addNews(news);
    }

    @Override
    public int editNews(News news) {
        return newsDao.updateNews(news);
    }

    @Override
    public int delNews(Integer newsId) {
        return newsDao.delNews(newsId);
    }
}
