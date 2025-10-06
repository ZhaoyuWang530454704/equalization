package com.ktz.equalization.system.service;

import com.ktz.equalization.commons.persistence.BaseService;
import com.ktz.equalization.domain.Policy;
import com.ktz.equalization.system.abstracts.AbstractServiceImpl;

/**
 * @Author 刘世阳
 * @Date 2021/1/10 9:40
 * @Description:
 */
public interface PolicyService extends BaseService<Policy> {

    void save(String key);
}
