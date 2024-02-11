package com.acme.thinking.in.spring.bean.definition;

import com.acme.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注册 BeanDefinition 示例
 */
// 3. 通过 @Import 进行导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        // 本质上是使用 Java API 的方式注册 Spring Bean - AnnotatedBeanDefinitionReader#register
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 通过另一种 Java API 的方式注册 BeanDefinition
        // 1. 命名 Bean 的注册方式
        registerUserBeanDefinition(applicationContext, "another-user");
        // 2. 非命名 Bean 的注册方式
        registerUserBeanDefinition(applicationContext);

        // 按照类型依赖查找
        System.out.println("Config 类型的所有 Beans" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有 Beans" + applicationContext.getBeansOfType(User.class));

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);

        beanDefinitionBuilder
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "mercy");

        // 判读如果 beanName 参数存在时
        if (StringUtils.hasText(beanName)) {
            // 命名 Bean 注册方法
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名 Bean 注册方法
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    // 2. 通过 @Component 方式
    @Component
    public static class Config {

        // 1. 通过 @Bean 方式定义
        @Bean(name = {"user", "mercy-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("MercyCratos");
            return user;
        }
    }
}
