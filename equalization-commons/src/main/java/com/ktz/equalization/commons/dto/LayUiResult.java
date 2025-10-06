package com.ktz.equalization.commons.dto;

import com.ktz.equalization.commons.persistence.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/11 上午11:55
 */
@Data
public class LayUiResult<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据总数目
     */
    private Integer count;

    /**
     * 数据
     */
    private List<T> data;

    public static <T> LayUiResult<T> createResult(Integer code, String msg, Integer count, List<T> data) {
        LayUiResult<T> result = new LayUiResult<T>();
        result.setCode(code);
        result.setMsg(msg);
        result.setCount(count);
        result.setData(data);
        return result;
    }
}
