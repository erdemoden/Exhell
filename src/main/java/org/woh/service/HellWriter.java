package org.woh.service;


import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.woh.DTO.IndexObjectDTO;
import org.woh.DTO.interfaces.CellStrategy;
import org.woh.annotations.HellIndex;
import org.woh.exceptions.OhSheetException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class HellWriter {

    public static <T> void writeToExHell(List<T> objects, HellOptions options) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet();
        if(objects == null || objects.isEmpty()) {
            throw new OhSheetException("The list of objects cannot be null or empty.");
        }
        checkTitles(objects.get(0));
        Stream<IndexObjectDTO> indexObjectTitles = getAnnotatedTitles(objects.get(0));
        Stream<IndexObjectDTO> indexObjectDTOS = getAnnotatedFields(objects);

        indexObjectTitles.forEach(indexObjectDTO -> {
            SXSSFRow row = sheet.getRow(indexObjectDTO.getRow());
            if (row == null) {
                row = sheet.createRow(indexObjectDTO.getRow());
            }
            SXSSFCell cell = row.createCell(indexObjectDTO.getCell());
            implementStrategies(options, indexObjectDTO.getCell(), indexObjectDTO.getRow(), workbook, cell);
            cell.setCellValue(indexObjectDTO.getObject() != null ? indexObjectDTO.getObject().toString() : "null");
        });

        indexObjectDTOS.forEach(indexObjectDTO -> {
            SXSSFRow row = sheet.getRow(indexObjectDTO.getRow());
            if (row == null) {
                row = sheet.createRow(indexObjectDTO.getRow());
            }
            SXSSFCell cell = row.createCell(indexObjectDTO.getCell());
            implementStrategies(options, indexObjectDTO.getCell(), indexObjectDTO.getRow(), workbook, cell);
            cell.setCellValue(indexObjectDTO.getObject()!=null ? indexObjectDTO.getObject().toString() : "null");
        });
        File file = new File((options.getFileName() != null ? options.getFileName() : "file") + ".xlsx");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> Stream<IndexObjectDTO> getAnnotatedFields(List<T> objects) {
        return IntStream.range(0, objects.size())
                .boxed()
                .flatMap(index -> {
                    T obj = objects.get(index);
                    Class<?> clazz = obj.getClass();
                    return Arrays.stream(clazz.getDeclaredFields())
                            .filter(field -> field.isAnnotationPresent(HellIndex.class))
                            .map(field -> {
                                HellIndex annotation = field.getAnnotation(HellIndex.class);
                                Integer cellOrder = annotation.hellColumnOrder();
                                Integer rowOrder = annotation.hellRowOrder() + index;
                                IndexObjectDTO dto = new IndexObjectDTO();
                                dto.setCell(cellOrder);
                                dto.setRow(rowOrder);
                                try {
                                    field.setAccessible(true);
                                    dto.setObject(field.get(obj));
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                                return dto;
                            });
                })
                .sorted(Comparator.comparing(IndexObjectDTO::getRow));
    }
    private static <T> Stream<IndexObjectDTO> getAnnotatedTitles(T object){
        Class<?> clazz = object.getClass();
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(HellIndex.class))
                .map(field -> {
                    HellIndex annotation = field.getAnnotation(HellIndex.class);
                    IndexObjectDTO dto = new IndexObjectDTO();
                    if(!annotation.hellColumnName().isEmpty()){
                        Integer cellOrder = annotation.hellColumnOrder();
                        Integer rowOrder = 0;
                        dto.setCell(cellOrder);
                        dto.setRow(rowOrder);
                        dto.setObject(annotation.hellColumnName());
                    }
                    return dto;
                });
    }
    private static <T> void checkTitles(T object){
        Class<?> clazz = object.getClass();
        Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.isAnnotationPresent(HellIndex.class))
                .forEach(field -> {
                    HellIndex annotation = field.getAnnotation(HellIndex.class);
                    if(annotation.hellRowOrder() == 0 && !annotation.hellColumnName().isEmpty()){
                        throw new OhSheetException("HellIndex annotation for field " + field.getName() + " must have a hellRowOrder greater than 0 if hellColumnName is provided.");
                    }
                });
    }

    private static void implementStrategies(HellOptions options, Integer hellCell, Integer hellRow, SXSSFWorkbook workbook, SXSSFCell cell) {
        CellStrategy cellStrategy = options.getStrategyWithCell(hellCell);
        if (cellStrategy != null) {
            cellStrategy.applyStrategy(workbook, cell);
        }
        options.getStrategiesWithRowAndCell(hellCell, hellRow).forEach(strategy -> strategy.applyStrategy(workbook, cell));
    }
}
