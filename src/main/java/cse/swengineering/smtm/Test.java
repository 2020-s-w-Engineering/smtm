package cse.swengineering.smtm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        String [] korName = {"들깨묵국", "치즈"};
        String [] engName = {"Radish Soup", "Cheese"};
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<korName.length; i++){
            sb.append("INSERT into MENU values (" + i + ", '" + engName[i] + "', 'FFFFFF', '" + korName[i] + "');\n");
        }
        String s = sb.toString();
        try {
            Files.write(Paths.get("./src/main/resources/data.txt"), s.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
        }
//        File file = new File("./src/main/resources/data.txt");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
        //
    }

}
