package com.acme.thinking.in.spring.ioc.overview.domain;

import com.acme.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * 超级用户
 *
 * @author <a href="mailto:wanghongxuan36@gmailcom">MercyCratos</a>
 * @since 2024-01-28
 */
@Super
public class SuperUser extends User {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
