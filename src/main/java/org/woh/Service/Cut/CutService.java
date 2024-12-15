package org.woh.Service.Cut;

import org.woh.annotations.HellIndex;

public class CutService {
    @HellIndex(hellColumnOrder = 1)
    private String cutId;
    @HellIndex(hellColumnOrder = 2)
    private Integer cutAmount;
    @HellIndex(hellColumnOrder = 3,hellRowOrder = 1)
    private String name;
    @HellIndex(hellColumnOrder = 4,hellRowOrder = 2)
    private String surname;
    @HellIndex(hellColumnOrder = 5,hellRowOrder = 3)
    private String address;

    public String getCutId() {
        return cutId;
    }

    public void setCutId(String cutId) {
        this.cutId = cutId;
    }

    public Integer getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(Integer cutAmount) {
        this.cutAmount = cutAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
