package cse.swengineering.smtm.menus;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DietService {

    private List<Diet> dietList = new ArrayList<>();

    public DietService() throws IOException {
        getDietInformation();
    }

    public List<Diet> getDietList() {
        return dietList;
    }

    public void setDietList(List<Diet> dietList) {
        this.dietList = dietList;
    }

    public void getDietInformation() throws IOException {
        final String RE_INCLUDE_KOREAN = ".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*";
        final String RE_KOREAN = "^[가-힣\\s]*$";
        final String RE_DAY = "[0-9]+\\([가-힣]\\)";

        URL url = new URL("http://dorm.cnu.ac.kr/html/kr/sub03/sub03_0304.html");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuilder builder = new StringBuilder();

        // 태그까지 모조리 긁어오기
        while ((line = br.readLine()) != null) {
            if(line.contains("<div class=\"diet_info\">")) {
                builder.append(line);
                line = br.readLine();
                builder.append(line);
            }
            if(line.contains("<table class=\"default_view diet_table\">"))
                while(!(line = br.readLine()).contains("</table>"))
                    if(line.matches(RE_INCLUDE_KOREAN))
                        builder.append(line);
        }

        // <"diet_info">에 시작 날짜와 끝 날짜가 있다
        String rawData = builder.toString();
        int start = rawData.indexOf("<td>");
        int end = rawData.indexOf("(");
        int firstDay = Integer.parseInt(rawData.substring(start+4, end)); // 식단표의 첫날 계산
        // 시작 날짜와 끝 날짜 얻기
        start = rawData.indexOf("<p>202");
        String stringDate = rawData.substring(start + 3, start + 13);
//        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(split[0]);

        // <br>태그 기준으로 한 번 잘라서 다듬고
        String[] splitByTag = rawData.split("<br />");
        // 다시 합친 뒤
        String data = convertObjectArrayToString(splitByTag, ",");
        // 날짜별로 다 쪼개기
        String[] splitByDay = data.split(RE_DAY);

        // 날짜마다
        for(int i=1; i<=7; i++){
            Diet diet = new Diet();
            if(i != 1)
                stringDate = addDay(stringDate);
            diet.setDate(LocalDate.parse(stringDate));
            // 메뉴별로 다 쪼개서
            String[] splitByMenu = splitByDay[i].split(",");
            for (String menuStr : splitByMenu){
                // 메뉴 이름만 저장하기
                if(menuStr.matches(RE_KOREAN))
                    diet.getMenus().add(new Menu(menuStr));
            }
            dietList.add(diet);
        }
    }

    // string array to string
    private static String convertObjectArrayToString(Object[] arr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : arr)
            sb.append(obj.toString()).append(delimiter);
        return sb.substring(0, sb.length() - 1);
    }

    private String addDay(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(date));
        }catch(ParseException e){
            e.printStackTrace();
        }
        //Incrementing the date by 1 day
        c.add(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(c.getTime());
    }
}
