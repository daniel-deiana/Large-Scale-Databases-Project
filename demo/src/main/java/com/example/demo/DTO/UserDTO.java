package com.example.demo.DOT;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private Date birthday;
    private String country;
    private String gender;

    private boolean isMyself;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private boolean isMyself(){
        return isMyself;
    }

    public UserDTO(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
