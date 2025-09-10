package com.kuklin.userservice.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceSubtractRequest {
    private BigDecimal amount;
}
