package com.fingenius.json;

import java.io.Serializable;

public class Counter implements Serializable{
    private String number;
    private Integer count;

    public Counter(String number, Integer count) {
        this.number = number;
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public String getNumber() {
        return number;
    }
}
