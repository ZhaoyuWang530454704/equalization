package com.ktz.equalization.qa.repository;

import com.ktz.equalization.qa.model.dw;
import com.ktz.equalization.qa.model.zz;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends Neo4jRepository<dw, Long> {

    /**
     * 0 对应问题模板0
     *
     * @param field 领域
     * @return 返回服务项目
     */
    @Query("match(n:ly)-[r]-(b) where n.name = '服务项目' and r = {relation} return b")
    List<String> getFwxm(@Param("field") String field);

    /**
     * 1 对应问题模板1
     *
     * @param xm node1
     * @return 概念
     */
    @Query("match(n)-[r]-(b) where n.name = {xm} and r= {xmsx} return b")
    List getSome(@Param("xm") String xm,@Param("xmsx") String xmsx);

    /**
     * 2 对应问题模板2 研究热点
     * @param field
     *
     * @return 研究热点
     */
    @Query("match(n:ly)-[r]->(b:yjrd) where n.name={field} return b")
    List<String> getHotSpot(@Param("field") String field);

    /**
     * 3 对应问题模板3 =领域学者
     *
     * @param field 领域
     * @return 高产作者
     */
    @Query("match(n:ly)-[r]->(b:zz) where n.name={field} and r='高产作者' return b")
    List<zz> getActors(@Param("field") String field);

    /**
     * 4 对应问题模板4 领域部门
     *
     * @param field 领域
     * @return 部门
     */
    @Query("match(n:ly)-[r]->(b:bm) where n.name={field} and r='牵头负责' return b")
    List<String> getBm(@Param("field") String field);

    /**
     * 5 对应问题模板5 地区均等化水平
     *
     * @param name 地区
     * @return 服务指导标准
     */
    @Query("match(n)-[r]->(b:bm) where n.name={name} and r='服务指导标准' return b")
    List<String> getServiceLevel(@Param("name") String name);

}



