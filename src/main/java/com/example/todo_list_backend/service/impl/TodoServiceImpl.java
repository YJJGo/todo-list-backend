package com.example.todo_list_backend.service.impl;

import com.example.todo_list_backend.entity.Todo;
import com.example.todo_list_backend.mapper.TodoMapper;
import com.example.todo_list_backend.service.ITodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 待办事项表 服务实现类
 * </p>
 *
 * @author yangjj
 * @since 2025-11-20
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements ITodoService {

}
