package org.woh.DTO;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.woh.DTO.interfaces.Strategy;

public class BorderColor extends Strategy {
    private IndexedColors color;
    @Override
    public void applyStrategy(SXSSFWorkbook workbook, SXSSFCell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.cloneStyleFrom(cell.getCellStyle());
        cellStyle.setBorderLeft(BorderStyle.THICK);
        cellStyle.setLeftBorderColor(getColor().getIndex());
        cellStyle.setBorderRight(BorderStyle.THICK);
        cellStyle.setRightBorderColor(getColor().getIndex());
        cellStyle.setBorderTop(BorderStyle.THICK);
        cellStyle.setTopBorderColor(getColor().getIndex());
        cellStyle.setBorderBottom(BorderStyle.THICK);
        cellStyle.setBottomBorderColor(getColor().getIndex());
        cell.setCellStyle(cellStyle);
    }

    public IndexedColors getColor() {
        return color;
    }

    public void setColor(IndexedColors color) {
        this.color = color;
    }
}
