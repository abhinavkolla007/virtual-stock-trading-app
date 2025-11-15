package com.abhi.virtualstock.model;

import com.abhi.virtualstock.constants.Constants;
import com.fasterxml.jackson.annotation.JsonBackReference;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private Constants.OrderAction action;

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

    @Min(value = 0, message = "Trade price can't be a negative number.")
    private BigDecimal tradePrice;

    @NotNull
    private Constants.OrderDuration duration;

    @NotNull
    private Constants.OrderType type;

    @NotNull
    private LocalDateTime dateTime;
}
