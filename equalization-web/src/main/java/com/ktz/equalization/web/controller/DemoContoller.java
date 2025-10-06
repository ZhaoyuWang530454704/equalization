package com.ktz.equalization.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.ktz.equalization.commons.dto.JsonResult;
import com.ktz.equalization.domain.Economy;
import com.ktz.equalization.domain.EconomyExtra;
import com.ktz.equalization.mapper.EconomyMapper;
import com.ktz.equalization.system.service.EconomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 刘世阳
 * @date 2021/2/7 8:02
 * @description:
 */
@Controller
public class DemoContoller {
    @Autowired
    EconomyMapper economyMapper;
    @PostMapping("/hebei")
    @ResponseBody
    public JsonResult<EconomyExtra> listhebei(@RequestBody Economy temp){
        Economy economy = new Economy();
        economy.setIndicator(temp.getIndicator());
        economy.setRegionId(2L);
        economy.setFieldId(temp.getFieldId());

        List<Economy> list = economyMapper.list(economy);
        Object o[] = new Object[list.size()];
        int i=0;
        for (Economy economy1 : list) {
            o[i]=economy1.getYear();
            o[i]=economy1.getValue();
            i++;
        }
        return JsonResult.createResult(o);
    }
    @PostMapping("/beijing")
    @ResponseBody
    public JsonResult<EconomyExtra> listbeijing(@RequestBody Economy temp){
        Economy economy = new Economy();
        economy.setIndicator(temp.getIndicator());
        economy.setRegionId(3L);
        economy.setFieldId(temp.getFieldId());

        List<Economy> list = economyMapper.list(economy);
        Object o[] = new Object[list.size()];
        int i=0;
        for (Economy economy1 : list) {
            o[i]=economy1.getYear();
            o[i]=economy1.getValue();
            i++;
        }
        return JsonResult.createResult(o);
    }
    @PostMapping("/tianjin")
    @ResponseBody
    public JsonResult<EconomyExtra> listtianjin(@RequestBody Economy temp){
        Economy economy = new Economy();
        economy.setIndicator(temp.getIndicator());
        economy.setRegionId(4L);
        economy.setFieldId(temp.getFieldId());

        List<Economy> list = economyMapper.list(economy);
        Object o[] = new Object[list.size()];
        int i=0;
        for (Economy economy1 : list) {
            o[i]=economy1.getYear();
            o[i]=economy1.getValue();
            i++;
        }
        return JsonResult.createResult(o);
    }
    @PostMapping("/quanguo")
    @ResponseBody
    public JsonResult<EconomyExtra> listquanguo(@RequestBody Economy temp){
        Economy economy = new Economy();
        economy.setIndicator(temp.getIndicator());
        economy.setRegionId(1L);
        economy.setFieldId(temp.getFieldId());

        List<Economy> list = economyMapper.list(economy);
        Object o[] = new Object[list.size()];
        int i=0;
        for (Economy economy1 : list) {
            o[i]=economy1.getYear();
            o[i]=economy1.getValue();
            i++;
        }
        return JsonResult.createResult(o);
    }
}
