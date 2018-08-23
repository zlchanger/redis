package com.example.redis.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/23 16:12
 * @Description:
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1222221L;

    private Long id;

    private String name;

    private String password;

    public User(){}

    public User(Long id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString(){
        return "user{"+
                "id="+id+
                "name="+name+
                "password="+password+
                "}";
    }
}
