package infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Collections;

@SpringBootApplication
@ComponentScan({"infrastructure", "domain"})
@EnableScheduling
public class MainClass {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MainClass.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8085"));
        app.run(args);
    }
}
