package cse.swengineering.smtm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmtmApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SmtmApplication.class);
        app.setWebApplicationType(WebApplicationType.SERVLET);
        app.run(args);
    }
}