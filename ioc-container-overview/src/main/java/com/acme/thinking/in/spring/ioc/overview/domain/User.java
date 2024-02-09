package com.acme.thinking.in.spring.ioc.overview.domain;

/**
 * 用户类
 *
 * @author <a href="mailto:wanghongxuan36@gmailcom">MercyCratos</a>
 * @since 2024-01-28
 */
public class User {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
