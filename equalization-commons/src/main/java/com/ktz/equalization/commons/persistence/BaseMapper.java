package com.ktz.equalization.commons.persistence;

import com.ktz.equalization.commons.dto.BaseResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午2:07
 */
public interface BaseMapper<T extends BaseEntity> {

    /**
     * 插入一条数据
     * @param entity 待插入数据的实体类对象
     */
    void insertOne(T entity);

    /**
     * 根据 id 删除一条数据
     * @param id 待删除数据的 id
     */
    void deleteOne(@Param("id") Long id);

    /**
     * 根据 id 数组批量删除数据
     * @param ids 待删除数据的 id 数组
     */
    void deleteMulti(String[] ids);

    /**
     * 更新数据
     * @param entity 待更新数据
     */
    void update(T entity);

    /**
     * 根据 id 查询单个数据
     * @param id 待查询数据的 id
     * @return 实体类对象
     */
    T getOne(@Param("id") Long id);

    /**
     * 查询全部数据
     * @return 实体类对象组成的 List 集合
     */
    List<T> listAll();

    /**
     * 根据条件查询数据
     * @param entity 查询条件
     * @return 符合条件的实体类对象组成的 List 集合
     */
    List<T> list(T entity);

    /**
     * 根据条件查询统计数据数目
     * @param entity 查询条件
     * @return 统计结果数目
     */
    int count(T entity);
}
