package com.example.todo_list_backend.utils;

import com.example.todo_list_backend.Data.BaseResult;

/**
 * @author yangjj
 * @since 2025/11/20
 */
public class BaseResultResponseUtil {
    public static <T> BaseResult<T> success() {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(true);
        baseResult.setCode(200);
        baseResult.setMsg("ok");
        return baseResult;
    }

    public static<T> BaseResult<T> success(T data) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setSuccess(true);
        baseResult.setCode(200);
        baseResult.setData(data);
        baseResult.setMsg("ok");
        return baseResult;
    }

    public static BaseResult failure(String msg) {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false);
        baseResult.setCode(500);
        baseResult.setMsg(msg);
        return baseResult;
    }
}
