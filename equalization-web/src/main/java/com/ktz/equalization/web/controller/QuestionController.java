package com.ktz.equalization.web.controller;

import com.ktz.equalization.qa.service.Impl.QuestionServiceImpl;
import com.ktz.equalization.qa.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.neo4j.driver.v1.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question")
    @ResponseBody
    public List question(String str) throws Exception {

        List list = questionService.getResults(str);
        return list;
    }
    @GetMapping("/search")
    public String search(){return "Search";}


}
