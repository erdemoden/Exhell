package org.woh;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.woh.Service.Cut.CutService;
import org.woh.Service.HellOptions;
import org.woh.Service.HellWriter;
import org.woh.Service.PaybackService;

import java.io.IOException;
import java.util.*;

import static org.woh.annotations.AnnotationProcessor.getAllClasses;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IllegalAccessException {
        List<Class<?>> classes = null;
        try {
            classes = getAllClasses();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (Class<?> cls : classes) {
            System.out.println("Found class: " + cls.getName());
        }
        PaybackService paybackService = new PaybackService();
        PaybackService paybackService1 = new PaybackService();
        List<PaybackService> paybackServices = new LinkedList<>(Arrays.asList(paybackService, paybackService1));


        //HellWriter.deneme();
        List<CutService> cutServiceList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 1; i++) {
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
                .withBackgroundColorByRowAndCell(3,1, IndexedColors.BLUE)
                .withFontColorByRowAndCell(3,1, IndexedColors.GREEN)
                .withBorderColorByRowAndCell(3, 1,IndexedColors.RED)
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
