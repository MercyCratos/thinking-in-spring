package com.acme.thinking.in.spring.dependency.lookup;

import com.acme.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 {@link ObjectProvider} 进行依赖查找
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        // 本质上是使用 Java API 的方式注册 Spring Bean - AnnotatedBeanDefinitionReader#register
        applicationContext.register(ObjectProviderDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找集合对象
        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);


        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
//        for (String str : objectProvider) {
//            System.out.println(str);
//        }
        // Stream -> Method Reference
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前 User 对象 : " + user);
    }

    @Bean
    @Primary
    public String helloWorld() { // 方法名就是 Bean 名称 = "helloWorld"
        return "Hello,World";
    }

    @Bean
    public String message() {
        return "Message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
