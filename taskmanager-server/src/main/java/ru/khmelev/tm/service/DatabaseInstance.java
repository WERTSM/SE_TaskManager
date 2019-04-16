//package ru.khmelev.tm.service;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import java.io.Reader;
//
//public class DatabaseInstance {
//
//    private static DatabaseInstance instance = null;
//
//
//    public DatabaseInstance() {
//        try (Reader reader = Resources.getResourceAsReader("database/SqlMapConfig.xml")) {
//            setFactory(new SqlSessionFactoryBuilder().build(reader));
//        } catch (Exception e) {}
//    }
//
//    private static DatabaseInstance getInstance() {
//        if (instance == null)
//            instance = new DatabaseInstance();
//        return instance;
//    }
//}
