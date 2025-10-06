package com.ktz.equalization.system;

import com.github.pagehelper.PageInfo;
import com.ktz.equalization.commons.dto.TreeResult;
import com.ktz.equalization.domain.Economy;
import com.ktz.equalization.domain.Role;
import com.ktz.equalization.system.service.EconomyService;
import com.ktz.equalization.system.service.ExcelService;
import com.ktz.equalization.system.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/5 下午1:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context*.xml")
public class ExcelServiceTests {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private EconomyService economyService;

    @Test
    public void test() {
        // String path = "/home/raincoding/IdeaProjects/maven_projects/equalization/equalization-system/src/main/resources/excel/中国教育数据库-年度（分省） (1).xls";
        // List<Economy> economies = excelService.parseExcel(path, "基本公共教育");
        // economyService.insertMulti(economies);
    }

}
