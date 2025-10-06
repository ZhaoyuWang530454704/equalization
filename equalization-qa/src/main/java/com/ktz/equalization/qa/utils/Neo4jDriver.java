package com.ktz.equalization.qa.utils;

import org.neo4j.driver.v1.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Neo4jDriver {
    private Driver driver;
    //    private static org.apache.log4j.Logger logger = Logger.getLogger(JDBCToSpark.class);
    private static String NEO4J_CONFIG_PATH = "neo4j.properties";

    public Neo4jDriver() {
        Properties properties = new Properties();
        try {
            InputStream f = Neo4jDriver.class.getClassLoader().getResourceAsStream(NEO4J_CONFIG_PATH);
            System.out.println(f);
            properties.load(f);
        } catch (IOException e) {
//            logger.error("can not find config file for neo4j.");
            System.out.println("error");
        }
        driver = GraphDatabase.driver(properties.getProperty("uri"), AuthTokens.basic(properties.getProperty("user"), properties.getProperty("passwd")));


    }

    /**
     * run create statements
     *
     */
    public Transaction preStatement() {
        Session session = driver.session();
        Transaction transaction = session.beginTransaction();
        return transaction;
//        StatementResult result = transaction.run(txt,parameters("NAME","james"));
//        transaction.success();
//        session.close();
//        return result;
    }

    /**
     * explicitly call this method to stop the driver instance
     */
    public void stop() {
        driver.closeAsync();
    }

    public static void main(String[] args) {
        System.out.println("hello");
//        Neo4jDriver driver = new Neo4jDriver();
//        StatementResult result = driver.exeStatement("MATCH (c) return c");
//        while (result.hasNext()) {
//            System.out.println(result.next());
//        }
//        driver.stop();
    }

}

//public class Neo4jDriver {
//    private Driver driver;
////    private static org.apache.log4j.Logger logger = Logger.getLogger(JDBCToSpark.class);
//    private static String NEO4J_CONFIG_PATH = "config/Neo4j.properties";

//    public Neo4jDriver() {
//        Properties properties = new Properties();
//        try {
//            InputStream f = Neo4jDriver.class.getClassLoader().getResourceAsStream(NEO4J_CONFIG_PATH);
//            System.out.println(f);
//            properties.load(f);
//        } catch (IOException e) {
////            logger.error("can not find config file for neo4j.");
//            System.out.println("error");
//        }
//        driver = GraphDatabase.driver(properties.getProperty("uri"), AuthTokens.basic(properties.getProperty("user"), properties.getProperty("passwd")));
//    }
//
//
//    /**
//     * run create statements
//     *
//     * @param txt
//     * @return
//     */
//    public StatementResult exeStatement(String txt) {
//        Session session = driver.session();
//        Transaction transaction = session.beginTransaction();
//        StatementResult result = transaction.run(txt);
//        transaction.success();
//        session.close();
//        return result;
//
//    }
//
//    /**
//     * explicitly call this method to stop the driver instance
//     */
//    public void stop() {
//        driver.closeAsync();
//    }
//
//    public static void main(String[] args) {
//        Neo4jDriver driver = new Neo4jDriver();
//        StatementResult result = driver.exeStatement("MATCH (c) return c");
//        while (result.hasNext()) {
//            System.out.println(result.next());
//        }
//        driver.stop();
//    }
//
//}
