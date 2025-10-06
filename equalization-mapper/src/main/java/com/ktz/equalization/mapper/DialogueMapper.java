package com.ktz.equalization.mapper;

import com.ktz.equalization.commons.persistence.BaseMapper;
import com.ktz.equalization.domain.Dialogue;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 刘世阳
 * @date 2021/1/5 18:59
 */
@Mapper
@Repository
public interface DialogueMapper extends BaseMapper<Dialogue> {

}
