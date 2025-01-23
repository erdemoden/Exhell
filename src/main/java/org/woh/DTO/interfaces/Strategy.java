package org.woh.DTO.interfaces;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.HashMap;
import java.util.Map;

public abstract class Strategy {
    private Map<Integer, Integer> rowCellMap = new HashMap<>();

    public abstract void applyStrategy(SXSSFWorkbook workbook, SXSSFCell cell);

    public abstract void applyCellStrategy(SXSSFWorkbook workbook,CellStyle cellStyle,Font font);
    public Map<Integer, Integer> getRowCellMap() {
        return rowCellMap;
    }

    public void setRowCellMap(Map<Integer, Integer> rowCellMap) {
        this.rowCellMap = rowCellMap;
    }
}
