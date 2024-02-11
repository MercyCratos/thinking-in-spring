package com.acme.thinking.in.spring.dependency.lookup;

import com.acme.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全 依赖查找示例
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        // 本质上是使用 Java API 的方式注册 Spring Bean - AnnotatedBeanDefinitionReader#register
        applicationContext.register(ObjectProviderDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 演示 BeanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        // 演示 ObjectFactory#getObject 方法的安全性
        displayObjectFactoryGetObject(applicationContext);
        // 演示 ObjectProvider#getIfAvailable 方法的安全性
        displayObjectProviderIfAvailable(applicationContext);

        // 演示 ListableBeanFactory#getBeansOfType 方法的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        // 演示 ObjectProvider Stream 操作的安全性
        displayObjectProviderStreamOps(applicationContext);

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(ApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () -> userObjectProvider.forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType", () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(ApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", userObjectProvider::getIfAvailable);
    }

    private static void displayObjectFactoryGetObject(ApplicationContext applicationContext) {
        // ObjectProvider is ObjectFactory
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", userObjectFactory::getObject);
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("==============================================");
        System.err.println("Source from : " + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
