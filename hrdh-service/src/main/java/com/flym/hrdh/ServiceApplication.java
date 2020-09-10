package com.flym.hrdh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * <p>Title:红人带货系统</p>
 * <p>Description:Spring Boot应用启动类</p>
 * <p>Copyright: Copyright (c) 2020-04-28</p>
 * <p>Company: 翔梦[http://mail.flym.cn]</p>
 * @author $Author: jh.x $
 * @version $Revision: 1.0.0 $
 */
@ImportResource({"classpath:spring-registry.xml"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ServiceApplication
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(ServiceApplication.class,args);
        System.out.println("SERVER SUCCESSFUL!");
    }

}
