package com.ktz.equalization.web.controller;

import com.ktz.equalization.commons.dto.BaseResult;
import com.ktz.equalization.commons.dto.LayUiResult;
import com.ktz.equalization.domain.Economy;
import com.ktz.equalization.domain.Policy;
import com.ktz.equalization.domain.User;
import com.ktz.equalization.system.service.PolicyService;
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
import java.util.List;

/**
 * @Author 刘世阳
 * @Date 2021/1/14 18:04
 * @Description:
 */
@Controller
@RequestMapping("/policy")
public class PolicyController {
    @Autowired
    PolicyService policyService;
    @GetMapping("/view")
    public String view() {
        return "policy";
    }
    @DeleteMapping("/deleteMulti")
    @ResponseBody
    public BaseResult deleteMulti( @RequestBody String ids[]){
        policyService.deleteMulti(ids);
        return BaseResult.success();
    }
    @GetMapping("/listAll")
    @ResponseBody
    public LayUiResult<Policy> listAll(){
        List<Policy> policies = policyService.listAll();
        return LayUiResult.createResult(0,"ok",policies.size(),policies);
    }
    @DeleteMapping("/deleteOne/{id}")
    @ResponseBody
    public BaseResult deleteOne(@PathVariable("id") Long id){
        policyService.deleteOne(id);
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
        Policy policy = new Policy();
        for (Row cells : sheetAt) {
            //4.获取单元格
            for (int i=0;i< cells.getLastCellNum();i++) {
                Cell cell = cells.getCell(i);
                cell.setCellType(CellType.STRING);
                String stringCellValue = cell.getStringCellValue();
                if (i==0) {
                    policy.setTitle(stringCellValue);
                }else if (i==1){
                    policy.setAuthor(stringCellValue);
                }else if (i==2){
                    policy.setPublish(stringCellValue);
                }else if (i==3){
                    policy.setUrl(stringCellValue);
                }
            }
            policy.setCreateTime(LocalDateTime.now());
            policy.setUpdateTime(LocalDateTime.now());
            policyService.insertOne(policy);
        }
        sheets.close();
        return BaseResult.success();
    }
}
