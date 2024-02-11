package com.acme.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException} 示例代码
 */
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        // 本质上是使用 Java API 的方式注册 Spring Bean - AnnotatedBeanDefinitionReader#register
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        try {
            // 由于 Spring 应用上下文存在两个 String 类型的 Bean，通过单一类型查找会抛出异常
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("Spring 应用上下文存在%d个 %s 类型的 Bean, 具体原因: %s%n",
                    e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage());
        }

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "1";
    }

    @Bean
    public String bean2() {
        return "2";
    }
}
