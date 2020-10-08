package cse.swengineering.smtm;

import cse.swengineering.smtm.menus.Diet;
import cse.swengineering.smtm.menus.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SmtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmtmApplication.class, args);
    }

}
