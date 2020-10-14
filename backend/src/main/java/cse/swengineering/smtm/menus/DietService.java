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
import java.util.*;
import java.util.stream.Collectors;

// todo 너무 보기 드러움, 한글이 있는 것만 짤라서 영어이름만 있는 메뉴는 가져오지 않음

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
        final String RE_INCLUDE_ENGLISH = ".*[a-zA-Z]+.*";
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
            if (line.contains("<div class=\"diet_info\">")) {
                builder.append(line);
                line = br.readLine();
                builder.append(line);
            }
            if (line.contains("<table class=\"default_view diet_table\">"))
                while (!(line = br.readLine()).contains("</table>"))
                    if (line.matches(RE_INCLUDE_KOREAN) || line.matches(RE_INCLUDE_ENGLISH))
                        builder.append(line);
        }

        // <"diet_info">에 시작 날짜와 끝 날짜가 있다
        String rawData = builder.toString();
        // 무의미한 공백 제거
        rawData = rawData.replace("\t", "");
        // 시작 날짜와 끝 날짜 얻기
        int dateIndex = rawData.indexOf("<p>202");
        String stringDate = rawData.substring(dateIndex + 3, dateIndex + 13);

        // <br>태그 기준으로 한 번 잘라서 다듬고
        String[] splitByTag = rawData.split("<br />");
//        for(String str : splitByTag)
//            System.out.println(str);

        // 다시 합친 뒤
        String data = convertObjectArrayToString(splitByTag, ".");
//        System.out.println(data);
        // 날짜별로 다 쪼개기
        String[] splitByDay = data.split(RE_DAY);
        for(String str : splitByDay)
            System.out.println(str);
        List<String> strList = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            // 메뉴별로 다 쪼개서
            String[] splitByMenu = splitByDay[i].split("\\.");
            for (String menuStr : splitByMenu) {
                if (menuStr.contains("메인A") || menuStr.contains("메인 C") || menuStr.contains("메인C")) { // 메인A or 메인C
                    //                    diet.getMenus().add(new Menu(menuStr.substring(menuStr.indexOf("메인"), menuStr.indexOf("kcal]") + "kcal]".length())));
                    String mainAndCalories = menuStr.substring(menuStr.indexOf("메인"), menuStr.indexOf("kcal]") + "kcal]".length());
                    mainAndCalories = mainAndCalories.replace(" ", "");
                    String replace = mainAndCalories.replace("[", " ");
                    replace = replace.replace("]", "");
                    String[] split = replace.split(" ");
                    String main = split[0];
                    String cal = split[1];
                    strList.add(main);
                    strList.add(cal);
                } else if (menuStr.matches(RE_INCLUDE_KOREAN) || menuStr.matches(RE_INCLUDE_ENGLISH)) {
                    if (menuStr.startsWith(" "))
                        menuStr = menuStr.substring(findFirstChar(menuStr));
                    //                    diet.getMenus().add(new Menu(menuStr));
                    strList.add(menuStr);
                }
            }
        }

        strList = strList.stream().filter(c -> {
            if (c.contains("<") || c.contains(">"))
                return false;
            return true;
        }).collect(Collectors.toList());

//        strList.forEach(System.out::println);

        // 아 아침점심저녁 메인A 최소 3번
        boolean flag = false;
        ListIterator<String> iterator = strList.listIterator();
        Diet diet;
        int countMainA = 0;
        while(iterator.hasNext()) {
            if (iterator.next().equals("메인A")) {
                String name = null;
                diet = new Diet();
                diet.setDate(LocalDate.parse(stringDate));
                stringDate = addDay(stringDate);
                while (iterator.hasNext() && !(name = iterator.next()).contains("메인")) {
                    if (name.contains("kcal")) {
                        name = name.substring(0, name.indexOf("k"));
                        diet.setCalories(name);
                    }
                    else
                        diet.getMainA().add(new Menu(name));
                }
                if (iterator.hasNext() && name.equals("메인C")) {
                    while (!(name = iterator.next()).contains("메인")) {
                        if (name.contains("kcal")) {
                            name = name.substring(0, name.indexOf("k"));
                            diet.setCalories(name);
                        } else
                            diet.getMainC().add(new Menu(name));
                    }
                }
                dietList.add(diet);
            }
        }
//
//        for(Diet d : dietList){
//            d.setDate(LocalDate.parse(stringDate));
//            stringDate = addDay(stringDate);
//        }

    }
//        dietList.forEach(System.out::println);


    private int findFirstChar(String str){
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) != ' ')
                return i;
        }
        return -1;
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
