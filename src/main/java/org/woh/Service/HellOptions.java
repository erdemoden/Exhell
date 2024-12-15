package org.woh.Service;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.woh.DTO.BackGroundColor;
import org.woh.DTO.BorderColor;
import org.woh.DTO.FontStrategy;
import org.woh.DTO.interfaces.Strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HellOptions {

    private String fileName;
    private String password;
    private List<BackGroundColor> backgroundColorByRowAndCell;
    private List<FontStrategy> fontColorByRowAndCell;
    private List<BorderColor> borderColorByRowAndCell;
    private List<FontStrategy> boldByRowAndCell;
    private List<FontStrategy> italicByRowAndCell;
    private Map<Integer,IndexedColors> backgroundColorByRow;
    private HellOptions() {
        backgroundColorByRowAndCell = new ArrayList<>();
        fontColorByRowAndCell = new ArrayList<>();
        borderColorByRowAndCell = new ArrayList<>();
        boldByRowAndCell = new ArrayList<>();
        italicByRowAndCell = new ArrayList<>();
    }
    public static Options builder() {
        return new Options();
    }

    public static class Options {
        private HellOptions hellOptions = new HellOptions();
        public Options() {

        }
        public Options withFileName(String fileName) {
            hellOptions.fileName = fileName;
            return this;
        }
        public Options withPassword(String password) {
            hellOptions.password = password;
            return this;
        }
        public Options withBackgroundColorByRowAndCell(Integer hellCell, Integer hellRow,IndexedColors color) {
            BackGroundColor backGroundColor = new BackGroundColor();
            Map<Integer,Integer> rowCellMap = new HashMap<>();
            rowCellMap.put(hellRow,hellCell);
            backGroundColor.setRowCellMap(rowCellMap);
            backGroundColor.setColor(color);
            hellOptions.backgroundColorByRowAndCell.add(backGroundColor);
            return this;
        }
        public Options withFontColorByRowAndCell(Integer hellCell, Integer hellRow,IndexedColors color) {
            FontStrategy fontStrategy = new FontStrategy();
            Map<Integer,Integer> rowCellMap = new HashMap<>();
            rowCellMap.put(hellRow,hellCell);
            fontStrategy.setRowCellMap(rowCellMap);
            fontStrategy.setFontColor(color);
            hellOptions.fontColorByRowAndCell.add(fontStrategy);
            return this;
        }
        public Options withBorderColorByRowAndCell(Integer hellCell, Integer hellRow,IndexedColors color) {
            BorderColor borderColor = new BorderColor();
            Map<Integer,Integer> rowCellMap = new HashMap<>();
            rowCellMap.put(hellRow,hellCell);
            borderColor.setRowCellMap(rowCellMap);
            borderColor.setColor(color);
            hellOptions.borderColorByRowAndCell.add(borderColor);
            return this;
        }
        public Options withBoldByRowAndCell(Integer hellCell,Integer hellRow) {
            FontStrategy fontStrategy = new FontStrategy();
            Map<Integer,Integer> rowCellMap = new HashMap<>();
            rowCellMap.put(hellRow,hellCell);
            fontStrategy.setRowCellMap(rowCellMap);
            fontStrategy.setBold(true);
            hellOptions.boldByRowAndCell.add(fontStrategy);
            return this;
        }
        public Options withItalicByRowAndCell(Integer hellCell,Integer hellRow,Boolean isItalic) {
            FontStrategy fontStrategy = new FontStrategy();
            Map<Integer,Integer> rowCellMap = new HashMap<>();
            rowCellMap.put(hellRow,hellCell);
            fontStrategy.setRowCellMap(rowCellMap);
            fontStrategy.setItalic(isItalic);
            hellOptions.italicByRowAndCell.add(fontStrategy);
            return this;
        }
        public <T> void writeToExHell(List<T> objects) throws IllegalAccessException {
            HellWriter.writeToExHell(objects,hellOptions);
        }
        public HellOptions build() {
            return hellOptions;
        }

    }

    public String getFileName() {
        return fileName;
    }

    public String getPassword() {
        return password;
    }

    public List<BackGroundColor> getBackgroundColorByRowAndCell() {
        return backgroundColorByRowAndCell;
    }

    public List<FontStrategy> getFontColorByRowAndCell() {
        return fontColorByRowAndCell;
    }

    public List<BorderColor> getBorderColorByRowAndCell() {
        return borderColorByRowAndCell;
    }

    public List<FontStrategy> getBoldByRowAndCell() {
        return boldByRowAndCell;
    }

    public List<FontStrategy> getItalicByRowAndCell() {
        return italicByRowAndCell;
    }
    public List<Strategy> getStrategiesWithRowAndCell(Integer hellCell, Integer hellRow) {
        List<Strategy> strategies = getAllStrategies();
        strategies.removeIf(strategy -> !strategy.getRowCellMap().containsKey(hellRow) || !strategy.getRowCellMap().containsValue(hellCell));
        return strategies;
    }
    public List<Strategy> getAllStrategies() {
        List<Strategy> strategies = new ArrayList<>();
        strategies.addAll(backgroundColorByRowAndCell);
        strategies.addAll(fontColorByRowAndCell);
        strategies.addAll(borderColorByRowAndCell);
        strategies.addAll(boldByRowAndCell);
        strategies.addAll(italicByRowAndCell);
        return strategies;
    }
}
