package com.example.warehouse.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends BaseEntity{

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(
            name = "product_code",
            referencedColumnName = "productCode",
            insertable = false,
            updatable = false
    )
    private Product product;

    @ManyToOne(targetEntity = Warehouse.class)
    @JoinColumn(
            name = "warehouse_code",
            referencedColumnName = "warehouseCode",
            insertable = false,
            updatable = false
    )
    private Warehouse warehouse;

    @Column(name = "warehouse_code", nullable = false)
    @CsvBindByName(column = "Warehouse_Code")
    private String warehouseCode;

    @Column( name = "product_code", nullable = false)
    @CsvBindByName(column = "Product_Code")
    private String productCode;

    @Column(nullable = false)
    @CsvBindByName(column = "Qty")
    private int qty;


}
