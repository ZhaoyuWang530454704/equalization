package com.ktz.equalization.system.service;

import com.ktz.equalization.domain.Economy;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/18 下午2:23
 */
public interface ExcelService {

    List<Economy> parseExcel(String path, String field);
}
