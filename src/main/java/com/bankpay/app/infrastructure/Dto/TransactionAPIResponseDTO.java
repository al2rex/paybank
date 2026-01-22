package com.bankpay.app.infrastructure.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionAPIResponseDTO {
    private Integer idTransaction;
    private String status;

}
