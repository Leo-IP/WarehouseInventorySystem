package com.example.warehouse.model;


import com.opencsv.bean.CsvBindAndJoinByPosition;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity implements Serializable {

    @Column(nullable = false)
    @CsvBindByName(column = "Product_Name")
//    @CsvBindByPosition(position = 0)
    private String productName;

    @Column(nullable = false, unique = true)
    @NaturalId
    @CsvBindByName(column = "Code")
    private String productCode;

    @CsvBindByName(column = "Weight")
    @Type(type = "big_decimal")
    @Column(nullable = false)
    private BigDecimal weight;

    @OneToMany(mappedBy = "product")
    private Set<Inventory> inventories;
}
