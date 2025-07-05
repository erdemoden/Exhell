package org.woh.service;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.woh.DTO.BackGroundColor;
import org.woh.DTO.BorderColor;
import org.woh.DTO.FontStrategy;
import org.woh.DTO.Styles;
import org.woh.DTO.interfaces.CellStrategy;
import org.woh.DTO.interfaces.Strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HellOptions {

    private String fileName;
    private List<Strategy> strategies;
    private Map<Integer, CellStrategy> cellStrategies;

    private HellOptions() {
        strategies = new ArrayList<>();
        cellStrategies = new HashMap<>();
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

        public Options withBackgroundColorByRowAndCell(Integer hellCell, Integer hellRow, IndexedColors color) {
            BackGroundColor backGroundColor = new BackGroundColor();
            Map<Integer, Integer> rowCellMap = new HashMap<>();
            rowCellMap.put(hellRow, hellCell);
            backGroundColor.setRowCellMap(rowCellMap);
            backGroundColor.setColor(color);
            hellOptions.strategies.add(backGroundColor);
            return this;
        }
        public Options withBackgroundColorByCell(Integer hellCell, IndexedColors color) {
            CellStrategy cellStrategy = hellOptions.cellStrategies.get(hellCell);
            BackGroundColor backGroundColor = new BackGroundColor();
            backGroundColor.setColor(color);
            if(cellStrategy == null){
                cellStrategy = new CellStrategy();
                Styles styles = new Styles();
                styles.setBackgroundColor(color);
                cellStrategy.setStyle(styles);
                cellStrategy.getStrategies().add(backGroundColor);
                hellOptions.cellStrategies.put(hellCell,cellStrategy);
                return this;
            }
            cellStrategy.getStrategies().add(backGroundColor);
            cellStrategy.getStyle().setBackgroundColor(color);
            return this;
        }

        public Options withFontColorByRowAndCell(Integer hellCell, Integer hellRow, IndexedColors color) {
            FontStrategy fontStrategy = hellOptions.strategies.stream().
                    filter(strategy -> strategy instanceof FontStrategy)
                    .map(strategy -> (FontStrategy) strategy)
                    .filter(strategy-> strategy.getRowCellMap().containsKey(hellRow) && strategy.getRowCellMap().containsValue(hellCell)).findFirst().orElse(null);
            if(fontStrategy != null) {
                fontStrategy.setFontColor(color);
                return this;
            }
            else{
            fontStrategy = new FontStrategy();
            Map<Integer, Integer> rowCellMap = new HashMap<>();
            rowCellMap.put(hellRow, hellCell);
            fontStrategy.setRowCellMap(rowCellMap);
            fontStrategy.setFontColor(color);
            hellOptions.strategies.add(fontStrategy);
            return this;
        }
        }
        public Options withFontColorByCell(Integer hellCell, IndexedColors color) {
            CellStrategy cellStrategy = hellOptions.cellStrategies.get(hellCell);
            if(cellStrategy == null){
                FontStrategy fontStrategy = new FontStrategy();
                cellStrategy = new CellStrategy();
                Styles styles = new Styles();
                fontStrategy.setFontColor(color);
                styles.setFontColor(color);
                cellStrategy.setStyle(styles);
                cellStrategy.getStrategies().add(fontStrategy);
                hellOptions.cellStrategies.put(hellCell,cellStrategy);
                return this;
            }
            cellStrategy.getStrategies().forEach(strategy -> {
                if(strategy instanceof FontStrategy){
                    ((FontStrategy) strategy).setFontColor(color);
                }
            });
            cellStrategy.getStyle().setFontColor(color);
            return this;
        }

        public Options withBorderColorByRowAndCell(Integer hellCell, Integer hellRow, IndexedColors color) {
            BorderColor borderColor = new BorderColor();
            Map<Integer, Integer> rowCellMap = new HashMap<>();
            rowCellMap.put(hellRow, hellCell);
            borderColor.setRowCellMap(rowCellMap);
            borderColor.setColor(color);
            hellOptions.strategies.add(borderColor);
            return this;
        }

        public Options withBorderColorByCell(Integer hellCell, IndexedColors color) {
            CellStrategy cellStrategy = hellOptions.cellStrategies.get(hellCell);
            BorderColor borderColor = new BorderColor();
            borderColor.setColor(color);
            if(cellStrategy == null){
                cellStrategy = new CellStrategy();
                Styles styles = new Styles();
                styles.setBorderColor(color);
                cellStrategy.setStyle(styles);
                cellStrategy.getStrategies().add(borderColor);
                hellOptions.cellStrategies.put(hellCell,cellStrategy);
                return this;
            }
            cellStrategy.getStrategies().add(borderColor);
            cellStrategy.getStyle().setBorderColor(color);
            return this;
        }

        public Options withBoldByRowAndCell(Integer hellCell, Integer hellRow) {
            FontStrategy fontStrategy = hellOptions.strategies.stream().
                    filter(strategy -> strategy instanceof FontStrategy)
                    .map(strategy -> (FontStrategy) strategy)
                    .filter(strategy-> strategy.getRowCellMap().containsKey(hellRow) && strategy.getRowCellMap().containsValue(hellCell)).findFirst().orElse(null);
            if (fontStrategy != null) {
                fontStrategy.setBold(true);
                return this;
            } else {
                fontStrategy = new FontStrategy();
                Map<Integer, Integer> rowCellMap = new HashMap<>();
                rowCellMap.put(hellRow, hellCell);
                fontStrategy.setRowCellMap(rowCellMap);
                fontStrategy.setBold(true);
                hellOptions.strategies.add(fontStrategy);
                return this;
            }
        }

        public Options withBoldByCell(Integer hellRow) {
            CellStrategy cellStrategy = hellOptions.cellStrategies.get(hellRow);
            if(cellStrategy == null){
                FontStrategy fontStrategy = new FontStrategy();
                cellStrategy = new CellStrategy();
                Styles styles = new Styles();
                styles.setBold(true);
                fontStrategy.setBold(true);
                cellStrategy.setStyle(styles);
                cellStrategy.getStrategies().add(fontStrategy);
                hellOptions.cellStrategies.put(hellRow,cellStrategy);
                return this;
            }
            cellStrategy.getStrategies().forEach(strategy -> {
                if(strategy instanceof FontStrategy){
                    ((FontStrategy) strategy).setBold(true);
                }
            });
            cellStrategy.getStyle().setBold(true);
            return this;
        }

        public Options withItalicByRowAndCell(Integer hellCell, Integer hellRow) {
            FontStrategy fontStrategy = hellOptions.strategies.stream().
                    filter(strategy -> strategy instanceof FontStrategy)
                    .map(strategy -> (FontStrategy) strategy)
                    .filter(strategy-> strategy.getRowCellMap().containsKey(hellRow) && strategy.getRowCellMap().containsValue(hellCell)).findFirst().orElse(null);
            if (fontStrategy != null) {
                fontStrategy.setItalic(true);
                return this;
            } else {
                fontStrategy = new FontStrategy();
                Map<Integer, Integer> rowCellMap = new HashMap<>();
                rowCellMap.put(hellRow, hellCell);
                fontStrategy.setRowCellMap(rowCellMap);
                fontStrategy.setItalic(true);
                hellOptions.strategies.add(fontStrategy);
                return this;
            }
        }
        public Options withItalicByCell(Integer hellCell) {
            CellStrategy cellStrategy = hellOptions.cellStrategies.get(hellCell);
            if(cellStrategy == null){
                FontStrategy fontStrategy = new FontStrategy();
                cellStrategy = new CellStrategy();
                Styles styles = new Styles();
                styles.setItalic(true);
                cellStrategy.setStyle(styles);
                fontStrategy.setItalic(true);
                cellStrategy.getStrategies().add(fontStrategy);
                hellOptions.cellStrategies.put(hellCell,cellStrategy);
                return this;
            }
            cellStrategy.getStrategies().forEach(strategy -> {
                if(strategy instanceof FontStrategy){
                    ((FontStrategy) strategy).setItalic(true);
                }
            });
            cellStrategy.getStyle().setItalic(true);
            return this;
        }

        public <T> void writeToExHell(List<T> objects) throws IllegalAccessException {
            HellWriter.writeToExHell(objects, hellOptions);
        }

        public HellOptions build() {
            return hellOptions;
        }

    }

    public String getFileName() {
        return fileName;
    }
    public List<Strategy> getStrategies() {
        return strategies;
    }

    public Map<Integer, CellStrategy> getcellStrategies() {
        return cellStrategies;
    }
    public List<Strategy> getStrategiesWithRowAndCell(Integer hellCell, Integer hellRow) {
        List<Strategy> strategies = new ArrayList<>(this.getStrategies());
        strategies.removeIf(strategy -> !strategy.getRowCellMap().containsKey(hellRow) || !strategy.getRowCellMap().containsValue(hellCell));
        return strategies;
    }
    public CellStrategy getStrategyWithCell(Integer hellCell) {
        Map<Integer, CellStrategy> cellStrategyMap = this.getcellStrategies();
        return cellStrategyMap.get(hellCell);
    }
}
