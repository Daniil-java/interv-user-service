package com.kuklin.user_service.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceSubtractRequest {
    private BigDecimal amount;
}
