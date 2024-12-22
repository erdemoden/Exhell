package org.woh;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.woh.Service.Cut.CutService;
import org.woh.Service.HellOptions;
import org.woh.Service.HellWriter;
import org.woh.Service.PaybackService;

import java.io.IOException;
import java.util.*;


/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IllegalAccessException {

        //HellWriter.deneme();
        List<CutService> cutServiceList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 500000; i++) {
            CutService cutService = new CutService();
            cutService.setCutId(generateRandomString(12, random));
            cutService.setCutAmount(random.nextInt(1000) + 1); // 1 ile 1000 arasında bir değer
            cutService.setName(generateRandomString(6, random));
            cutService.setSurname(generateRandomString(8, random));
            cutService.setAddress(generateRandomString(15, random));
            cutServiceList.add(cutService);
        }

        cutServiceList.forEach(System.out::println);
        HellOptions.builder()
                .withFileName("deneme")
                .withBackgroundColorByRowAndCell(3,0, IndexedColors.BLUE)
                .withFontColorByRowAndCell(3,0, IndexedColors.GREEN)
                .withBorderColorByRowAndCell(3, 0,IndexedColors.RED)
                .withFontColorByRowAndCell(3,1254,IndexedColors.RED)
                .withBorderColorByRowAndCell(3, 1254,IndexedColors.BLACK)
                .withBackgroundColorByRowAndCell(3, 1254, IndexedColors.AQUA)
                .withFontColorByRowAndCell(1, 80, IndexedColors.RED)
                .withBoldByRowAndCell(1, 80)
                .withFontColorByRowAndCell(1, 80, IndexedColors.GREEN)
                .withBorderColorByRowAndCell(1, 80, IndexedColors.RED)
                .writeToExHell(cutServiceList);
    }
    private static String generateRandomString(int length, Random random) {
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }
        return sb.toString();
    }
}
