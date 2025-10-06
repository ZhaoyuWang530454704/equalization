package com.ktz.equalization.mapper;

import com.ktz.equalization.commons.persistence.BaseMapper;
import com.ktz.equalization.domain.Policy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 刘世阳
 * @Date 2021/1/9 10:08
 * @Description:
 */
@Mapper
@Repository
public interface PolicyMapper extends BaseMapper<Policy> {

    void insertMulti(List<Policy> policies);
}
