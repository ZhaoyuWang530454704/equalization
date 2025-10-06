package com.ktz.equalization.system.service;

import com.ktz.equalization.commons.persistence.BaseService;
import com.ktz.equalization.domain.Economy;

import java.util.List;

/**
 * @Author 刘世阳
 * @Date 2021/1/10 9:45
 * @Description:
 */
public interface EconomyService extends BaseService<Economy> {

    void insertMulti(List<Economy> economies);
}
