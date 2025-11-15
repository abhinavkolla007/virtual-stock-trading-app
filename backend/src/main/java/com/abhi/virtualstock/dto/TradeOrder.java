package com.abhi.virtualstock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

import com.abhi.virtualstock.constants.Constants;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TradeOrder {
    private String symbol;
    private String name;
    private Constants.OrderAction action;
    private int quantity;
    private Constants.OrderType type;
    private BigDecimal price;
    private Constants.OrderDuration duration;

    @Override
    public String toString() {
        return "TradeOrder{" +
            "symbol='" + symbol + '\'' +
            ", name='" + name + '\'' +
            ", action=" + action +
            ", quantity=" + quantity +
            ", type=" + type +
            ", price=" + price +
            ", duration=" + duration +
            '}';
    }
}
