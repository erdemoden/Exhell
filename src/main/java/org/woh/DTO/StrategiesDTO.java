package org.woh.DTO;

import org.woh.DTO.interfaces.Strategy;

import java.util.List;

public class StrategiesDTO {
    private List<Strategy> strategies;
    private Integer row;
    private Integer column;
    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
}
