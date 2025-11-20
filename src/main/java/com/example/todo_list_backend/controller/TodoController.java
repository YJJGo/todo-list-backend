package com.example.todo_list_backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.todo_list_backend.Data.BaseResult;
import com.example.todo_list_backend.Data.Request.TodoAddRequest;
import com.example.todo_list_backend.Data.Request.TodoListRequest;
import com.example.todo_list_backend.Data.Request.TodoUpdateRequest;
import com.example.todo_list_backend.Data.Response.TodoResponse;
import com.example.todo_list_backend.entity.Todo;
import com.example.todo_list_backend.service.ITodoService;
import com.example.todo_list_backend.utils.BaseResultResponseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private ITodoService todoService;

    // 1. 获取列表 (支持按创建时间倒序)
    @PostMapping("/list")
    public BaseResult<List<TodoResponse>> list(@RequestBody TodoListRequest todoListRequest) {
        String sortBy = todoListRequest.getSortBy();
        LambdaQueryWrapper<Todo> wrapper = new LambdaQueryWrapper<>();
        if ("dueDatetime".equals(sortBy)) {
            wrapper.orderByAsc(Todo::getDueDatetime);
        } else {
            wrapper.orderByDesc(Todo::getCreateDatetime);
        }
        List<Todo> todoList = todoService.list(wrapper);
        List<TodoResponse> TodoResponses = todoList.stream().map(todo -> {
            TodoResponse todoResponse = new TodoResponse();
            BeanUtils.copyProperties(todo, todoResponse);
            return todoResponse;
        }).toList();
        return BaseResultResponseUtil.success(TodoResponses);
    }

    // 2. 添加任务
    @PostMapping("/add")
    public BaseResult<Boolean> add(@RequestBody TodoAddRequest todoAddRequest) {
        Todo todo = new Todo().setTitle(todoAddRequest.getTitle())
                .setDescription(todoAddRequest.getDescription())
                .setCategory(todoAddRequest.getCategory())
                .setDueDatetime(todoAddRequest.getDueDatetime());
        return BaseResultResponseUtil.success(todoService.save(todo));
    }

    // 3. 删除任务
    @DeleteMapping("/delete/{id}")
    public BaseResult<Boolean> delete(@PathVariable Long id) {
        return BaseResultResponseUtil.success(todoService.removeById(id));
    }

    // 4. 切换状态 / 更新任务
    @PutMapping("/update")
    public BaseResult<Boolean> update(@RequestBody TodoUpdateRequest todoUpdateRequest) {
        Todo todo = new Todo().setId(todoUpdateRequest.getId())
                .setTitle(todoUpdateRequest.getTitle())
                .setDescription(todoUpdateRequest.getDescription())
                .setCategory(todoUpdateRequest.getCategory())
                .setCompleted(todoUpdateRequest.getCompleted())
                .setDueDatetime(todoUpdateRequest.getDueDatetime());
        return BaseResultResponseUtil.success(todoService.updateById(todo));
    }
}