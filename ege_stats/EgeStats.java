package ege_stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class EgeStats {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://cabinet.spbu.ru/Lists/1k_EntryLists/list_900f0ad3-62a3-42b8-916f-baab8808930a.html#0fa5f7cb-69c6-41e9-ab92-a377b4ce6c81");
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

                try {
                    rusString = rusString.replaceAll(" ", "");
                    rusString = rusString.replaceAll("<td>", "");
                    rusString = rusString.replaceAll("</td>", "");
                    Double rusDouble = Double.parseDouble(rusString);
                    int rusInt = rusDouble.intValue();
                    rus = rus + rusInt;
                    countRus++;
                }
                catch (Exception e) {
                    System.out.print("");
                }
            }
        }
            double averMath = (double) math/countMath;
            double averInform = (double) inform/countInform;
            double averRus = (double) rus/countRus;
            printAverageMath(averMath);
            printAverageInform(averInform);
            printAverageRus(averRus);
        }
        reader.close();
    }
    public static void printAverageMath (double averageMath) {
		System.out.print("Средний балл по математике - ");
        System.out.println(averageMath);
    }
    public static void printAverageInform (double averageInform) {
		System.out.print("Средний балл по информатике - ");
        System.out.println(averageInform);
    }
    public static void printAverageRus (double averageRus) {
		System.out.print("Средний балл по русскому языку - ");
        System.out.println(averageRus);
    }
}