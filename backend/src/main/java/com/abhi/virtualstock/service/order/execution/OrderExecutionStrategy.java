package com.abhi.virtualstock.service.order.execution;

import com.abhi.virtualstock.dto.TradeOrder;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.BigDecimal;

public interface OrderExecutionStrategy {
    ObjectNode executeOrder(String email, TradeOrder tradeOrder, BigDecimal price) throws Exception;
}
