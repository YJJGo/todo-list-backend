package com.example.todo_list_backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.todo_list_backend.common.Result;
import com.example.todo_list_backend.entity.Todo;
import com.example.todo_list_backend.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private ITodoService todoService;

    // 1. 获取列表 (支持按创建时间倒序)
    @GetMapping("/list")
    public Result<List<Todo>> list() {
        LambdaQueryWrapper<Todo> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Todo::getCreateDatetime); // 按时间倒序
        List<Todo> list = todoService.list(wrapper);
        return Result.success(list);
    }

    // 2. 添加任务
    @PostMapping("/add")
    public Result<Todo> add(@RequestBody Todo todo) {
        todoService.save(todo);
        return Result.success(todo); // 返回带ID的完整对象
    }

    // 3. 删除任务
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean success = todoService.removeById(id);
        return success ? Result.success(null) : Result.error("删除失败，ID不存在");
    }

    // 4. 切换状态 / 更新任务
    @PutMapping("/update")
    public Result<String> update(@RequestBody Todo todo) {
        boolean success = todoService.updateById(todo);
        return success ? Result.success(null) : Result.error("更新失败");
    }
}