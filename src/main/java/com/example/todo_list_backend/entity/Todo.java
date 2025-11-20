package com.example.todo_list_backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 待办事项表
 * </p>
 *
 * @author yangjj
 * @since 2025-11-20
 */
@Data
@ToString
@TableName("todo")
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务标题
     */
    @TableField("title")
    private String title;

    /**
     * 任务描述
     */
    @TableField("description")
    private String description;

    /**
     * 分类: 工作/学习/生活/其他
     */
    @TableField("category")
    private String category;

    /**
     * 是否完成 0:未完成 1:已完成
     */
    @TableField("completed")
    private Boolean completed;

    /**
     * 截至时间戳
     */
    @TableField("due_datetime")
    private Date dueDatetime;

    /**
     * 创建时间戳
     */
    @TableField("create_datetime")
    private Date createDatetime;

    /**
     * 更新时间戳
     */
    @TableField("create_datetime")
    private Date updateDatetime;
}
