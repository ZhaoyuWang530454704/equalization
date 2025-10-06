package com.ktz.equalization.system;

import com.ktz.equalization.commons.dto.TreeResult;
import com.ktz.equalization.domain.Role;
import com.ktz.equalization.system.service.PolicyService;
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
public class PolicyServiceTests {

    @Autowired
    private PolicyService policyService;

    @Test
    public void test() {
        policyService.save("均等化");
    }

}
