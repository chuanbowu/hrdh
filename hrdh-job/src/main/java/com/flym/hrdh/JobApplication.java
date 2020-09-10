package com.flym.hrdh;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:系统任务调度启动管理</p>
 * <p>Copyright: Copyright (c) 2020-06-12</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@ImportResource({"classpath:spring-registry.xml"})
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, ValidationAutoConfiguration.class})
public class JobApplication
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(JobApplication.class, args);
        System.out.println("JOB SERVER SUCCESSFUL!");
    }
}
