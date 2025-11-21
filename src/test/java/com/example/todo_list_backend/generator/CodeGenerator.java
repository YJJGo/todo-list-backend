package com.example.todo_list_backend.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * MyBatis-Plus 代码生成器
 * 直接运行 main 方法即可
 */
public class CodeGenerator {

    // 数据库配置
    private static final String URL = "jdbc:mysql://localhost:3306/todo_db?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String AUTHOR = "yangjj";
    private static final String PARENT = "com.example.todo_list_backend";
    private static final String TABLE_NAME = "todo";
    private static final String SERVICE_FILE_NAME = "ITodoService";
    private static final String SERVICE_IMPL_FILE_NAME = "TodoServiceImpl";


    public static void main(String[] args) {
        // 1. 获取项目路径
        String projectPath = System.getProperty("user.dir");

        // 2. 开始生成
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // --- 全局配置 ---
                .globalConfig(builder -> {
                    builder.author(AUTHOR) // 设置作者
                            .dateType(DateType.TIME_PACK) // 使用 Java 8 时间类型 (LocalDateTime/Long)
                            .commentDate("yyyy-MM-dd")
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                // --- 包配置 ---
                .packageConfig(builder -> {
                    builder.parent(PARENT) // 设置父包名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .controller("controller")
                            // 设置 Mapper XML 文件生成的路径 (通常在 resources 下)
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper"));
                })
                // --- 策略配置 (核心) ---
                .strategyConfig(builder -> {
                    builder.addInclude(TABLE_NAME); // 设置需要生成的表名

                    // 1. Entity 策略
                    builder.entityBuilder()
                            .enableLombok() // 开启 Lombok
                            .enableTableFieldAnnotation() // 开启字段注解
                            .idType(IdType.AUTO) // 主键策略：自增
                            .naming(NamingStrategy.underline_to_camel) // 下划线转驼峰
                            .columnNaming(NamingStrategy.underline_to_camel);

                    // 2. Controller 策略
                    builder.controllerBuilder()
                            .enableRestStyle(); // 开启 @RestController 模式

                    // 3. Service 策略
                    builder.serviceBuilder()
                            .formatServiceFileName(SERVICE_FILE_NAME) // 格式化 Service 接口名为 I开头
                            .formatServiceImplFileName(SERVICE_IMPL_FILE_NAME);

                    // 4. Mapper 策略
                    builder.mapperBuilder()
                            .enableMapperAnnotation(); // 开启 @Mapper 注解
                })
                // --- 模板引擎 ---
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 引擎模板，默认是 Velocity
                .execute(); // 执行生成
    }
}