package com.example.todo_list_backend.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.todo_list_backend.entity.Todo;
import com.example.todo_list_backend.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class ReminderTask {

    @Autowired
    private ITodoService todoService;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    // cron = "0 * * * * ?" 表示每分钟的第0秒执行一次
    @Scheduled(cron = "0 * * * * ?")
    public void checkAndSendReminders() {
        long now = System.currentTimeMillis();

        // 1. 查询条件：未完成 + 有截止日期 + 设置了提醒时间 + 还没发送过提醒 + 有邮箱
        LambdaQueryWrapper<Todo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Todo::getCompleted, 0)
                .eq(Todo::getReminderSent, 0)
                .isNotNull(Todo::getDueDatetime)
                .isNotNull(Todo::getReminderPreTime)
                .isNotNull(Todo::getEmail);

        List<Todo> list = todoService.list(wrapper);

        for (Todo todo : list) {
            // 计算触发时间：截止时间 - (提前分钟数 * 60 * 1000)
            long triggerTime = todo.getDueDatetime().getTime() - (todo.getReminderPreTime() * 60 * 1000L);

            // 如果当前时间已经超过了触发时间 (即：到了该提醒的时候了)
            if (now >= triggerTime) {
                sendEmail(todo);
            }
        }
    }

    private void sendEmail(Todo todo) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String prettyTime = sdf.format(todo.getDueDatetime());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(todo.getEmail());
            message.setSubject("待办提醒: " + todo.getTitle());
            message.setText("你好！\n\n你的任务【" + todo.getTitle() + "】即将截止。\n\n" +
                    "截止时间: " + prettyTime + "\n" +
                    "描述: " + (todo.getDescription() == null ? "无" : todo.getDescription()) + "\n\n" +
                    "请及时处理！");

            mailSender.send(message);
            System.out.println("邮件发送成功: " + todo.getTitle());

            // 发送成功后，标记为已发送，防止重复轰炸
            todo.setReminderSent(true);
            todoService.updateById(todo);

        } catch (Exception e) {
            System.err.println("邮件发送失败: " + e.getMessage());
        }
    }
}