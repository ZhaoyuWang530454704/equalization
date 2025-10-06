package com.ktz.equalization.commons.persistence;

import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午2:21
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * 插入单个数据
     * @param entity 待插入数据的实体类对象
     * @return dto 返回结果
     */
    BaseResult insertOne(T entity);

    /**
     * 根据 id 删除数据
     * @param id 待删除数据的 id
     */
    void deleteOne(Long id);

    /**
     * 批量删除数据
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
    T getOne(Long id);

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
     * 分页 + 条件查询
     * @param page 当前页数
     * @param size 每页显示条数
     * @param entity 查询条件
     * @return 分页对象
     */
    PageInfo<T> list(Integer page, Integer size, T entity);
}
