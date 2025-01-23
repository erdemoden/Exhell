package org.woh.DTO.interfaces;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.woh.DTO.Styles;

import java.util.ArrayList;
import java.util.List;


public class CellStrategy {
    CellStyle cellStyle;
    Font font;
    Styles style;
    List<Strategy> strategies = new ArrayList<>();
    public void applyStrategy(SXSSFWorkbook workbook,SXSSFCell cell){
        if (cellStyle == null) {
            cellStyle = workbook.createCellStyle();
            cellStyle.cloneStyleFrom(cell.getCellStyle());
            if (font == null) {
                font = workbook.createFont();
            }
            strategies.forEach(strategy -> strategy.applyCellStrategy(workbook,cellStyle,font));
        }
        if(font!=null){
            cellStyle.setFont(font);
        }
        cell.setCellStyle(cellStyle);
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Styles getStyle() {
        return style;
    }

    public void setStyle(Styles style) {
        this.style = style;
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
    }
}
