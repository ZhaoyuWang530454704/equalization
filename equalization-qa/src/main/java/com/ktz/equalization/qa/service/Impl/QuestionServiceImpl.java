//package com.ktz.equalization.qa.service;
//
//import com.ktz.equalization.qa.core.CoreProcess;
//import com.ktz.equalization.qa.repository.Questions;
//import org.neo4j.driver.v1.Value;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class QuestionService {
//    private String questionModel;
//    private String code;
//    private Questions questions=new Questions();
//    public QuestionService(String s) throws Exception {
//        CoreProcess query = new CoreProcess();
////        String [] questionArr = new String[] {"基本社会保险的服务项目有哪些"};
//        String abstr = query.queryAbstract(s);
//        questionModel=query.queryClassify(abstr);
//        System.out.println("questionModel:"+questionModel);
//        ArrayList<String> question = query.analyQuery(questionModel);
////        for (String que: questionArr){
////
////            ArrayList<String> question = query.analyQuery(que);
////            System.err.println(question.get(1));
////            code=question.get(1);
////        }
////        System.out.println(question);
//        code=question.get(1);
//    }
//    public List<Value> getResults(){
//
//
//        List list=new ArrayList();
//        if (questionModel.equals("ly 服务项目")){
//            list=questions.getService(code);
//        }else if (questionModel.equals("xm smsx服务项目属性")){
//
//        }else if (questionModel.equals("ly 研究热点问题")){
//            list=questions.getHotSpot(code);
//        }else if (questionModel.equals("ly 高产作者问题")){
//            list=questions.getActors(code);
//        }else if (questionModel.equals("bm 服务项目")){
//            list=questions.getBm(code);
//        }else if (questionModel.equals("qd 均等化水平")){
//            list=questions.getLevel(code);
//        }else {
//            list=null;
//        }
//        return list;
//    }
//}
package com.ktz.equalization.qa.service.Impl;

import com.ktz.equalization.qa.core.CoreProcess;
import com.ktz.equalization.qa.repository.Questions;
import com.ktz.equalization.qa.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    public QuestionServiceImpl() {
    }

    @Override
    public List getResults(String s) throws Exception{
        CoreProcess query = new CoreProcess("D:/HLP汉语言处理");
        String abstr = query.queryAbstract(s);
        String questionModel=query.queryClassify(abstr);//

        System.out.println("abstr: "+abstr);
        System.out.println("questionModel:"+questionModel);

        ArrayList<String> question = query.analyQuery(questionModel);
        String code=question.get(1);
        System.out.println("code: " + code);

//        String questionModel=query.queryClassify("基本 公共服务 的 哪些 学者 研究 的 多");
//        String questionModel = "研究热点问题";
//        String code = "基本公共服务";

        List list = new ArrayList();
        Questions questions = new Questions();
        if (questionModel.equals("ly 服务项目")){
            list=questions.getService(code);
        }else if (questionModel.equals("xm smsx服务项目属性")){

        }else if (questionModel.equals("ly 研究热点问题")){
            list=questions.getHotSpot(code);
        }else if (questionModel.equals("ly 高产作者问题")){
            list=questions.getActors(code);
        }else if (questionModel.equals("bm 服务项目")){
            list=questions.getBm(code);
        }else if (questionModel.equals("qd 均等化水平")){
            list=questions.getLevel(code);
        }else {
            list=null;
        }
        return list;
    }


}

