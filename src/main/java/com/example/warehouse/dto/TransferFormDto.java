package com.example.warehouse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferFormDto {

    private String productCode;
    private String fromWarehouseCode;
    private String toWarehouseCode;
    private int qty;
    private int checkingQty;
}
