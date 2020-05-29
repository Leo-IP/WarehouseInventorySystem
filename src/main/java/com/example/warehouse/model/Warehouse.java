package com.example.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse extends BaseEntity implements Serializable {

    @Column(nullable = false, unique = true)
    @NaturalId
    private String warehouseCode;

    @OneToMany(mappedBy = "warehouse")
    private Set<Inventory>  inventories;
}
