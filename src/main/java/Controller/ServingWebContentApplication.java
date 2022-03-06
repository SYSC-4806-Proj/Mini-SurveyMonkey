package Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = "Entity")
@SpringBootApplication
public class ServingWebContentApplication {
    public static void main(String[] args){
        SpringApplication.run(ServingWebContentApplication.class, args);
    }
}

