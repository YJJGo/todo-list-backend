package com.example.todo_list_backend.Data;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * 统一返回对象
 * Created by zhengmm on 2020-04-16.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = -8676181355403088497L;

    /**
     * 调用是否正常
     */
    boolean success;

    /**
     * 返回对象
     */
    T data;

    /**
     * 返回码，错误码
     */
    int code;

    /**
     * 返回消息
     */
    String msg;


    public BaseResult() {
        this.success = false;
    }

    public BaseResult(boolean success) {
        this.success = success;
    }

    public BaseResult(String msg) {
        this.success = false;
        this.msg = msg;
    }

    public BaseResult(int code, String msg) {
        this.code = code;
        this.success = false;
        this.msg = msg;
    }
}
