package ege_stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class EgeStats {
    public static void main(String[] args) throws IOException {
        String urlAddress1Priority = "https://cabinet.spbu.ru/Lists/1k_EntryLists/list_900f0ad3-62a3-42b8-916f-baab8808930a.html#0fa5f7cb-69c6-41e9-ab92-a377b4ce6c81";
        String urlAddress2Priority = "https://cabinet.spbu.ru/Lists/1k_EntryLists/list_ecbc3a5c-44f6-4bb4-bb9c-ddd1615cc3a6.html#8ad38e9e-903f-424e-9026-eb4205beb1a6";
        String urlAddress3Priority = "https://cabinet.spbu.ru/Lists/1k_EntryLists/list_60725d3e-3d0e-4fc5-8051-1dfc11c13663.html#91a0cc93-d3a2-4eb1-8718-224b7d70590a";
        int priority = 1;
        AverageScore(urlAddress1Priority, priority);
        priority = 2;
        AverageScore(urlAddress2Priority, priority);
        priority = 3;
        AverageScore(urlAddress3Priority, priority);
    }
        public static void AverageScore (String urlAddress, int priority) throws IOException {
        URL url = new URL(urlAddress);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;
        int kcp = 0;
        int count = 0;
        int math = 0;
        int countMath = 0;
        int inform = 0;
        int countInform = 0;
        int countRus = 0;
        int rus = 0;
        while (!(inputLine = reader.readLine()).equals("<tbody>"))
            ;

        while (inputLine != null){
        for (int i = 0; i < 19; i++) {
            inputLine = reader.readLine();
            if (i == 9) { //Math
                String mathString = inputLine;

                try {
                    mathString = mathString.replaceAll(" ", "");
                    mathString = mathString.replaceAll("<td>", "");
                    mathString = mathString.replaceAll("</td>", "");
                    Double mathDouble = Double.parseDouble(mathString);
                    int mathInt = mathDouble.intValue();
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
                    int informInt = informDouble.intValue();
                    inform = inform + informInt;
                    countInform++;
                } catch (Exception e) {
                    System.out.print("");
                }
            }

            if (i == 11) { //Rus
                String rusString = inputLine;
                int rusInt;
                try {
                    rusString = rusString.replaceAll(" ", "");
                    rusString = rusString.replaceAll("<td>", "");
                    rusString = rusString.replaceAll("</td>", "");
                    Double rusDouble = Double.parseDouble(rusString);
                    rusInt = rusDouble.intValue();
                    rus = rus + rusInt;
                    countRus++;
                }
                catch (Exception e) {
                    System.out.print("");
                }
            }
            /*if (i == 13){
                String isOriginal = inputLine;
                isOriginal = isOriginal.replaceAll("<td>", "");
                isOriginal = isOriginal.replaceAll("</td>", "");
                if (isOriginal.equals("Нет")){
                    math = math - mathInt;
                    inform = inform - informInt;
                    rus = rus - rusInt;
                }
            }*/



        }

        }
            System.out.print("Средние баллы по ");
            System.out.print(priority);
            System.out.println(" приоритету:");
            System.out.print("Средний балл по математике - ");
            System.out.println((double) math/countMath);
            System.out.print("Средний балл по информатике - ");
            System.out.println((double) inform/countInform);
            System.out.print("Средний балл по русскому языку - ");
            System.out.println((double) rus/countRus);
            System.out.println("");

        reader.close();
    }
}