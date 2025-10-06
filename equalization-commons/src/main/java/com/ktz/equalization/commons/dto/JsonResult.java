package com.ktz.equalization.commons.dto;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘世阳
 * @date 2021/2/1 15:41
 * @description:
 */
@Data
public class JsonResult<T> implements Serializable {
    //数据
    private Object[] data;

    public static <T> JsonResult<T> createResult(Object[] data) {
        JsonResult<T> result = new JsonResult<T>();
        result.setData(data);
        return result;
    }
}
