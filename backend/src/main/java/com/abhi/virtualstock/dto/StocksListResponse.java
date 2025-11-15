package com.abhi.virtualstock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import com.abhi.virtualstock.model.StockInfo;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StocksListResponse {
    private List<StockInfo> data;
}
