package com.example.todo_list_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.todo_list_backend.entity.Todo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 待办事项表 Mapper 接口
 * </p>
 *
 * @author yangjj
 * @since 2025-11-20
 */
@Mapper
public interface TodoMapper extends BaseMapper<Todo> {

}
