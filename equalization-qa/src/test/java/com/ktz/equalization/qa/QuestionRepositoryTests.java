package com.ktz.equalization.qa;

import com.ktz.equalization.qa.repository.Questions;

import com.ktz.equalization.qa.service.Impl.QuestionServiceImpl;
import com.ktz.equalization.qa.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.driver.v1.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 庞超然
 * @date 2021/1/18 下午6:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context*.xml")
public class QuestionRepositoryTests {


//    private QuestionRepository questionRepository;

//    @Test
//    public void test() {
//        List<String> permissions = questionRepository.getBm("基本公共就业服务");
//        permissions.forEach(System.out::println);
//    }
@Test
    public void testdata(){
        String s="基本社会保险";
        Questions q=new Questions();
        List<Value> l=q.getActors("基本公共服务");
        for (int i = 0; i <l.size() ; i++) {
            System.out.println(l.get(i));
        }
    }
    @Autowired
    private QuestionService questionService;
@Test
    public void testService() throws Exception {
        List list=questionService.getResults("哪些学者在研究基本公共服务均等化问题");
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }

}
