package cse.swengineering.smtm;

import cse.swengineering.smtm.menus.Diet;
import cse.swengineering.smtm.menus.DietService;
import cse.swengineering.smtm.menus.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SmtmApplication {

    public static void main(String[] args) throws IOException, ParseException {
         SpringApplication.run(SmtmApplication.class);
//        System.out.println(str.contains("A"));
//        System.out.println(str.indexOf("메인"));
//        System.out.println(str.indexOf("kcal]") + "kcal]".length());
    }

}
