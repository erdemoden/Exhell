package org.woh.DTO;

import org.apache.poi.ss.usermodel.IndexedColors;

public class Styles {
    private Boolean bold;
    private Boolean italic;
    private IndexedColors fontColor;
    private IndexedColors borderColor;
    private IndexedColors backgroundColor;

    public Boolean getItalic() {
        return italic;
    }

    public void setItalic(Boolean italic) {
        this.italic = italic;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public IndexedColors getFontColor() {
        return fontColor;
    }

    public void setFontColor(IndexedColors fontColor) {
        this.fontColor = fontColor;
    }

    public IndexedColors getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(IndexedColors borderColor) {
        this.borderColor = borderColor;
    }

    public IndexedColors getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(IndexedColors backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
