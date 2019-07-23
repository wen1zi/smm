package com.aaa.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Author: 陈建
 * @Date: 2019/7/18 0018 16:53
 * @Version 1.0
 * <!--此处配置spring容器-->
 */
@Configuration
@ComponentScan(basePackages = {"com.aaa.dao", "com.aaa.service"})
public class SpringConfig {
    //    定义数据源
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sz0304");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;

    }


    /**
     * 配置mybatis SqlSessionFactoryBean
     */

    //xxxx是bean的id和名字，如果不写，那么bean的id和名字默认是方法名getSqlSessionFactoryBean
    @Bean("getSqlSessionFactoryBean")
    public SqlSessionFactoryBean getSqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
// 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource());

// 设置环境为开发模式

        sqlSessionFactoryBean.setEnvironment("development");
// 设置别名

         sqlSessionFactoryBean.setTypeAliasesPackage("com.aaa.entity");

// 扫描mybatis的mapper映射文件

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

// mapper文件所在的路径

        String locationPattern = "mapper/*.xml";

        try {

            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(locationPattern));

        } catch (IOException e) {

            e.printStackTrace();

        }

        return sqlSessionFactoryBean;

    }

    /**
     * 自动扫描使用jdk动态代理 将Mapper接口生成代理注入到Spring 容器中
     */

    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.aaa.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("getSqlSessionFactoryBean");
        return mapperScannerConfigurer;

    }

}
