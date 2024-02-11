package com.acme.thinking.in.spring.bean.factory;

import com.acme.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link User} Bean 的 {@link FactoryBean} 实现
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
