package com.ktz.equalization.qa.repository;

import com.ktz.equalization.qa.utils.Neo4jDriver;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.Value;
import java.util.ArrayList;
import java.util.List;
import static org.neo4j.driver.v1.Values.parameters;
/**
 * @author 庞超然
 * @date 2021/1/20 下午6:50
 */
public class Questions {
    private Neo4jDriver driver;
    private Transaction t;
    private List<Value> list;

    public Questions(){
        driver=new Neo4jDriver();
        t=driver.preStatement();
        list=null;
    }
    /**
     * 0 对应问题模板0
     *
     * @param relation 领域
     * @return 返回服务项目
     */
    public List<String> getService(String relation){
        List<String> list=new ArrayList<>();
        StatementResult result =t.run("match(n1)-[p:`服务项目`]->(n2) where n1.name={ relation } return n2",parameters("relation",relation));
        while (result.hasNext()) {

//            System.out.println(result.next().get(0).get("name"));
            list.add(result.next().get(0).get("name").asString());
//            System.out.println(result.next());
        }


        return list;
    }

    /**
     * 1 对应问题模板1
     *
     * @return 概念
     */
//    node1 relation node2
    public List getSome(String relation,String xm){


        StatementResult r =t.run("match(n1)-[p]->(n2) where n1.name={ node } and p.name={ relation } return n2.name",parameters("node",relation,"relation",xm));
        List<String> list=new ArrayList<>();
        while (r.hasNext()) {
            String v=r.next().get(0).asString();
            list.add(v);
        }

        return list;
    }

    /**
     * 2 对应问题模板2 研究热点
     *
     * @return 研究热点
     */
    //ly = code;
    public List getHotSpot(String ly){
        //ly: 研究热点问题
        String lys=ly+".*";
//        System.out.println("match(n1)-[p:`研究热点` ]->(n2) where n1.name=~{ relation } return n2");
        StatementResult r1 =t.run("match(n1)-[p:`研究热点` ]->(n2) where n1.name=~{ relation } return n2",parameters("relation",lys));



        List<String> l2=new ArrayList<>();
        while (r1.hasNext()) {
            String v=r1.next().get(0).get("name").asString();
            l2.add(v);
        }
        System.out.println("list:");
        for (int i = 0; i <l2.size() ; i++) {
            System.out.println(l2.get(i));
        }

        return l2;
    }

    /**
     * 3 对应问题模板3 =领域学者
     *
     * @return 高产作者
     */
    public List getActors(String ly){

        String lys=ly+".*";
        StatementResult r2 =t.run("match(n1)-[p:`高产作者` ]->(n2) where n1.name=~{ relation } return n2",parameters("relation",lys));
        List<String> l3=new ArrayList<>();
        while (r2.hasNext()) {

            l3.add(r2.next().get(0).get("name").asString());
        }
        //        for (int i = 0; i <l2.size() ; i++) {
//            System.out.println(l2.get(i));
//        }

        return l3;

    }


    /**
     * 4 对应问题模板4 领域部门
     *
     * @return 部门
     */

    public List getBm(String ly){
        List<String> l3=new ArrayList<>();
        StatementResult r1 =t.run("match(n1)-[p:`牵头负责` ]->(n2) where n1.name={ relation } return n2",parameters("relation",ly));
        while (r1.hasNext()) {


            l3.add(r1.next().get(0).get("name").asString());

        }

        return l3;
    }


    /**
     * 5 对应问题模板5 地区均等化水平
     *
     * @return 服务指导标准
     */
    public List getLevel(String dq){
        List<String> l4=new ArrayList<>();

        StatementResult r1 =t.run("match(n1)-[p:`服务指导标准` ]->(n2) where n1.name={ relation } return n2",parameters("relation",dq));
        while (r1.hasNext()) {

            l4.add(r1.next().get(0).get("name").asString());

        }

        return l4;
    }


}
