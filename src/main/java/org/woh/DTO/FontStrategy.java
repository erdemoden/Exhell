package org.woh.DTO;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.woh.DTO.interfaces.Strategy;

public class FontStrategy extends Strategy{
    private Boolean bold;
    private Boolean italic;
    private IndexedColors fontColor;
    @Override
    public void applyStrategy(SXSSFWorkbook workbook, SXSSFCell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.cloneStyleFrom(cell.getCellStyle());
        Font font = workbook.createFont();
        if(getFontColor()!=null){
            font.setColor(getFontColor().getIndex());
        }
        if(getBold()!=null){
            font.setBold(getBold());
        }
        if(getItalic()!=null){
            font.setItalic(getItalic());
        }
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

    @Override
    public void applyCellStrategy(SXSSFWorkbook workbook,CellStyle cellStyle,Font font) {
        if(font == null){
            font = workbook.createFont();
        }
        if(getFontColor()!=null){
            font.setColor(getFontColor().getIndex());
        }
        if(getBold()!=null){
            font.setBold(getBold());
        }
        if(getItalic()!=null){
            font.setItalic(getItalic());
        }
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public Boolean getItalic() {
        return italic;
    }

    public void setItalic(Boolean italic) {
        this.italic = italic;
    }

    public IndexedColors getFontColor() {
        return fontColor;
    }

    public void setFontColor(IndexedColors fontColor) {
        this.fontColor = fontColor;
    }
}
