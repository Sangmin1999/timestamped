package com.sparta.todopractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodoPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoPracticeApplication.class, args);
    }

}
