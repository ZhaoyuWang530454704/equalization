package com.ktz.equalization.qa.core;

import com.ktz.equalization.qa.service.Impl.QuestionServiceImpl;
import com.ktz.equalization.qa.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestController {
    @Autowired
    private QuestionService questionService;
//    List list=questionService.getResults("哪些学者在研究基本公共服务均等化问题");
    public static void main(String[] args) throws Exception {
        QuestionService questionService = new QuestionServiceImpl();
        List list = questionService.getResults("哪些学者在研究基本公共服务均等化问题");
        for (Object ls : list) {
            System.out.println(ls);
        }
    }
}
