package com.yh.csx.demo.service.vo;

import lombok.Data;

/**
 * User class
 *
 * @author huyalin
 * @date 2019/2/22
 */
@Data
public class User {
    private Long userId;
    private String userName;

    public User() {
    }

    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
