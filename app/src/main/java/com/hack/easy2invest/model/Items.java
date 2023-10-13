package com.hack.easy2invest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("symbol")
    @Expose
    String symbol;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("currency")
    @Expose
    String currency;

    @SerializedName("stockExchange")
    @Expose
    String stockExchange;

    @SerializedName("exchangeShortName")
    @Expose
    String exchangeShortName;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public String getExchangeShortName() {
        return exchangeShortName;
    }

    public void setExchangeShortName(String exchangeShortName) {
        this.exchangeShortName = exchangeShortName;
    }
}
