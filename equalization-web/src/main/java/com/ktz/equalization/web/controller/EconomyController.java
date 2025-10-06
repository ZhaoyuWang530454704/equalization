package com.ktz.equalization.web.controller;

import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.dto.LayUiResult;
import com.ktz.equalization.domain.Economy;
import com.ktz.equalization.system.service.EconomyService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
/**
 * @Author 刘世阳
 * @Date 2021/1/14 17:56
 * @Description:
 */
@Controller
@RequestMapping("/economy")
public class EconomyController {

    @Autowired
    EconomyService economyService;

    @GetMapping("/view")
    public String view() {
        return "economics";
    }

    @GetMapping("/add")
    public String add() {
        return "upload";
    }

    @GetMapping("/economyAdd")
    public String economyAdd(){
        return "economicAdd";
    }

    @GetMapping("/list")
    @ResponseBody
    public LayUiResult<Economy> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                        @RequestParam(name = "limit", defaultValue = "10") Integer limit,
                                        Economy economy) {
        PageInfo<Economy> pageInfo = economyService.list(page - 1, limit, economy);
        return LayUiResult.createResult(0, "ok", (int) pageInfo.getTotal(), pageInfo.getList());
    }

    @DeleteMapping("/deleteMulti")
    @ResponseBody
    public BaseResult deleteMulti(@RequestBody String ids[]) {
        economyService.deleteMulti(ids);
        return BaseResult.success();
    }

    @DeleteMapping("/deleteOne/{id}")
    @ResponseBody
    public BaseResult deleteOne(@PathVariable("id") Long id) {
        economyService.deleteOne(id);
        return BaseResult.success();
    }
    @PostMapping("/upload")
    @ResponseBody
    public BaseResult upload(@RequestParam("file") MultipartFile file) throws IOException, InvalidFormatException {
        System.out.println(file);
        InputStream inputStream = file.getInputStream();
        XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
        //2、获取工作表
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        //获取行
        Economy economy = new Economy();
        for (Row cells : sheetAt) {
            //4.获取单元格
            for (int i=0;i< cells.getLastCellNum();i++) {
                Cell cell = cells.getCell(i);
                cell.setCellType(CellType.STRING);
                String stringCellValue = cell.getStringCellValue();
                if (i==0) {
                    economy.setIndicator(stringCellValue);
                }else if (i==1){
                    economy.setRegionId(Long.valueOf(stringCellValue));
                }else if (i==2){
                    economy.setFieldId(Long.valueOf(stringCellValue));
                }else if (i==3){
                    economy.setYear(stringCellValue);
                }else if(i==4){
                    economy.setValue(Float.parseFloat(stringCellValue));
                }
            }
            economy.setCreateTime(LocalDateTime.now());
            economy.setUpdateTime(LocalDateTime.now());
            economyService.insertOne(economy);
        }
        sheets.close();
        return BaseResult.success();
    }
}
