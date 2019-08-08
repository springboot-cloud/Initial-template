package com.framework.initial;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author XiongFeiYang
 * @description 项目启动类
 * @createTime 2019-08-08 15:00
 **/
@SpringBootApplication
@MapperScan("com.framework.initial.mapper.*")
public class InitialTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitialTemplateApplication.class, args);
    }

}
