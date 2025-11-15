package com.abhi.virtualstock.dto;

import com.abhi.virtualstock.model.StockHolding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BestWorstStocksResponse {
    private StockHolding best;
    private StockHolding worst;
}
