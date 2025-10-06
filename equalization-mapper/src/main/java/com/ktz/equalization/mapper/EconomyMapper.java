package com.ktz.equalization.mapper;

import com.ktz.equalization.commons.persistence.BaseMapper;
import com.ktz.equalization.domain.Economy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 刘世阳
 * @Date 2021/1/9 10:24
 * @Description:
 */
@Mapper
@Repository
public interface EconomyMapper extends BaseMapper<Economy> {

    void insertMulti(List<Economy> economies);
}
