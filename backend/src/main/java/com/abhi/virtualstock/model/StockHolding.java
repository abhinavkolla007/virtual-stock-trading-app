package com.abhi.virtualstock.model;

import com.abhi.virtualstock.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stock_holding")
public class StockHolding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @Transient
    private BigDecimal currentPrice;

    @NotNull
    @NotEmpty
    @NotBlank
    private String symbol;

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @Min(value = 0, message = "Stock quantity needs to be at least 0.")
    private int quantity;

    @Min(value = 0, message = "Purchase price can't be a negative number.")
    private BigDecimal purchasePrice;

    @NotNull
    private Constants.OrderDuration duration;

    @NotNull
    private Constants.OrderType type;

    @NotNull
    private LocalDateTime dateTime;
}
