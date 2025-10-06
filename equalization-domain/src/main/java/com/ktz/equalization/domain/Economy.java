package com.ktz.equalization.domain;

import com.ktz.equalization.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午8:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Economy extends BaseEntity {

    @Length(min = 1, message = "指标名称不能为空")
    private String indicator;

    @NotNull(message = "地区 id 不能为空")
    private Long regionId;

    private Region region;

    @NotNull(message = "所属领域 id 不能为空")
    private Long fieldId;

    private Field field;

    @NotNull(message = "年份不能为空")
    private String year;

    @NotNull(message = "指标值不能为空")
    private Float value;

}
