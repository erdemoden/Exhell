package org.woh.Service;

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
    private List<BackGroundColor> backgroundColorByRowAndCell;
    private List<FontStrategy> fontColorByRowAndCell;
    private List<BorderColor> borderColorByRowAndCell;
    private List<FontStrategy> boldByRowAndCell;
    private List<FontStrategy> boldByRow;
    private List<FontStrategy> italicByRowAndCell;
    private List<FontStrategy> fontStrategies;
    private Map<Integer, IndexedColors> backgroundColorByRow;
    private List<Strategy> strategies;
    private Map<Integer, CellStrategy> cellStrategies;

    private HellOptions() {
        backgroundColorByRowAndCell = new ArrayList<>();
        fontColorByRowAndCell = new ArrayList<>();
        borderColorByRowAndCell = new ArrayList<>();
        boldByRowAndCell = new ArrayList<>();
        italicByRowAndCell = new ArrayList<>();
        strategies = new ArrayList<>();
        cellStrategies = new HashMap<>();
        boldByRow = new ArrayList<>();
        fontStrategies = new ArrayList<>();
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
            CellStrategy rowStrategy = hellOptions.cellStrategies.get(hellCell);
            BackGroundColor backGroundColor = new BackGroundColor();
            backGroundColor.setColor(color);
            if(rowStrategy == null){
                rowStrategy = new CellStrategy();
                Styles styles = new Styles();
                styles.setBackgroundColor(color);
                rowStrategy.setStyle(styles);
                rowStrategy.getStrategies().add(backGroundColor);
                hellOptions.cellStrategies.put(hellCell,rowStrategy);
                return this;
            }
            rowStrategy.getStrategies().add(backGroundColor);
            rowStrategy.getStyle().setBackgroundColor(color);
            return this;
        }

        public Options withFontColorByRowAndCell(Integer hellCell, Integer hellRow, IndexedColors color) {
            FontStrategy fontStrategy = hellOptions.fontStrategies.stream().filter(strategy -> strategy!=null &&  strategy.getRowCellMap().containsKey(hellRow) && strategy.getRowCellMap().containsValue(hellCell)).findFirst().orElse(null);
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
            hellOptions.fontStrategies.add(fontStrategy);
            return this;
        }
        }
        public Options withFontColorByCell(Integer hellCell, IndexedColors color) {
            CellStrategy rowStrategy = hellOptions.cellStrategies.get(hellCell);
            if(rowStrategy == null){
                FontStrategy fontStrategy = new FontStrategy();
                rowStrategy = new CellStrategy();
                Styles styles = new Styles();
                fontStrategy.setFontColor(color);
                styles.setFontColor(color);
                rowStrategy.setStyle(styles);
                rowStrategy.getStrategies().add(fontStrategy);
                hellOptions.cellStrategies.put(hellCell,rowStrategy);
                return this;
            }
            rowStrategy.getStrategies().forEach(strategy -> {
                if(strategy instanceof FontStrategy){
                    ((FontStrategy) strategy).setFontColor(color);
                }
            });
            rowStrategy.getStyle().setFontColor(color);
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
            CellStrategy rowStrategy = hellOptions.cellStrategies.get(hellCell);
            BorderColor borderColor = new BorderColor();
            borderColor.setColor(color);
            if(rowStrategy == null){
                rowStrategy = new CellStrategy();
                Styles styles = new Styles();
                styles.setBorderColor(color);
                rowStrategy.setStyle(styles);
                rowStrategy.getStrategies().add(borderColor);
                hellOptions.cellStrategies.put(hellCell,rowStrategy);
                return this;
            }
            rowStrategy.getStrategies().add(borderColor);
            rowStrategy.getStyle().setBorderColor(color);
            return this;
        }

        public Options withBoldByRowAndCell(Integer hellCell, Integer hellRow) {
            FontStrategy fontStrategy = hellOptions.fontStrategies.stream().filter(strategy -> strategy!=null && strategy.getRowCellMap().containsKey(hellRow) && strategy.getRowCellMap().containsValue(hellCell)).findFirst().orElse(null);
            if (fontStrategy != null) {
                fontStrategy.setBold(true);
                return this;
            } else {
                fontStrategy = new FontStrategy();
                Map<Integer, Integer> rowCellMap = new HashMap<>();
                rowCellMap.put(hellRow, hellCell);
                fontStrategy.setRowCellMap(rowCellMap);
                fontStrategy.setBold(true);
                hellOptions.fontStrategies.add(fontStrategy);
                return this;
            }
        }

        public Options withBoldByCell(Integer hellRow) {
            CellStrategy rowStrategy = hellOptions.cellStrategies.get(hellRow);
            if(rowStrategy == null){
                FontStrategy fontStrategy = new FontStrategy();
                rowStrategy = new CellStrategy();
                Styles styles = new Styles();
                styles.setBold(true);
                fontStrategy.setBold(true);
                rowStrategy.setStyle(styles);
                rowStrategy.getStrategies().add(fontStrategy);
                hellOptions.cellStrategies.put(hellRow,rowStrategy);
                return this;
            }
            rowStrategy.getStrategies().forEach(strategy -> {
                if(strategy instanceof FontStrategy){
                    ((FontStrategy) strategy).setBold(true);
                }
            });
            rowStrategy.getStyle().setBold(true);
            return this;
        }

        public Options withItalicByRowAndCell(Integer hellCell, Integer hellRow, Boolean isItalic) {
            FontStrategy fontStrategy = hellOptions.fontStrategies.stream().filter(strategy -> strategy!=null && strategy.getRowCellMap().containsKey(hellRow) && strategy.getRowCellMap().containsValue(hellCell)).findFirst().orElse(null);
            if (fontStrategy != null) {
                fontStrategy.setItalic(isItalic);
                return this;
            } else {
                fontStrategy = new FontStrategy();
                Map<Integer, Integer> rowCellMap = new HashMap<>();
                rowCellMap.put(hellRow, hellCell);
                fontStrategy.setRowCellMap(rowCellMap);
                fontStrategy.setItalic(isItalic);
                hellOptions.fontStrategies.add(fontStrategy);
                return this;
            }
        }
        public Options withItalicByCell(Integer hellCell) {
            CellStrategy rowStrategy = hellOptions.cellStrategies.get(hellCell);
            if(rowStrategy == null){
                FontStrategy fontStrategy = new FontStrategy();
                rowStrategy = new CellStrategy();
                Styles styles = new Styles();
                styles.setItalic(true);
                rowStrategy.setStyle(styles);
                fontStrategy.setItalic(true);
                rowStrategy.getStrategies().add(fontStrategy);
                hellOptions.cellStrategies.put(hellCell,rowStrategy);
                return this;
            }
            rowStrategy.getStrategies().forEach(strategy -> {
                if(strategy instanceof FontStrategy){
                    ((FontStrategy) strategy).setItalic(true);
                }
            });
            rowStrategy.getStyle().setItalic(true);
            return this;
        }

        public <T> void writeToExHell(List<T> objects) throws IllegalAccessException {
            hellOptions.strategies.addAll(hellOptions.fontStrategies);
            HellWriter.writeToExHell(objects, hellOptions);
        }

        public HellOptions build() {
            return hellOptions;
        }

    }

    public String getFileName() {
        return fileName;
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

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public Map<Integer, CellStrategy> getcellStrategies() {
        return cellStrategies;
    }

    public List<FontStrategy> getBoldByRow() {
        return boldByRow;
    }

    public List<Strategy> getStrategiesWithRowAndCell(Integer hellCell, Integer hellRow) {
        List<Strategy> strategies = new ArrayList<>(this.getStrategies());
        strategies.removeIf(strategy -> !strategy.getRowCellMap().containsKey(hellRow) || !strategy.getRowCellMap().containsValue(hellCell));
        return strategies;
    }
    public CellStrategy getStrategyWithRow(Integer hellRow) {
        Map<Integer, CellStrategy> rowStrategyMap = this.getcellStrategies();
        return rowStrategyMap.get(hellRow);
    }
}
