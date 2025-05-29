package com.example.spring_tutorial02.form;

import lombok.Data;

@Data
public class UserRegisterForm {
    // ユーザー名
    private String userName;
    // パスワード
    private String password;
    // ロールID
    private Integer roleId;
}
