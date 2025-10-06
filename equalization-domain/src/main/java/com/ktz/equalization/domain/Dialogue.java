package com.ktz.equalization.domain;

import com.ktz.equalization.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午8:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Dialogue extends BaseEntity {

    @Length(min = 1, message = "问题内容不能为空")
    private String question;

    @Length(min = 1, message = "答复结果不能为空")
    private String answer;
}
