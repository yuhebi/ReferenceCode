package com.qhit.produceJob.controller;

import com.qhit.produceJob.pojo.ProduceJob;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by yy on 2019/5/20.
 */
public class test {
    // 创建sqlSessionFactory
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        // mybatis配置文件
        String resource = "com/qhit/produceJob/dao/ProduceJobMapper.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建会话工厂，传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void testFindUserById() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 创建UserMapper对象，mybatis自动生成mapper代理对象
        ProduceJobController userMapper = sqlSession.getMapper(ProduceJobController.class);

        // 调用userMapper的方法
        // 第一次查询（先去缓存中查找，有则直接获取，没有则执行SQL语句查询）
        ProduceJob load = userMapper.load(1);
        System.out.println(load);
        // 第二次查询（先去缓存中查找，有则直接获取，没有则执行SQL语句查询）
        ProduceJob load1 = userMapper.load(1);
        System.out.println(load1);

    }


}
