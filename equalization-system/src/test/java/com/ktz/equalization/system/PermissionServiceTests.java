package com.ktz.equalization.system;

import com.ktz.equalization.commons.dto.TreeResult;
import com.ktz.equalization.domain.Permission;
import com.ktz.equalization.system.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/5 下午1:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context*.xml")
public class PermissionServiceTests {

    @Autowired
    private PermissionService service;

    @Test
    public void test() {
        Permission permission = service.getOne(1L);
        System.out.println(permission);
    }

    @Test
    public void listPermissionTest() {
        List<Permission> permissions = service.list(1L);
        permissions.forEach(System.out::println);
    }

    @Test
    public void listTreeTest() {
        List<TreeResult> treeResults = service.listTree(1L);
        treeResults.forEach(System.out::println);
    }
}
