package com.acme.thinking.in.spring.bean.definition;

import com.acme.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名示例
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        // 通过别名 mercy-user 获取曾用名 user 的 bean
        User user = beanFactory.getBean("user", User.class);
        User mercyUser = beanFactory.getBean("mercy-user", User.class);

        System.out.println("mercy-user 是否与 user Bean 相同: " + (user == mercyUser));
    }
}
