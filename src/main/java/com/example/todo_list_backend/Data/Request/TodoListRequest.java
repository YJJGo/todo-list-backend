package com.example.todo_list_backend.Data.Request;

import lombok.Data;

import java.util.Date;

/**
 * @author yangjj
 * @since 2025/11/20
 */
@Data
public class TodoListRequest {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 分类: 工作/学习/生活/其他
     */
    private String category;

    /**
     * 是否完成 0:未完成 1:已完成
     */
    private Boolean completed;

    /**
     * 截止时间戳
     */
    private Date dueDatetime;

    /**
     * 创建时间戳
     */
    private Date createDatetime;

    /**
     * 更新时间戳
     */
    private Date updateDatetime;

    /**
     * 排序字段
     */
    private String sortBy;
}
