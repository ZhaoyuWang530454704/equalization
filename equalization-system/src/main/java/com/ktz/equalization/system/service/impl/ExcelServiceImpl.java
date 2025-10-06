package com.ktz.equalization.system.service.impl;

import com.ktz.equalization.domain.Economy;
import com.ktz.equalization.mapper.FieldMapper;
import com.ktz.equalization.mapper.RegionMapper;
import com.ktz.equalization.system.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/18 下午2:24
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Economy> parseExcel(String path, String field) {
        List<Economy> economies = new ArrayList<>();
        InputStream is = null;
        try {
            Long fieldId = fieldMapper.getIdByName(field);
            is = new FileInputStream(path);
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            for (int i = 1; i < rows; i += 4) {
                HSSFCell indicatorCell = sheet.getRow(i).getCell(0);
                String indicator = indicatorCell.getStringCellValue();
                for (int j = 0; j < 4; j++) {
                    HSSFCell regionCell = sheet.getRow(i + j).getCell(1);
                    String region = regionCell.getStringCellValue();
                    Long regionId = regionMapper.getIdByName(region);
                    for (int k = 0; k < cells - 2; k++) {
                        HSSFCell yearCell = sheet.getRow(0).getCell(2 + k);
                        HSSFCell valueCell = sheet.getRow(i + j).getCell(2 + k);
                        String year = yearCell.getStringCellValue();
                        Float value = (float) valueCell.getNumericCellValue();
                        Economy economy = new Economy();
                        economy.setIndicator(indicator);
                        economy.setRegionId(regionId);
                        economy.setFieldId(fieldId);
                        economy.setYear(year);
                        economy.setValue(value);
                        economy.setCreateTime(LocalDateTime.now());
                        economy.setUpdateTime(LocalDateTime.now());
                        economies.add(economy);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return economies;
    }
}
