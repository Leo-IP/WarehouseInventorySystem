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

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(
            name = "product_code",
            referencedColumnName = "productCode",
            insertable = false,
            updatable = false
    )
    private Product product;

    @ManyToOne(targetEntity = Warehouse.class, fetch = FetchType.LAZY, cascade={CascadeType.ALL})
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

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Inventory)) return false;
        if (!super.equals(o)) return false;

        Inventory inventory = (Inventory) o;

        if (!getId().equals(inventory.getId())) return false;
        if (!getWarehouseCode().equals(inventory.getWarehouseCode())) return false;
        if (!getProductCode().equals(inventory.getProductCode())) return false;
        return getQty() == (inventory.getQty());
    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = 31 * result + getId().hashCode();
        result = 31 * result + getWarehouseCode().hashCode();
        result = 31 * result + getProductCode().hashCode();
        result = 31 * result + Integer.hashCode(getQty());
        return result;
    }

    @Override
    public String toString(){
        return "Inventory{" +
                "id ='" + getId() + '\'' +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", productCode='" + productCode + '\'' +
                ", qty='" + qty +'\'' +
                '}';
    }
}
