package cn.pickle.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/29 10:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private Integer currentPage;    //当前页码
    private Integer pageSize;       //每一页显示新闻条数
    private Integer count;          //新闻数量
    private Integer totalPage;      //总页数
    private List<T> list;           //当前页的新闻集合列表
}
