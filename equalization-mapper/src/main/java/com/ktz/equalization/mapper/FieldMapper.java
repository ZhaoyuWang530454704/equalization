package com.ktz.equalization.mapper;

import com.ktz.equalization.commons.persistence.BaseMapper;
import com.ktz.equalization.domain.Field;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author 刘世阳
 * @Date 2021/1/9 9:52
 * @Description:
 */
@Mapper
@Repository
public interface FieldMapper extends BaseMapper<Field> {

    Long getIdByName(@Param("name") String name);

}
