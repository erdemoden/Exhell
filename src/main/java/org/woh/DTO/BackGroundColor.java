package org.woh.DTO;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.woh.DTO.interfaces.Strategy;

public class BackGroundColor extends Strategy {
    private IndexedColors color;
    @Override
    public void applyStrategy(SXSSFWorkbook workbook, SXSSFCell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.cloneStyleFrom(cell.getCellStyle());
        cellStyle.setFillForegroundColor(getColor().getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(cellStyle);
    }

    @Override
    public void applyCellStrategy(SXSSFWorkbook workbook, CellStyle cellStyle, Font font) {
        cellStyle.setFillForegroundColor(getColor().getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    public IndexedColors getColor() {
        return color;
    }

    public void setColor(IndexedColors color) {
        this.color = color;
    }
}
