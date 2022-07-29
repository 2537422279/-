package cn.pickle.controller;

import cn.pickle.po.Category;
import cn.pickle.po.News;
import cn.pickle.service.CategoryService;
import cn.pickle.service.NewsService;
import cn.pickle.utils.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/29 15:04
 */

/*
 * 新闻控制类
 */
@Controller
public class NewsController {
    //依赖注入
    @Resource(name = "newsService")
    private NewsService newsService;
    @Resource(name = "categoryService")
    private CategoryService categoryService;

    //查询新闻分页
    @RequestMapping(value = "/findNewsByPage")
    public String findNewsByPage(String keywords, Integer newsListCategoryId,
                                 @RequestParam(defaultValue = "1") Integer currentPage,
                                 @RequestParam(defaultValue = "10") Integer pageSize, Model model){
        //获取角色列表
        final List<Category> categoryList = categoryService.findCategoryList();
        model.addAttribute("categoryList",categoryList);
        //获取用户PageBean实例
        final PageBean<News> pb = newsService.findNewsByPage(keywords, newsListCategoryId, currentPage, pageSize);
        //想model添加对象属性，用于页面显示
        model.addAttribute("pb",pb);
        model.addAttribute("keywords",keywords);
        model.addAttribute("newsListCategoryId",newsListCategoryId);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pageSize",pageSize);
        return "news/news_list";
    }

    //设置新闻状态 （publishStatus：‘1’：发布，‘2’：撤稿）
    @RequestMapping(value = "/setNewsPublishStatus")
    @ResponseBody
    public News setNewsPublishStatus(@RequestBody News news,Model model){
        final int rows = newsService.setNewsPublishStatus(news);
        if(rows<=0){
            news.setNewId(0);
        }
        return news;
    }

    //转向添加新闻

    @RequestMapping(value = "/toAddNews")
    public String toAddNews(Model model){
        final List<Category> categoryList = categoryService.findCategoryList();
        //用于下拉列表新闻类型选择
        model.addAttribute("categoryList",categoryList);
        return "news/add_news";
    }

    //添加新闻

    @RequestMapping(value = "/addNews",method= RequestMethod.POST)
    public String addNews(News news,Model model){
        Date date = new Date();
        news.setPublishTime(date);
        news.setPublishStatus("1");//默认设置新闻为已发布状态
        final int rows = newsService.addNews(news);
        if(rows>0){
            return "redirect:findNewsByPage";
        }else{
            final List<Category> categoryList = categoryService.findCategoryList();
            model.addAttribute("categoryList",categoryList);
            model.addAttribute("news",news);
            //添加失败,转回添加新闻页面
            return "news/add_news";
        }
    }

    //修改新闻

    @RequestMapping(value = "/editNews",method=RequestMethod.POST)
    public String editNews(News news,Model model){
        Date date=new Date();
        news.setPublishTime(date);
        news.setPublishStatus("1");//默认设置新闻为已发布状态
        final int rows = newsService.editNews(news);
        if(rows>0){
            return "redirect:findNewsByPage";
        }else{
            final List<Category> categoryList = categoryService.findCategoryList();
            model.addAttribute("categoryList",categoryList);
            model.addAttribute("news",news);
            return "news/edit_news";
        }
    }

    //转向修改新闻页面

    @RequestMapping(value = "/toEditNews")
    public String toEditNews(Integer newId,Model model){
        final News news = newsService.getNewsByNewsId(newId);
        if(news!=null){
            model.addAttribute("news",news);
            final List<Category> categoryList = categoryService.findCategoryList();
            model.addAttribute("categoryList",categoryList);
        }
        return "news/edit_news";
    }

    //删除新闻

    @RequestMapping(value = "/delNews")
    @ResponseBody
    public News delNews(@RequestBody News news,Model model){
        final int rows = newsService.delNews(news.getNewId());
        if(rows<=0){
            news.setNewId(0);
        }
        return news;
    }

    //根据新闻类别ID查询新闻分页（用于前台首页）

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response,String keywords,Integer newsListCategoryId,
                        @RequestParam(defaultValue = "1") Integer currentPage,
                        @RequestParam(defaultValue = "10")Integer pageSize,
                        Model model){
        final PageBean<News> pb1 = newsService.findNewsByPage(keywords, 1, currentPage, pageSize);
        final PageBean<News> pb2 = newsService.findNewsByPage(keywords, 2, currentPage, pageSize);
        model.addAttribute("pb1",pb1);
        model.addAttribute("pb2",pb2);
        return "../../first";

    }


    //根据新闻类别ID查询新闻分页（用于前台新闻列表页）

    @RequestMapping(value = "/findNewsByCategoryIdPage")
    public String findNewsByCategoryIdPage(HttpServletRequest request,HttpServletResponse response,String keywords,
                                           Integer newsListCategoryId,
                                           @RequestParam(defaultValue = "1")Integer currentPage,
                                           @RequestParam(defaultValue = "1") Integer pageSize,Model model){
        final Category category = categoryService.findCategoryById(newsListCategoryId);
        model.addAttribute("category",category);
        final PageBean<News> pb = newsService.findNewsByPage(keywords, newsListCategoryId, currentPage, pageSize);
        model.addAttribute("pb",pb);
        model.addAttribute("newsListCategoryId",newsListCategoryId);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pageSize",pageSize);
        return "../../list";
    }

    //查询新闻（用于前台新闻内容页）
    @RequestMapping(value = "/findFrontNewsByNewsId")
    public String findFrontNewsByNewsId(Integer newsId,Model model){
        final News news = newsService.getNewsByNewsId(newsId);
        if(news!=null){
            model.addAttribute("news",news);
        }
        return "../../detail";
    }

}
