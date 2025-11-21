# TODO List - 后端文档

这是一个基于 **Spring Boot 3 + MyBatis Plus + MySQL** 构建的待办事项后端服务，支持任务管理、排序及邮件提醒功能。

## 环境要求

- **JDK**: 17 或更高版本 (Spring Boot 3 必需)
- **MySQL**: 8.0
- **Maven**: 3.6+

## 快速运行

### 1. 初始化数据库
请在 MySQL 中创建数据库 `todo_db` 并执行以下 SQL：

```sql
CREATE TABLE `todo` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) NOT NULL COMMENT '任务标题',
  `description` varchar(500) DEFAULT NULL COMMENT '任务描述',
  `category` varchar(20) DEFAULT '其他' COMMENT '分类: 工作/学习/生活/其他',
  `completed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否完成 0:未完成 1:已完成',
  `email` varchar(100) DEFAULT NULL COMMENT '提醒邮箱',
  `reminder_pre_time` int DEFAULT NULL COMMENT '提前多少分钟提醒',
  `reminder_sent` tinyint(1) DEFAULT '0' COMMENT '提醒状态 0:已提醒 1:未提醒',
  `due_datetime` datetime DEFAULT NULL COMMENT '截止日期时间戳',
  `create_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间戳',
  `update_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='待办事项表'
```

### 2. 修改配置文件
打开 `src/main/resources/application.yml`，配置以下信息：

数据库连接: 修改 `spring.datasource` 下的 `username` 和 `password`。

邮件服务: 修改 `spring.mail` 下的 `username` (发件人邮箱) 和 `password` (SMTP 授权码)。

### 3. 启动项目
在 `backend` 目录下执行 Maven 命令：
```bash
mvn spring-boot:run
```
或者在 IDE (IntelliJ IDEA / Eclipse) 中直接运行 `TodoApplication` 类的 `main` 方法。

服务启动后默认监听端口：8080。