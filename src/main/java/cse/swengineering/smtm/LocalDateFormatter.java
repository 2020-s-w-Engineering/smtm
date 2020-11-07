package cse.swengineering.smtm;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

@Component
public class LocalDateFormatter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String s, Locale locale) throws ParseException {
        return LocalDate.parse(s);
    }

    @Override
    public String print(LocalDate date, Locale locale) {
        return date.toString();
    }
}
