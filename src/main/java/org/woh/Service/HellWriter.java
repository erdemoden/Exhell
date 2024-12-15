package org.woh.Service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.woh.DTO.IndexObjectDTO;
import org.woh.DTO.StrategiesDTO;
import org.woh.DTO.interfaces.Strategy;
import org.woh.annotations.HellIndex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import static org.woh.annotations.AnnotationProcessor.getAllClasses;


public class HellWriter {
    private List<Class<?>> classes;

    public HellWriter() throws Exception {
        classes = getAllClasses();
    }

    public static <T> void writeToExHell(List<T> objects, HellOptions options) throws IllegalAccessException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        int rowNumber = 0;
        SXSSFSheet sheet = workbook.createSheet();
        List<IndexObjectDTO> indexObjectDTOS = getAnnotatedFields(objects);
        List<StrategiesDTO> strategiesDTOS = new LinkedList<>();

        indexObjectDTOS.forEach(indexObjectDTO -> {
            SXSSFRow row = sheet.getRow(indexObjectDTO.getRow());
            if (row == null) {
                row = sheet.createRow(indexObjectDTO.getRow());
            }
            SXSSFCell cell = row.createCell(indexObjectDTO.getCell());
            implementStrategies(options, indexObjectDTO.getCell(), indexObjectDTO.getRow(), workbook,cell);
            cell.setCellValue(indexObjectDTO.getObject().toString());
        });
        File file = new File((options.getFileName()!=null?options.getFileName():"file") + ".xlsx");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> List<IndexObjectDTO> getAnnotatedFields(List<T> objects) throws IllegalAccessException {
        List<IndexObjectDTO> fields = new LinkedList<>();
        Integer rowIncrement = 0;
        for (T obj : objects) {
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(HellIndex.class)) {
                    Integer cellOrder = field.getAnnotation(HellIndex.class).hellColumnOrder();
                    Integer rowOrder = field.getAnnotation(HellIndex.class).hellRowOrder()+rowIncrement;
                    IndexObjectDTO indexObjectDTO = new IndexObjectDTO();
                    indexObjectDTO.setCell(cellOrder);
                    indexObjectDTO.setRow(rowOrder);
                    field.setAccessible(true);
                    indexObjectDTO.setObject(field.get(obj));
                    fields.add(indexObjectDTO);
                    //field.setAccessible(false);
                }
            }
            rowIncrement++;
        }
        fields.sort(Comparator.comparing(IndexObjectDTO::getRow));
        return fields;
    }

    public static void deneme() {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        for (int i = 0; i < 150; i++) {
                Row row = sheet.createRow(i);
                Cell cell = row.createCell(0);
                cell.setCellValue("Satır " + i);

        }
        Row row = sheet.createRow(15);
        Cell cell = row.createCell(5);
        CellStyle backgroundStyle = workbook.createCellStyle();

        backgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        backgroundStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(backgroundStyle);
        File file = new File("deneme.xlsx");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void implementStrategies(HellOptions options, Integer hellCell , Integer hellRow,SXSSFWorkbook workbook, SXSSFCell cell) {
        options.getStrategiesWithRowAndCell(hellCell,hellRow).forEach(strategy -> strategy.applyStrategy(workbook,cell));
    }

    private static List<Strategy> getStrategies(HellOptions options) {
        List<Strategy> strategies = new LinkedList<>();
        options.getBackgroundColorByRowAndCell().forEach(backGroundColor -> strategies.add(backGroundColor));
        options.getBorderColorByRowAndCell().forEach(borderColor -> strategies.add(borderColor));
        options.getBoldByRowAndCell().forEach(fontStrategy -> strategies.add(fontStrategy));
        options.getItalicByRowAndCell().forEach(fontStrategy -> strategies.add(fontStrategy));
        options.getFontColorByRowAndCell().forEach(fontStrategy -> strategies.add(fontStrategy));
        return strategies;
    }

   /* private static void writeToHellWithOptions(SXSSFCell hell, HellOptions options) {
        if (options.getBackgroundColor().containsKey(hell.getColumnIndex())) {
            hell.getCellStyle().setFillBackgroundColor(options.getBackgroundColor().get(hell.getColumnIndex()).getIndex());
        }
        if (options.getFontColor().containsKey(hell.getColumnIndex())) {
            hell.getCellStyle().setFillForegroundColor(options.getFontColor().get(hell.getColumnIndex()).getIndex());
        }
        if (options.getBorderColor().containsKey(hell.getColumnIndex())) {
            hell.getCellStyle().setFillBackgroundColor(options.getBorderColor().get(hell.getColumnIndex()).getIndex());
        }
        if (options.getIsBold().containsKey(hell.getColumnIndex())) {

            hell.getCellStyle().setFont();
        }
        if (options.getIsItalic().containsKey(hell.getColumnIndex())) {
            hell.getCellStyle().setFillBackgroundColor(options.getIsItalic().get(hell.getColumnIndex()) ? (short) 1 : (short) 0);
        }

    }*/
}
