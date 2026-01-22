package com.bankpay.app.infrastructure.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionAPIResponseDTO {
    private Integer idTransaction;
    private String status;

}
