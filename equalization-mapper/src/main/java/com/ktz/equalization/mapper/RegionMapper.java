package com.ktz.equalization.mapper;

import com.ktz.equalization.commons.persistence.BaseMapper;
import com.ktz.equalization.domain.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author 刘世阳
 * @Date 2021/1/9 10:00
 * @Description:
 */
@Mapper
@Repository
public interface RegionMapper extends BaseMapper<Region> {

    Long getIdByName(@Param("name") String name);
}
