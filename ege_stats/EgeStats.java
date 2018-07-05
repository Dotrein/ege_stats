package ege_stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class EgeStats {
    public static void main(String[] args) throws IOException {
        String urlAddress1Priority = "https://cabinet.spbu.ru/Lists/1k_EntryLists/list_900f0ad3-62a3-42b8-916f-baab8808930a.html#0fa5f7cb-69c6-41e9-ab92-a377b4ce6c81";
        String urlAddress2Priority = "https://cabinet.spbu.ru/Lists/1k_EntryLists/list_ecbc3a5c-44f6-4bb4-bb9c-ddd1615cc3a6.html#8ad38e9e-903f-424e-9026-eb4205beb1a6";
        String urlAddress3Priority = "https://cabinet.spbu.ru/Lists/1k_EntryLists/list_60725d3e-3d0e-4fc5-8051-1dfc11c13663.html#91a0cc93-d3a2-4eb1-8718-224b7d70590a";
        int priority = 1;
        countAverageScore(urlAddress1Priority, priority);
        priority = 2;
        countAverageScore(urlAddress2Priority, priority);
        priority = 3;
        countAverageScore(urlAddress3Priority, priority);
    }
    public static void countAverageScore (String urlAddress, int priority) throws NullPointerException, IOException {
        URL url = new URL(urlAddress);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;
        int gordienko = 0;
        int totalScoreGordienko = 0;
        int kcpInt = 0;
        int count = 0;
        int math = 0;
        int countMath = 0;
        int inform = 0;
        int countInform = 0;
        int countRus = 0;
        int rus = 0;
        int totalScore = 0;
        int totalScoreInt = 0;
        int countTotalScore = 0;
        int mathInt = 0;
        int informInt = 0;
        int rusInt = 0;
        int mathYes = 0;
        int informYes = 0;
        int rusYes = 0;
        int totalScoreYes = 0;
        int countYes = 0;
        int countMathYes = 0;
        int countInformYes = 0;
        int countRusYes = 0;
        ArrayList<Integer> scoreOfYes = new ArrayList<>();
        while (!(inputLine = reader.readLine()).contains("КЦП по конкурсу"))
            ;
        String kcpString = inputLine;
        kcpString = kcpString.replaceAll("<b>КЦП по конкурсу:</b>", "");
        kcpString = kcpString.replaceAll("<br />", "");
        kcpString = kcpString.replaceAll(" ", "");
        kcpInt = Integer.parseInt(kcpString);
        while (!(inputLine = reader.readLine()).equals("<tbody>"))
            ;

        while (inputLine != null){
            for (int i = 0; i < 19; i++) {
                inputLine = reader.readLine();
                try{
                if (i == 3 && inputLine.contains("Гордиенко")){ //Почему-то условие выполняется всегда
                    gordienko = 1;
                }
                }
                catch (Exception e){
                    System.out.println("");
                }
                if (i == 7){
                    String totalScoreString = inputLine;
                    try{
                        totalScoreString = totalScoreString.replaceAll(" ", "");
                        totalScoreString = totalScoreString.replaceAll("<td>", "");
                        totalScoreString = totalScoreString.replaceAll("</td>", "");
                        totalScoreString = totalScoreString.replace(",", ".");
                        Double totalScoreDouble = Double.parseDouble(totalScoreString);
                        totalScoreInt = totalScoreDouble.intValue();
                        totalScore = totalScore + totalScoreInt;
                        countTotalScore++;
                        if (gordienko == 1){
                            scoreOfYes.add(totalScoreInt); //из-за этого всегда сюда добавляются значения
                            totalScoreGordienko = totalScoreInt;
                        }
                    }
                    catch (Exception e){
                        System.out.print("");
                    }
                }
                if (i == 9) { //Math
                    String mathString = inputLine;
                    try {
                        mathString = mathString.replaceAll("<td>", "");
                        mathString = mathString.replaceAll("</td>", "");
                        mathString = mathString.replaceAll(" ", "");
                        Double mathDouble = Double.parseDouble(mathString);
                        mathInt = mathDouble.intValue();
                        math = math + mathInt;
                        countMath++;
                    } catch (Exception e) {
                        System.out.print("");
                    }
                }

                if (i == 10) { //Informatics
                    String informString = inputLine;

                    try {
                        informString = informString.replaceAll(" ", "");
                        informString = informString.replaceAll("<td>", "");
                        informString = informString.replaceAll("</td>", "");
                        Double informDouble = Double.parseDouble(informString);
                        informInt = informDouble.intValue();
                        inform = inform + informInt;
                        countInform++;
                    } catch (Exception e) {
                        System.out.print("");
                    }
                }

                if (i == 11) { //Rus
                    String rusString = inputLine;
                    try {
                        rusString = rusString.replaceAll(" ", "");
                        rusString = rusString.replaceAll("<td>", "");
                        rusString = rusString.replaceAll("</td>", "");
                        rusString = rusString.replaceAll("(О)", "");
                        Double rusDouble = Double.parseDouble(rusString);
                        rusInt = rusDouble.intValue();
                        rus = rus + rusInt;
                        countRus++;
                    }
                    catch (Exception e) {
                        System.out.print("");
                    }
                }
            if (i == 13){
                    String isOriginal = inputLine;
                    try {
                        isOriginal = isOriginal.replaceAll("<td>", "");
                        isOriginal = isOriginal.replaceAll("</td>", "");
                        isOriginal = isOriginal.replaceAll(" ", "");
                        if (isOriginal.equals("Да")){
                            scoreOfYes.add(totalScoreInt);
                            totalScoreYes = totalScoreYes + totalScoreInt;
                            mathYes = mathYes + mathInt;
                            informYes = informYes + informInt;
                            rusYes = rusYes + rusInt;
                            countYes++;
                        }

                    }
                    catch (Exception e){
                        System.out.print("");
                    }
                }
            }
        }
        Collections.sort(scoreOfYes);
        Collections.reverse(scoreOfYes);
        int place = scoreOfYes.indexOf(totalScoreGordienko);

        System.out.print(priority);
        System.out.println(" приоритет:");
        System.out.print("Количество бюджетных мест - ");
        System.out.println(kcpInt);
        System.out.print("Средний балл по всем предметам - ");
        System.out.println((double) totalScore/countTotalScore);
        System.out.print("Средний балл по математике - ");
        System.out.println((double) math/countMath);
        System.out.print("Средний балл по информатике - ");
        System.out.println((double) inform/countInform);
        System.out.print("Средний балл по русскому языку - ");
        System.out.println((double) rus/countRus);
        System.out.println("");

        System.out.print("Средний балл по всем предметам среди подавших оригинал - ");
        System.out.println((double) totalScoreYes/countYes);
        System.out.print("Средний балл по математике среди подавших оригинал - ");
        System.out.println((double) mathYes/countYes);
        System.out.print("Средний балл по информатике среди подавших оригинал - ");
        System.out.println((double) informYes/countYes);
        System.out.print("Средний балл по русскому языку среди подавших оригинал - ");
        System.out.println((double) rusYes/countYes);
        System.out.print("Место среди подавших оригинал - ");
        System.out.println(place);
        System.out.println("");

        reader.close();
    }
}