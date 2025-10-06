package com.ktz.equalization.domain;

import com.ktz.equalization.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午8:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Policy extends BaseEntity {

    @Length(min = 1, message = "政策标题不能为空")
    private String title;

    @Length(min = 1, message = "政策发布者不能为空")
    private String author;

    @Length(min = 1, message = "发布级别不能为空")
    private String publish;

    @Length(min = 10, message = "政策链接不能为空")
    private String url;

    @Length(min = 6, message = "政策发布日期不能为空")
    private String publishTime;
}
