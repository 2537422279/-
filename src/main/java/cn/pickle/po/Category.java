package cn.pickle.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pickle
 * @version V1.0
 * @date 2022/7/29 10:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer categoryId;     //新闻类别Id
    private String categoryName;    //新闻类别名称
}
