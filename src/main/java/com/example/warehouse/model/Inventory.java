package com.example.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends BaseEntity{

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(
            name = "product_code",
            referencedColumnName = "productCode"
    )
    private Product product;

    @ManyToOne(targetEntity = Warehouse.class)
    @JoinColumn(
            name = "warehouse_code",
            referencedColumnName = "warehouseCode"
    )
    private Warehouse warehouse;

    @Column(nullable = false)
    private int qty;


}
