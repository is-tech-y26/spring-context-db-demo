package ru.makarevich;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.lang.*;

@Configuration
@ComponentScan(basePackages = "ru.makarevich")
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        var service = context.getBean(UserService.class);
        for (int i = 0; i < 5; i++) {
            service.save("Test user %d".formatted(i));
        }
        service.getAll().stream().forEach(System.out::println);
    }
}
