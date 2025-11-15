package com.abhi.virtualstock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.virtualstock.dto.ErrorResponse;
import com.abhi.virtualstock.dto.TradeOrder;
import com.abhi.virtualstock.exception.InvalidOrderException;
import com.abhi.virtualstock.service.TradeService;

@RestController
@RequestMapping("/trade")
public class TradeController {
    private final TradeService tradeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody TradeOrder tradeOrder) {
        try {
            return ResponseEntity.ok(tradeService.placeOrder(tradeOrder));
        } catch (InvalidOrderException e) {
            LOGGER.error("Invalid trade order: {}", e.getMessage());
            return ResponseEntity.status(400).body(
                new ErrorResponse(e.getMessage())
            );
        } catch (Exception e) {
            LOGGER.error("Couldn't place an order: {}", e.getMessage());
            return ResponseEntity.status(500).body(
                new ErrorResponse(e.getMessage())
            );
        }
    }
}
