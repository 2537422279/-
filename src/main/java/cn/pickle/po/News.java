package cn.pickle.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/29 10:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private Integer newId;      //新闻Id
    private String title;       //新闻标题（用于新闻列表页）
    private String contentTitle;//新闻内容页标题（用于新闻内容页）
    private String content;     //新闻内容
    private String contentAbstract;//内容摘要
    private String keywords;    //新闻关键词
    private String author;      //作者或来源
    private Date publishTime;   //发布时间
    private Integer clicks;     //点击率
    private String publishStatus;//发布状态（‘1’：发布；‘2’：撤稿）
    private Integer categoryId; //新闻类别ID
    private String categoryName;//新闻类别名称（为了方便新闻列表页展示）
    private Integer userId;
}
