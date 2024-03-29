package com.acme.thinking.in.spring.ioc.overview.dependency.lookup;

import com.acme.thinking.in.spring.ioc.overview.annotation.Super;
import com.acme.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 * 1. 根据 Bean 名称查找
 * 2. 根据 Bean 类型查找
 * 3. 根据 Bean 名称 + 类型查找
 * 4. 根据 Java 注解查找
 *
 * @author <a href="mailto:wanghongxuan36@gmailcom">MercyCratos</a>
 * @since 2024-01-28
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        lookupInRealTime(beanFactory);

        lookupInLazy(beanFactory);

        lookupByType(beanFactory);

        lookupByCollectionType(beanFactory);

        lookupByAnnotationType(beanFactory);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");

        System.out.println("实时查找: " + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");

        User user = objectFactory.getObject();

        System.out.println("延迟查找: " + user);
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);

        System.out.println("实时查找: " + user);
    }


    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 集合对象: " + users);
        }
    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注 @Super 所有的 User 集合对象: " + users);
        }
    }
}
