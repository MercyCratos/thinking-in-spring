package com.acme.thinking.in.spring.bean.factory;

import com.acme.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User} User 工厂类
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
